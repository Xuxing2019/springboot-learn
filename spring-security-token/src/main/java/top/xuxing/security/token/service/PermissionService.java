package top.xuxing.security.token.service;



import top.xuxing.security.token.entity.Permission;

import java.util.List;

/**
 * @author xuxing
 * @date 2020/10/9
 */
public interface PermissionService {
    List<Permission> getByIds(List<Long> permissionIds);
}
