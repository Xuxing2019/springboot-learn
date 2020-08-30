package top.xuxing.websocket.message.handler;

import org.springframework.stereotype.Component;
import top.xuxing.websocket.message.Message;
import top.xuxing.websocket.message.SendResponse;
import top.xuxing.websocket.message.SendToAllRequest;
import top.xuxing.websocket.message.SendToUserRequest;
import top.xuxing.websocket.utils.WebSocketUtil;

import javax.websocket.Session;

/**
 * @author xuxing
 * @date 2020/8/27
 * @desc 群发
 */
@Component
public class SendToAllHandler implements MessageHandler<SendToAllRequest>{

    @Override
    public void execute(Session session, SendToAllRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = new SendResponse();
        sendResponse.setMsgId(message.getMsgId());
        sendResponse.setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToAllRequest sendToAllRequest = new SendToAllRequest();
        sendToAllRequest.setMsgId(message.getMsgId());
        sendToAllRequest.setContent(message.getContent());
        // 广播发送
        WebSocketUtil.broadcast(sendToAllRequest.TYPE, sendToAllRequest);
    }

    @Override
    public String getType() {
        return SendToAllRequest.TYPE;
    }

    @Override
    public SendToAllRequest getMessageType() {
        return new SendToAllRequest();
    }
}
