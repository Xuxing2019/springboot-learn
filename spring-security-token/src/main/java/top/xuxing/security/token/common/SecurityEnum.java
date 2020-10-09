package top.xuxing.security.token.common;

import lombok.Data;

/**
 * 响应类型枚举
 * @author xuxing
 * @date 2020/10/9
 */
public enum SecurityEnum implements EnumInterface{
    OK(1, "请求成功"),
    ERROR(-1, "未知异常");

    private Integer code;
    private String msg;

    SecurityEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
