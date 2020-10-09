package top.xuxing.security.token.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRelevance {
    private Long id;
    private Long userId;
    private Long roleId;
}
