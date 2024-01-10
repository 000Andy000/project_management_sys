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

    /**
     * 根据项目id获取任务列表
     *
     * @param projectId 项目id
     * @param status    任务状态 0未完成 1已完成 2今日到期任务 3逾期任务 null全部
     * @return 任务列表
     */
    long getTaskNumByProjectId(String projectId, String status);
}
