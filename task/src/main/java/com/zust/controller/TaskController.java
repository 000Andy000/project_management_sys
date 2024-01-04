package com.zust.controller;

import com.zust.entity.Result;
import com.zust.entity.po.Task;
import com.zust.service.ListService;
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
    @DubboReference
    final ListService listService;

    @GetMapping
    public Result getTask(@RequestParam("id") String id) {
        return Result.success(taskService.getTask(id));
    }

    @PostMapping
    public Result addTask(@RequestBody Task task) {
        return Result.success(taskService.addTask(task));
    }

    @DeleteMapping
    public Result deleteTask(@RequestParam("id") String id) {
        return Result.success(taskService.deleteTask(id));
    }

    @GetMapping("/all")
    public Result getAllTask(@RequestParam("executorId") String executorId) {
        return Result.success(taskService.getAllTask(executorId));
    }

    @GetMapping("/list")
    public Result getTaskList(@RequestParam("projectId") String projectId, @RequestParam("userId") String userId) {
        return Result.success(listService.getTaskList(projectId, userId));
    }

    @PostMapping("/list")
    public Result addTaskList(@RequestBody com.zust.entity.po.List taskList) {
        return Result.success(listService.addTaskList(taskList));
    }
}
