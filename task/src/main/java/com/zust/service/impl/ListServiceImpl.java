package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zust.mapper.TaskListMapper;
import com.zust.service.ListService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    final TaskListMapper taskListMapper;

    @Override
    public com.zust.entity.po.List getTaskList(String projectId, String userId) {
        QueryWrapper<com.zust.entity.po.List> wrapper = new QueryWrapper<>();
        if (projectId != null) {
            wrapper.eq("project_id", projectId);
        }
        if (userId != null) {
            wrapper.eq("user_id", userId);
        }
        return taskListMapper.selectOne(wrapper);
    }

    @Override
    public int addTaskList(com.zust.entity.po.List taskList) {
        return taskListMapper.insert(taskList);
    }
}
