package top.xuxing.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 这里配置视图 默认是GET请求方式才能访问。使用Security时 需要注意的是，当你登录成功页面跳转时默认是Post请求，会出现不支持Post请求跳转的警告
        // registry.addViewController("/index").setViewName("index");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/user/list").setViewName("user-list");
        registry.addViewController("/login/custom").setViewName("login-custom");
        registry.addViewController("/hello").setViewName("hello");
    }
}
