package com.zust.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 * @date 2024-1-3 003 15:14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test")
    public String test(){
        return "user test";
    }
}
