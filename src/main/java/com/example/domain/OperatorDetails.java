package com.example.domain;

import com.example.domain.AdminRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 扩展SpringSecurity的WebAuthenticationDetails类, 增加登录时间属性和角色属性.
 */
public class OperatorDetails extends User{

	private static final long serialVersionUID = 1L;

	private Date loginTime;

    private List<AdminRole> roleList;

    public OperatorDetails(String username, String password, boolean enabled,
            boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked, Collection<GrantedAuthority> authorities)
            throws IllegalArgumentException{
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
    }

    public boolean isInRole(String roleName){
        for (GrantedAuthority role : getAuthorities()) {
            if (role.getAuthority().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

    public List<AdminRole> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<AdminRole> roleList)
    {
        this.roleList = roleList;
    }
}
