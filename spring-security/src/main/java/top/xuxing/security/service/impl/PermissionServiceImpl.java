package top.xuxing.security.service.impl;

import org.springframework.stereotype.Service;
import top.xuxing.security.entity.Permission;
import top.xuxing.security.service.PermissionService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private static List<Permission> permissions = new CopyOnWriteArrayList<>();

    static {
        Permission permission_add = new Permission(1L, "add", "增加权限");
        Permission permission_delete = new Permission(2L, "delete", "删除权限");
        Permission permission_update = new Permission(3L, "update", "修改权限");
        Permission permission_query = new Permission(4L, "query", "查询权限");

        permissions.add(permission_add);
        permissions.add(permission_delete);
        permissions.add(permission_update);
        permissions.add(permission_query);
    }

    @Override
    public List<Permission> getByIds(List<Long> permissionIds) {
        List<Permission> collect = permissions.stream().filter(item -> {
            return permissionIds.contains(item.getId());
        }).collect(Collectors.toList());
        return collect;
    }
}
