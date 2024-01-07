package com.zust.controller;

import com.zust.entity.Code;
import com.zust.entity.Result;
import com.zust.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 * @date 2024-1-3 003 15:14
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @DubboReference
    UserService userService;

    @GetMapping
    public Result getUser(@RequestParam("id") String id) {
        return new Result(Code.SUCCESS, userService.selectById(Integer.parseInt(id)), "用户加载成功");
    }

    @GetMapping("/all")
    public Result getUsers(@RequestParam("role") String role) {
        return Result.success(userService.selectPage(null, null, 1, role));
    }
}
