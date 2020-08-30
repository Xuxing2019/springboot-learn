package top.xuxing.websocket.message;

import javax.swing.*;

/**
 * @author xuxing
 * @date 2020/8/27
 */
public class AuthResponse implements Message{

    public static final String TYPE = "AUTH_RESPONSE";

    private String msgId;

    private Integer code;

    private String content;
    /**
     * 响应提示
     */
    private String message;


    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
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
