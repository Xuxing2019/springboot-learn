package top.xuxing.security.token.service;

import top.xuxing.security.token.entity.Role;

import java.util.List;

/**
 * @author xuxing
 * @date 2020/10/9
 */
public interface RoleService {
    List<Role> getByIds(List<Long> roleIds);
}
