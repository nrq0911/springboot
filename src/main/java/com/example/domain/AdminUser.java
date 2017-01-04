package com.example.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**后台系统用户类*/
public class AdminUser {
	
    private BigDecimal id;

    private String email;

    private Long accountEnabled;

    private String loginName;

    private String name;

    private String[] operationalPaybanks;
    /**加密密码*/
    private String shaPassword;
    /**账号版本*/
    private Long version;
    
    private List<AdminRole> userRole;
    public List<AdminRole> getUserRole() { return userRole; }
    public void setUserRole(List<AdminRole> userRole) {  this.userRole = userRole; }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(Long accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String[] getOperationalPaybanks() {
        return operationalPaybanks;
    }

    public void setOperationalPaybanks(String[] operationalPaybanks) {
        this.operationalPaybanks = operationalPaybanks;
    }

    public String getShaPassword() {
        return shaPassword;
    }

    public void setShaPassword(String shaPassword) {
        this.shaPassword = shaPassword == null ? null : shaPassword.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", accountEnabled=" + accountEnabled +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", operationalPaybanks=" + Arrays.toString(operationalPaybanks) +
                ", shaPassword='" + shaPassword + '\'' +
                ", version=" + version +
                ", userRole=" + userRole +
                '}';
    }
}