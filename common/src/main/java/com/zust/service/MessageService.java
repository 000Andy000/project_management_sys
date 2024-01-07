package com.zust.service;

import com.zust.entity.po.Landmark;
import com.zust.entity.po.Task;

/**
 * 动态消息;(message)表服务接口
 *
 * @date : 2024-1-3
 */
public interface MessageService {
    /*新增一条动态*/
    int addMessage(String id, String id2, String content);

    /*分配任务日志*/
    int assignTask(int userId, Task task);

    /*完成任务日志*/
    int completeTask(Task task);

    /*完成里程碑日志*/
    int arriveLandMark(Landmark landmark, Integer projectId);

    int projectAssignTask(Task task);

    int projectCompleteTask(Task task);
    /*int projectArriveLandMark(Landmark landmark);*/
}
