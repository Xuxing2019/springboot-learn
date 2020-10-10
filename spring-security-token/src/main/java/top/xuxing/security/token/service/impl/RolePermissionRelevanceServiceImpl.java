package top.xuxing.security.token.service.impl;

import org.springframework.stereotype.Service;
import top.xuxing.security.token.entity.RolePermissionRelevance;
import top.xuxing.security.token.service.RolePermissionRelevanceService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Service
public class RolePermissionRelevanceServiceImpl implements RolePermissionRelevanceService {
    private static List<RolePermissionRelevance> rolePermissionRelevanceList = new CopyOnWriteArrayList<>();

    static {
        RolePermissionRelevance rolePermissionRelevance1 = new RolePermissionRelevance(1L, 1L, 4L);

        RolePermissionRelevance rolePermissionRelevance2 = new RolePermissionRelevance(2L, 2L, 3L);
        RolePermissionRelevance rolePermissionRelevance3 = new RolePermissionRelevance(3L, 2L, 4L);

        RolePermissionRelevance rolePermissionRelevance4 = new RolePermissionRelevance(4L, 3L, 1L);
        RolePermissionRelevance rolePermissionRelevance5 = new RolePermissionRelevance(5L, 3L, 2L);
        RolePermissionRelevance rolePermissionRelevance6 = new RolePermissionRelevance(6L, 3L, 3L);
        RolePermissionRelevance rolePermissionRelevance7 = new RolePermissionRelevance(7L, 3L, 4L);

        rolePermissionRelevanceList.add(rolePermissionRelevance1);
        rolePermissionRelevanceList.add(rolePermissionRelevance2);
        rolePermissionRelevanceList.add(rolePermissionRelevance3);
        rolePermissionRelevanceList.add(rolePermissionRelevance4);
        rolePermissionRelevanceList.add(rolePermissionRelevance5);
        rolePermissionRelevanceList.add(rolePermissionRelevance6);
        rolePermissionRelevanceList.add(rolePermissionRelevance7);
    }
    @Override
    public List<RolePermissionRelevance> getByRoleIds(List<Long> roleIds) {
        List<RolePermissionRelevance> collect = rolePermissionRelevanceList.stream().filter(item -> {
            return roleIds.contains(item.getRoleId());
        }).collect(Collectors.toList());
        return collect;
    }
}
