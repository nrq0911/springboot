package com.example.service;

import com.example.domain.AdminUser;
import com.example.mapper.AdminUserMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by nrq on 2016/12/24.
 */
@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);

    @Resource
    private AdminUserMapper userMapper;

    public AdminUser getUserByUserName(String username){
        logger.info("++++++++++++++>>  " + username + "  <<+++++++");
        return userMapper.getAdminUserByLoginName(username);
    }

    public List<AdminUser> getAllUsers(){
        return userMapper.getAllUsers();
    }


}
