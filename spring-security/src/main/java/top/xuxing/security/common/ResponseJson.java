package top.xuxing.security.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
