package com.example.domain;

import org.hibernate.validator.constraints.NotEmpty;
import java.util.List;

/**
 * Created by nrq on 2017/1/6.
 */
public class UserCreateFrom {

    @NotEmpty
    private String loginName = "";

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String comfirmPassword = "";

    private List<AdminRole> roleList ;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComfirmPassword() {
        return comfirmPassword;
    }

    public void setComfirmPassword(String comfirmPassword) {
        this.comfirmPassword = comfirmPassword;
    }

    public List<AdminRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<AdminRole> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserCreateFrom{" +
                "loginName='" + loginName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", comfirmPassword='" + comfirmPassword + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
