package top.xuxing.security.service.impl;

import org.springframework.stereotype.Service;
import top.xuxing.security.entity.UserRoleRelevance;
import top.xuxing.security.service.UserRoleRelevanceService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Service
public class UserRoleRelevanceServiceImpl implements UserRoleRelevanceService {

    private static List<UserRoleRelevance> userRoleRelevances = new CopyOnWriteArrayList<>();

    static {
        for (long i = 0; i < 3; i++) {
            UserRoleRelevance userRoleRelevance = new UserRoleRelevance(i, 1L, i);
            userRoleRelevances.add(userRoleRelevance);
        }
    }

    @Override
    public List<UserRoleRelevance> getByUserId(Long userId) {
        List<UserRoleRelevance> collect = userRoleRelevances.stream().filter(item -> {
            return item.getUserId().equals(userId);
        }).collect(Collectors.toList());
        return collect;
    }
}
