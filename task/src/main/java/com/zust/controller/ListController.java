package com.zust.controller;

import com.zust.entity.Result;
import com.zust.entity.dto.ListDTO;
import com.zust.service.ListService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task/list")
@RequiredArgsConstructor
public class ListController {
    @DubboReference
    final ListService listService;

    @GetMapping
    public Result getTaskList(@RequestParam("projectId") String projectId, @RequestParam("userId") String userId) {
        return Result.success(listService.getTaskList(projectId, userId));
    }

    @GetMapping("/all")
    public Result getAllTaskList(@RequestParam("projectId") String projectId) {
        return Result.success(listService.getAllTaskList(projectId));
    }

    @PostMapping
    public Result addTaskList(@RequestBody ListDTO listDTO) {
        return Result.success(listService.addTaskList(listDTO));
    }
}
