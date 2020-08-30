package top.xuxing.websocket.message;

/**
 * @author xuxing
 * @date 2020/8/27
 * @desc 所有消息类都需要继承该接口
 */
public interface Message {
    String getMsgId();
    Integer getCode();
    String getContent();
    void setMsgId(String msgId);
    void setCode(Integer code);
    void setContent(String content);
}
