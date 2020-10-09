package top.xuxing.security.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Controller
public class WebForward {
    @PostMapping("/index")
    public String doIndex(){
        // 在全局任意地方获取用户信息
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("authentication.getPrincipal() = " + authentication.getPrincipal());
        return "index";
    }
}
