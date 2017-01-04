package com.example.service;

import com.example.domain.AdminRole;
import com.example.domain.AdminUser;
import com.example.domain.OperatorDetails;
import com.example.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

/**
 * Created by nrq on 2016/12/28.
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        AdminUser user = userService.getUserByUserName(s);
        if (user == null){
            throw new UsernameNotFoundException("用户" + s + " 不存在");
        }

        Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

        // showcase的User类中无以下属性,暂时全部设为true.
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        if (user.getAccountEnabled() == 1 ){
            accountNonLocked = false;  //账号已经被锁定
        }

        OperatorDetails userDetails = new OperatorDetails(user.getLoginName(),
                user.getShaPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, grantedAuths);
        // 加入登录时间信息和用户角色
        userDetails.setLoginTime(new Date());
        userDetails.setRoleList(Lists.newArrayList(user.getUserRole()));

        return userDetails;
    }

    /**
     * 获得用户所有角色的权限.
     */
    private Set<GrantedAuthority> obtainGrantedAuthorities(AdminUser user){
        Set<GrantedAuthority> authSet = Sets.newHashSet();
        for (AdminRole role : user.getUserRole()){
            authSet.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authSet;
    }

    public void setAccountManager(UserService userService){
        this.userService = userService;
    }

}
