package top.xuxing.security.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.context.SpringContextUtils;
import top.xuxing.security.entity.User;
import top.xuxing.security.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Service
public class UserServiceImpl implements UserService, InitializingBean {

    private static List<User> users = new CopyOnWriteArrayList<>();

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User getById(Long userId) {
        for (User user: users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (long i = 0; i < 10; i++) {
            User user = new User(i, "xuxing_" + i, bCryptPasswordEncoder.encode("xuxing"));
            users.add(user);
        }
    }
}
