package com.zust.service;

import com.zust.entity.po.List;

/**
 * 任务列表;(list)表服务接口
 *
 * @date : 2024-1-3
 */
public interface ListService {
    /**
     * 获取List
     *
     * @param projectId
     * @param userId
     * @return list对象
     */
    List getTaskList(String projectId, String userId);

    int addTaskList(List taskList);
}
