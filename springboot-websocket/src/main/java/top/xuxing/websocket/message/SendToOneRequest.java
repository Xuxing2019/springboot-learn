package top.xuxing.websocket.message;

/**
 * @author xuxing
 * @date 2020/8/27
 */
public class SendToOneRequest implements Message{

    public static final String TYPE = "SEND_TO_ONE_REQUEST";


    private String msgId;

    private Integer code;

    private String content;

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getMsgId() {
        return msgId;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getContent() {
        return content;
    }
}
