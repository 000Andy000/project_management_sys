package com.zust.service;

import com.zust.entity.po.List;

/**
 * 任务列表;(list)表服务接口
 *
 * @date : 2024-1-3
 */
public interface ListService {
    List getTaskList(String projectId, String userId);

    /**
     * 获取List
     * @param id projectId
     * @return list对象
     */
    List getTaskListByProjectId(String id);

    int addTaskList(List taskList);
}
