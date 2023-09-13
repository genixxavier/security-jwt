package com.gnxcode.securityjwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    @GetMapping("/access-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin(){
        return "Hi, role admin";
    }

    @GetMapping("/access-user")
    @PreAuthorize("hasRole('USER')")
    public String accessUser(){
        return "Hi, role user";
    }

    @GetMapping("/access-invited")
    @PreAuthorize("hasRole('INVITED')")
    public String accessInvited(){
        return "Hi, role invited";
    }
}
