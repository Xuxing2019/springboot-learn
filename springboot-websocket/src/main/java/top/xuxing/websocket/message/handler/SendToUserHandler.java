package top.xuxing.websocket.message.handler;

import top.xuxing.websocket.message.SendToUserRequest;

import javax.websocket.Session;

/**
 * @author xuxing
 * @date 2020/8/29
 */
public class SendToUserHandler implements MessageHandler<SendToUserRequest> {

    @Override
    public void execute(Session session, SendToUserRequest message) {

    }

    @Override
    public String getType() {
        return SendToUserRequest.TYPE;
    }

    @Override
    public SendToUserRequest getMessageType() {
        return new SendToUserRequest();
    }
}
