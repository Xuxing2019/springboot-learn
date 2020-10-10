package top.xuxing.security.service;

import top.xuxing.security.entity.RolePermissionRelevance;

import java.util.List;

/**
 * @author xuxing
 * @date 2020/10/10
 */
public interface RolePermissionRelevanceService {
    public List<RolePermissionRelevance> getByRoleIds(List<Long> roleIds);
}
