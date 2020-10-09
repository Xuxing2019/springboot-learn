package top.xuxing.security.service;

import top.xuxing.security.entity.UserRoleRelevance;

import java.util.List;

/**
 * @author xuxing
 * @date 2020/10/9
 */
public interface UserRoleRelevanceService {
    List<UserRoleRelevance> getByUserId(Long userId);
}
