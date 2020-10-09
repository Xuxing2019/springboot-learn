package top.xuxing.security.service;

import top.xuxing.security.entity.User;

/**
 * @author xuxing
 * @date 2020/10/9
 */
public interface UserService {
    User getById(Long userId);

    User getByUsername(String username);
}
