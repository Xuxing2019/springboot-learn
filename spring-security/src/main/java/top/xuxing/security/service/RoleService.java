package top.xuxing.security.service;

import top.xuxing.security.entity.Role;

import java.util.List;

/**
 * @author xuxing
 * @date 2020/10/9
 */
public interface RoleService {
    List<Role> getByIds(List<Long> roleIds);
}
