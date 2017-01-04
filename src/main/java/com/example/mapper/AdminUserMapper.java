package com.example.mapper;

import com.example.domain.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by nrq on 2016/12/27.
 */
@Mapper
public interface AdminUserMapper {

    AdminUser getAdminUserByLoginName(@Param("userName") String name);



}
