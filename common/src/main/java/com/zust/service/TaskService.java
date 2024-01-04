package com.zust.service;

import com.zust.entity.po.Task;

import java.util.List;

/**
 * 任务;(task)表服务接口
 *
 * @date : 2024-1-3
 */
public interface TaskService {
    int addTask(Task task);

    int deleteTask(String id);

    Task getTask(String id);

    List<Task> getAllTask(String executorId);
}