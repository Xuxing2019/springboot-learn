package top.xuxing.websocket.message.handler;

import org.springframework.stereotype.Component;
import top.xuxing.websocket.message.SendResponse;
import top.xuxing.websocket.message.SendToOneRequest;
import top.xuxing.websocket.utils.WebSocketUtil;

import javax.websocket.Session;

/**
 * @author xuxing
 * @date 2020/8/29
 */
@Component
public class ErrorMessageHandler implements MessageHandler<SendResponse>{
    @Override
    public void execute(Session session, SendResponse message) {
        WebSocketUtil.send(session, message.TYPE, message);
    }

    @Override
    public String getType() {
        return "ERROR_MESSAGE_HANDLER";
    }

    @Override
    public SendResponse getMessageType() {
        return new SendResponse();
    }

}
