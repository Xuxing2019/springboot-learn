package top.xuxing.security.token.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.xuxing.security.token.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        logger.info("进入JwtFilter");
        // 获取请求头参数
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        // 校验请求头参数有效性
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        logger.info("JwtFilter_username：{}",username);
        logger.info("JwtFilter_Authentication：{}", SecurityContextHolder.getContext().getAuthentication());
        if (username != null && /*这是security内部对象*/SecurityContextHolder.getContext().getAuthentication() == null) {

            // 更新用户角色权限
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            logger.info("Filter过滤器-当前用户角色权限信息：{}", userDetails.getAuthorities());

            /**
             * 用jwt解析出来的username 和 UserDetails 中的username作比较 并且校验jwt没有过期
             */
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        chain.doFilter(request, response);
    }

}
