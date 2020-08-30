package top.xuxing.websocket.message.handler;

import org.springframework.stereotype.Component;
import top.xuxing.websocket.message.SendResponse;
import top.xuxing.websocket.message.SendToOneRequest;
import top.xuxing.websocket.message.SendToUserRequest;
import top.xuxing.websocket.utils.WebSocketUtil;

import javax.websocket.Session;
import java.util.UUID;

/**
 * @author xuxing
 * @date 2020/8/27
 * @desc 发送一条信息到指定会话
 */
@Component
public class SendToOneHandler implements MessageHandler<SendToOneRequest>{
    @Override
    public void execute(Session session, SendToOneRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = new SendResponse();
        sendResponse.setMsgId(message.getMsgId());
        sendResponse.setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息 这里直接用请求消息类型返回 需要自己自定义可以写一个Response类
        SendToOneRequest sendToOneRequest = new SendToOneRequest();
        sendToOneRequest.setMsgId(UUID.randomUUID().toString().replace("-",""));
        sendToOneRequest.setContent(message.getContent()+"----- 这条消息的回复信息");

        WebSocketUtil.sendOneMessageToChatRoom("sessionToken", SendToOneRequest.TYPE, sendToOneRequest);
    }

    @Override
    public String getType() {
        return SendToOneRequest.TYPE;
    }

    @Override
    public SendToOneRequest getMessageType() {
        return new SendToOneRequest();
    }
}
