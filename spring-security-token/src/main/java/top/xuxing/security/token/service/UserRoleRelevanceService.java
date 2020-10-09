package top.xuxing.security.token.service;

import top.xuxing.security.token.entity.UserRoleRelevance;

import java.util.List;

/**
 * @author xuxing
 * @date 2020/10/9
 */
public interface UserRoleRelevanceService {
    List<UserRoleRelevance> getByUserId(Long userId);
}
