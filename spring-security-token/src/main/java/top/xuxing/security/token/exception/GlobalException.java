package top.xuxing.security.token.exception;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xuxing.security.token.common.ResponseJson;

/**
 * @author xuxing
 * @date 2020/10/10
 */
@ControllerAdvice
@ResponseBody
public class GlobalException {

    @ExceptionHandler(value = {JwtException.class})
    public ResponseJson<String> JwtException(JwtException jwtException){
        return ResponseJson.error().setData(jwtException.getMessage());
    }
}
