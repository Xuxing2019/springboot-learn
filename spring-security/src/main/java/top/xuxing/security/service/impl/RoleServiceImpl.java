package top.xuxing.security.service.impl;

import org.springframework.stereotype.Service;
import top.xuxing.security.entity.Role;
import top.xuxing.security.service.RoleService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static List<Role> roles = new CopyOnWriteArrayList<>();

    static {
        for (long i = 0; i < 10; i++) {
            Role role = new Role(i, "admin_" + i, "role_" + i + "无描述");
            roles.add(role);
        }
    }


    @Override
    public List<Role> getByIds(List<Long> roleIds) {
        List<Role> collect = roles.stream().filter(item -> {
            return roleIds.contains(item.getId());
        }).collect(Collectors.toList());
        return collect;
    }
}
