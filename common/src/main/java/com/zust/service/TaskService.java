package com.zust.service;

import com.zust.entity.dto.TaskDTO;
import com.zust.entity.po.Task;

import java.util.List;

/**
 * 任务;(task)表服务接口
 *
 * @date : 2024-1-3
 */
public interface TaskService {
    int addTask(TaskDTO taskDTO);

    int deleteTask(String id);

    int finishTask(String id, String status);

    Task getTask(String id);

    List<TaskDTO> getAllTask(String executorId);

    String getProjectIdByTaskId(String taskId);
}
