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
        UserRoleRelevance userRoleRelevance1 = new UserRoleRelevance(1L, 1L, 1L);

        UserRoleRelevance userRoleRelevance4 = new UserRoleRelevance(4L, 2L, 1L);
        UserRoleRelevance userRoleRelevance5 = new UserRoleRelevance(5L, 2L, 2L);

        UserRoleRelevance userRoleRelevance6 = new UserRoleRelevance(6L, 3L, 1L);
        UserRoleRelevance userRoleRelevance7 = new UserRoleRelevance(7L, 3L, 2L);
        UserRoleRelevance userRoleRelevance8 = new UserRoleRelevance(8L, 3L, 3L);

        userRoleRelevances.add(userRoleRelevance1);
        userRoleRelevances.add(userRoleRelevance7);
        userRoleRelevances.add(userRoleRelevance8);
        userRoleRelevances.add(userRoleRelevance4);
        userRoleRelevances.add(userRoleRelevance5);
        userRoleRelevances.add(userRoleRelevance6);

    }

    @Override
    public List<UserRoleRelevance> getByUserId(Long userId) {
        List<UserRoleRelevance> collect = userRoleRelevances.stream().filter(item -> {
            return item.getUserId().equals(userId);
        }).collect(Collectors.toList());
        return collect;
    }
}
