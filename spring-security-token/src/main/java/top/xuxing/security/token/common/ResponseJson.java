package top.xuxing.security.token.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseJson<T> {
    private Integer code;
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;

    public ResponseJson(EnumInterface enumInterface, T data){
        this.code = enumInterface.getCode();
        this.msg = enumInterface.getMsg();
        this.data = data;
    }

    public static ResponseJson error(){
        return new ResponseJson(SecurityEnum.ERROR, null);
    }

    public static ResponseJson ok(){
        return new ResponseJson(SecurityEnum.OK, null);
    }

    public ResponseJson setData(T data){
        this.data = data;
        return this;
    }
}
