package com.zust.controller;

import com.zust.entity.Code;
import com.zust.entity.Result;
import com.zust.service.MessageService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    @DubboReference
     MessageService messageService;

    @GetMapping
    public Result getMessage(@RequestParam("userid") String userid,
                             @RequestParam("projectid")  String projectid,
                             @RequestParam(value = "pageNum",defaultValue = "1")  int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")  int pageSize) {
        return new Result(Code.SUCCESS, messageService.getMessage(userid, projectid, pageNum, pageSize), "查询动态成功")  ;
    }
}
