package com.example.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Created by nrq on 2017/1/4.
 */
@Controller
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping("/home")
    public String getHomePage(){
        logger.info("Getting home page");
        return "/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam Optional<String> error){
        logger.debug("Getting login page, error=" + error);
        return new ModelAndView("login", "error", error);
    }

}
