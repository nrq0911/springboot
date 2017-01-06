package com.example.controller;

import com.example.domain.UserCreateFrom;
import com.example.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by nrq on 2016/12/24.
 */
@Controller
public class UserController {

    private transient final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public ModelAndView getUserList(){
        logger.info("获取所有用户");
        return new ModelAndView("users","users",userService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/user/create",method = RequestMethod.GET)
    public ModelAndView getUserCreatePage(){
        logger.info("跳转到创建一个新用户页面");
        return new ModelAndView("user_create","form",new UserCreateFrom());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/user/create",method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("form") UserCreateFrom form, BindingResult bindingResult){
        logger.info("创建一个新用户"+form);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user_create";
        }
        
        return "redirect:/users";
    }

    @RequestMapping("/user/{username}")
    public ModelAndView userInfo(@PathVariable String username){
        logger.info("查看用户"+username+"信息");
        return new ModelAndView("user","user",userService.getUserByUserName(username));
    }

}
