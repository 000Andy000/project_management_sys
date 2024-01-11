package com.zust.controller;

import com.zust.entity.Code;
import com.zust.entity.Result;
import com.zust.entity.po.User;
import com.zust.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 登录
     *
     * @param user 用户对象
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User login = userService.login(user);
        if (login == null) {
            return new Result(Code.USERNAME_OR_PASSWORD_ERROR, "用户名或密码错误");
        }
        return Result.success(login.getId());

    }

    /**
     * 注册
     *
     * @param user 用户对象
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        User register = userService.register(user);
        if (register == null) {
            return new Result(Code.USER_HAS_EXISTED, "用户名已存在");
        }
        return Result.success(register.getId());
    }


}
