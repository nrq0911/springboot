package com.example.controller;

import com.example.domain.OperatorDetails;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    private static final Logger logger = Logger.getLogger(CurrentUserControllerAdvice.class);

    @ModelAttribute("currentUser")
    public OperatorDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (OperatorDetails) authentication.getPrincipal();
    }
}
