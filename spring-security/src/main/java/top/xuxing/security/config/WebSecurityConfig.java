package top.xuxing.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.xuxing.security.security.XxUserDetailsService;

/**
 * Security大解读
 * 1. Security有自带的登录页面 HttpBasic
 * 2. 如果想使用自定义登陆页面，需要重写configure(HttpSecurity http)方法进行配置
 * 3. 认证设置
 *      3.1 项目启动自动生成账号秘密，注意看控制台
 *      3.2 配置文件配置账号密码 spring.security.user.name=javaboy、spring.security.user.password=123
 *      3.3 通过继承WebSecurityConfigurerAdapter类重写configure(AuthenticationManagerBuilder auth) 方法 在内存中设置账号密码
 *      3.4 配置通过JDBC进行认证 (实际中都是这种认证配置)
 * @author xuxing
 * @date 2020/10/9
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private XxUserDetailsService xxUserDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf跨域安全监测
//        http.csrf().disable();
        // 记住我
        http.rememberMe().tokenValiditySeconds(3600).key("rememberMe");
        // 登录配置
        http.authorizeRequests()
                .antMatchers("/", "/login/custom", "error", "doLogin").permitAll()
                .antMatchers("/hello").hasRole("admin")
                .anyRequest().authenticated() // 表示其他接口登录就能访问
                .and()
                .formLogin()
                .loginPage("/login/custom")
                .loginProcessingUrl("/doLogin") // 设置处理请求的url，页面表单需要将请求提交到这个地址
                .usernameParameter("username") // 配置表单账号字段名
                .passwordParameter("password") // 配置表单账号字段名
                .successForwardUrl("/index") // 登录成功转发url  这里需要注意的是，如果登录成功默认转发的请求方式是post 但是由于我的视图配置请求方式是get 就会出现不支持post请求警告
                .failureForwardUrl("/error")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        out.write("success");
//                        out.flush();
//                    }
//                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        out.write("fail");
//                        out.flush();
//                    }
//                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/custom")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(xxUserDetailsService);

        // 内存数据
//        auth.inMemoryAuthentication().withUser("admin")
//                .roles("admin","boss").password(bCryptPasswordEncoder.encode("admin"))
//                .and()
//                .withUser("xuxing")
//                .roles("admin","boss").password(bCryptPasswordEncoder.encode("xuxing"));
    }

    /**
     * Spring5 之后强制要求密码必须加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
