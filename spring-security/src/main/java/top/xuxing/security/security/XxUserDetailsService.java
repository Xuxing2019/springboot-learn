package top.xuxing.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.xuxing.security.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Component
public class XxUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        top.xuxing.security.entity.User byUsername = userService.getByUsername(username);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        Set<GrantedAuthority> authorityHashSet = new HashSet<>(authorities);
        User user = new User(username, byUsername.getPassword(), authorityHashSet);
        return user;
    }
}
