package top.xuxing.security.token.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xuxing.security.token.common.ResponseJson;
import top.xuxing.security.token.util.JwtUtil;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Controller
public class SecurityController {

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 这里引入纯属为了构建jwt
     */
    @Autowired
    @Qualifier("xxUserDetailsService")
    UserDetailsService userDetailsService;
    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping("/do/login")
    @ResponseBody
    public ResponseJson<String> doLogin(@RequestParam String username, @RequestParam String password){

        if (StringUtils.isAnyBlank(username, password)) {
            return ResponseJson.error();
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseJson.error();
        }
        if (authenticate == null) {
            return ResponseJson.error();
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails);
        return ResponseJson.ok().setData(token);
    }

    @RequestMapping("/main")
    @ResponseBody
    public ResponseJson<String> main(){
        return ResponseJson.ok();
    }

    @RequestMapping("/library")
    @ResponseBody
    public ResponseJson<String> library(){
        return ResponseJson.ok();
    }

    @RequestMapping("/dormitory")
    @ResponseBody
    public ResponseJson<String> dormitory(){
        return ResponseJson.ok();
    }

    @RequestMapping("/office")
    @ResponseBody
    public ResponseJson<String> office(){
        return ResponseJson.ok();
    }
}
