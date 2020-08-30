package top.xuxing.websocket.message.handler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.xuxing.websocket.message.AuthRequest;
import top.xuxing.websocket.message.AuthResponse;
import top.xuxing.websocket.message.Message;
import top.xuxing.websocket.utils.WebSocketUtil;

import javax.websocket.Session;

/**
 * @author xuxing
 * @date 2020/8/27
 */
@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest>{

    @Override
    public void execute(Session session, AuthRequest message) {
        // 如果传递token，这里直接通过校验
        if (StringUtils.isEmpty(message.getAccessToken())){
            AuthResponse authResponse = new AuthResponse();
            authResponse.setCode(-1);
            authResponse.setMessage("Token Is Empty");
            WebSocketUtil.send(session, message.TYPE, authResponse);
            return;
        }
        // 用户认证通过就在WebSocketUtil.SESSION_USER_MAP 中添加用户标识和会话的键值对
        WebSocketUtil.addSession(session, message.getAccessToken());

        // 直接发送消息(这个处理器只有建立WebSocket连接时才会执行一次)
        AuthResponse authResponse = new AuthResponse();
        authResponse.setCode(0);
        WebSocketUtil.send(session, AuthResponse.TYPE, authResponse);
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }

    @Override
    public AuthRequest getMessageType() {
        return new AuthRequest();
    }


}
