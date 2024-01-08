package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zust.entity.dto.ListDTO;
import com.zust.entity.po.List;
import com.zust.mapper.TaskListMapper;
import com.zust.service.ListService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;

@DubboService
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    final TaskListMapper taskListMapper;

    @Override
    public com.zust.entity.po.List getTaskList(String projectId, String userId) {
        QueryWrapper<com.zust.entity.po.List> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(projectId), "project_id", projectId);
        return taskListMapper.selectOne(wrapper);
    }

    @Override
    public int addTaskList(ListDTO listDTO) {
        return taskListMapper.insert(new List(
                null,
                listDTO.getProjectId(),
                listDTO.getName(),
                new Date()
        ));
    }

    @Override
    public java.util.List<List> getAllTaskList(String projectId) {
        QueryWrapper<com.zust.entity.po.List> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(projectId), "project_id", projectId);
        return taskListMapper.selectList(wrapper);
    }
}
