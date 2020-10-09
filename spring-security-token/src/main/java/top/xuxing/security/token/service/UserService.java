package top.xuxing.security.token.service;

import top.xuxing.security.token.entity.User;

/**
 * @author xuxing
 * @date 2020/10/9
 */
public interface UserService {
    User getById(Long userId);

    User getByUsername(String username);
}
