package com.example.domain;

import java.math.BigDecimal;

/**后台管理角色类*/
public class AdminRole {
	
	
	/**管理员  */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
    /**客服  */
    public static final String ROLE_WAITER = "ROLE_WAITER";
    /**编辑  */
    public static final String ROLE_EDITOR = "ROLE_EDITOR";
    /**运维  */
    public static final String ROLE_OPERATE = "ROLE_OPERATE";
    /**财务  */
    public static final String ROLE_FINANCIAL = "ROLE_FINANCIAL";
	
    private BigDecimal id;
    private String description;
    private String name;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}