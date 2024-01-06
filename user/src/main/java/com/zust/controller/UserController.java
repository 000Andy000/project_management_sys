package com.zust.controller;

import com.zust.entity.Code;
import com.zust.entity.Result;
import com.zust.entity.po.User;
import com.zust.service.TaskService;
import com.zust.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
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
    public Result getUser(@RequestParam("id") String id){
        return new Result(Code.SUCCESS,userService.getUser(id),"用户加载成功");
    }

}
