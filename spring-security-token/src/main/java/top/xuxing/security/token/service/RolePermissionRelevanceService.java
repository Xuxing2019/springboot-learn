package top.xuxing.security.token.service;

import top.xuxing.security.token.entity.RolePermissionRelevance;

import java.util.List;

/**
 * @author xuxing
 * @date 2020/10/9
 */
public interface RolePermissionRelevanceService {
    List<RolePermissionRelevance> getByRoleIds(List<Long> roleIds);
}
