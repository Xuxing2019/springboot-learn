package top.xuxing.websocket.message;

/**
 * @author xuxing
 * @date 2020/8/27
 */
public class SendResponse implements Message{
    public static final String TYPE = "SEND_RESPONSE";

    private String msgId;

    private Integer code;

    private String content;

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    public void setContext(String content) {
        this.content = content;
    }

    @Override
    public String getMsgId() {
        return this.msgId;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getContent() {
        return this.content;
    }
}
