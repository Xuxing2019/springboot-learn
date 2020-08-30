package top.xuxing.websocket.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import top.xuxing.websocket.message.AuthRequest;
import top.xuxing.websocket.message.Message;
import top.xuxing.websocket.message.SendResponse;
import top.xuxing.websocket.message.SendToOneRequest;
import top.xuxing.websocket.message.handler.AuthMessageHandler;
import top.xuxing.websocket.message.handler.ErrorMessageHandler;
import top.xuxing.websocket.message.handler.MessageHandler;
import top.xuxing.websocket.utils.WebSocketUtil;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;


/**
 * @author xuxing
 * @date 2020/8/27
 * @desc websocket在线测试 http://www.easyswoole.com/wstool.html
 */
@Component
@ServerEndpoint("/websocket")
public class WebSocketServiceEndpoint implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(WebSocketServiceEndpoint.class);

    /**
     * 消息类型与 MessageHandler 的映射
     *
     * 注意，这里设置成静态变量。虽然说 WebsocketServerEndpoint 是单例，但是 Spring Boot 还是会为每个 WebSocket 创建一个 WebsocketServerEndpoint Bean 。
     */
    private static final Map<String, MessageHandler> HANDLERS = new HashMap<>();

    @Autowired
    ApplicationContext applicationContext;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // 进行一个假的认证 TODO 后续真的认证可以自己做，我这里只是做个Dome
        List<String> tokens = session.getRequestParameterMap().get("token");
        String token = !CollectionUtils.isEmpty(tokens) ? tokens.get(0):null;
        AuthRequest authRequest = new AuthRequest();
        authRequest.setAccessToken(token);
        MessageHandler<AuthRequest> messageHandler = HANDLERS.get(AuthRequest.TYPE);
        messageHandler.execute(session, authRequest);
        logger.info("[onOpen][session({}) 接入]", session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        // 将消息转换成JSON对象
        JSONObject messageJson = null;
        // 获取消息类型
        try {
            messageJson = JSON.parseObject(message);
            String messageType = messageJson.getString("type");
            MessageHandler messageHandler = HANDLERS.get(messageType.toUpperCase());
            if (messageType == null || messageType.equalsIgnoreCase("") || messageHandler == null){
                logger.info("无法处理当前消息类型:messageType={}", messageType);
                MessageHandler error_message_handler = HANDLERS.get("ERROR_MESSAGE_HANDLER");
                SendResponse sendResponse = new SendResponse();
                sendResponse.setContext("消息发送异常");
                error_message_handler.execute(session, sendResponse);
                return;
            }
            // 获取对应的消息处理器
            Message messageRsp = messageHandler.getMessageType();
            messageRsp.setContent(messageJson.getString("content"));
            messageRsp.setMsgId(UUID.randomUUID().toString().replace("-",""));
            messageHandler.execute(session, messageRsp);
            logger.info("[onOpen][session({}) 接收到一条消息({})]", session, message); // 生产环境下，请设置成 debug 级别
        } catch (Exception e) {
            e.printStackTrace();
            MessageHandler error_message_handler = HANDLERS.get("ERROR_MESSAGE_HANDLER");
            SendResponse sendResponse = new SendResponse();
            sendResponse.setContext("消息发送异常");
            error_message_handler.execute(session, sendResponse);
            return;
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("[onClose][session({}) 连接关闭。关闭原因是({})}]", session, closeReason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info("[onClose][session({}) 发生异常]", session, throwable);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, MessageHandler> beansOfType = applicationContext.getBeansOfType(MessageHandler.class);
        Collection<MessageHandler> values = beansOfType.values();
        values.stream().forEach(messageHandler -> {
            HANDLERS.put(messageHandler.getType(), messageHandler);
        });
    }
}
