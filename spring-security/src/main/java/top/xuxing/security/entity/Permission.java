package top.xuxing.security.entity;

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
public class Permission {
    private Long id;
    private String permissionName;
    private String permissionDesc;
}
