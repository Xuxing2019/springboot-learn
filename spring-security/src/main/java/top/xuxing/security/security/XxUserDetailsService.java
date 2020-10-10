package top.xuxing.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.xuxing.security.entity.*;
import top.xuxing.security.service.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuxing
 * @date 2020/10/9
 */
@Component
public class XxUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleRelevanceService userRoleRelevanceService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RolePermissionRelevanceService rolePermissionRelevanceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        List<UserRoleRelevance> userRoleRelevanceList = userRoleRelevanceService.getByUserId(user.getId());
        List<Long> roleIds = userRoleRelevanceList.stream().map(item -> {
            return item.getRoleId();
        }).collect(Collectors.toList());

        List<Role> roleList = roleService.getByIds(roleIds);

        List<RolePermissionRelevance> rolePermissionRelevanceList = rolePermissionRelevanceService.getByRoleIds(roleIds);
        List<Long> permissionIds = rolePermissionRelevanceList.stream().map(item -> {
            return item.getPermissionId();
        }).collect(Collectors.toList());

        List<Permission> permissionList = permissionService.getByIds(permissionIds);

        HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role: roleList) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        for (Permission permission: permissionList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
        }

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
    }
}
