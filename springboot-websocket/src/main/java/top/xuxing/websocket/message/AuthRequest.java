package top.xuxing.websocket.message;

import javax.swing.*;

/**
 * @author xuxing
 * @date 2020/8/27
 * @desc 用于认证的 Message
 */
public class AuthRequest implements Message{

    public static final String TYPE = "AUTH_REQUEST";

    private String msgId;

    private Integer code;

    private String content;

    /**
     * 认证 Token
     */
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

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
