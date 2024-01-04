package com.zust.controller;

import com.zust.service.TaskService;
import com.zust.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
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

    @DubboReference
    private UserService userService;


}
