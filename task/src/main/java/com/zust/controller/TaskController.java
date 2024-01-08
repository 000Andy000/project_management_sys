package com.zust.controller;

import com.zust.entity.Result;
import com.zust.entity.dto.TaskDTO;
import com.zust.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    @DubboReference
    final TaskService taskService;

    @GetMapping
    public Result getTask(@RequestParam("id") String id) {
        return Result.success(taskService.getTask(id));
    }

    @PostMapping
    public Result addTask(@RequestBody TaskDTO taskDTO) {
        return Result.success(taskService.addTask(taskDTO));
    }

    @DeleteMapping
    public Result deleteTask(@RequestParam("id") String id) {
        return Result.success(taskService.deleteTask(id));
    }

    @PutMapping
    public Result finishTask(@RequestParam("id") String id, @RequestParam("status") String status) {
        return Result.success(taskService.finishTask(id, status));
    }

    @GetMapping("/all")
    public Result getAllTask(@RequestParam("executorId") String executorId) {
        return Result.success(taskService.getAllTask(executorId));
    }
}
