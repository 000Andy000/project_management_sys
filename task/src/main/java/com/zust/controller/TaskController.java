package com.zust.controller;

import com.zust.entity.po.Task;
import com.zust.service.ListService;
import com.zust.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    final TaskService taskService;
    final ListService listService;

    @GetMapping
    public Task getTask(@RequestParam("id") String id) {
        return taskService.getTask(id);
    }

    @PostMapping
    public int addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @DeleteMapping
    public int deleteTask(@RequestParam("id") String id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/all")
    public List<Task> getAllTask(@RequestParam("executorId") String executorId) {
        return taskService.getAllTask(executorId);
    }

    @GetMapping("/list")
    public com.zust.entity.po.List getTaskList(@RequestParam("projectId") String projectId, @RequestParam("userId") String userId) {
        return listService.getTaskList(projectId, userId);
    }

    @PostMapping("/list")
    public int addTaskList(@RequestBody com.zust.entity.po.List taskList) {
        return listService.addTaskList(taskList);
    }
}
