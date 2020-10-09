package top.xuxing.security.token.service.impl;

import org.springframework.stereotype.Service;
import top.xuxing.security.token.entity.Role;
import top.xuxing.security.token.service.RoleService;

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
        Role role_1 = new Role(1L, "vip1", "role_1 无描述");
        Role role_2 = new Role(2L, "vip2", "role_2 无描述");
        Role role_3 = new Role(3L, "vip3", "role_3 无描述");
        roles.add(role_1);
        roles.add(role_2);
        roles.add(role_3);
    }


    @Override
    public List<Role> getByIds(List<Long> roleIds) {
        List<Role> collect = roles.stream().filter(item -> {
            return roleIds.contains(item.getId());
        }).collect(Collectors.toList());
        return collect;
    }
}
