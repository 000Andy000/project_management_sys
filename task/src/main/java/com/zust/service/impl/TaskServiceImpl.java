package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zust.entity.po.Task;
import com.zust.mapper.TaskMapper;
import com.zust.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author Andy
 * @date 2024-1-3 003 20:49
 */
@DubboService
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    final TaskMapper taskMapper;


    @Override
    public int addTask(Task task) {
        return taskMapper.insert(task);
    }

    @Override
    public int deleteTask(String id) {
        return taskMapper.deleteById(id);
    }

    @Override
    public Task getTask(String id) {
        return taskMapper.selectById(id);
    }

    @Override
    public List<Task> getAllTask(String executorId) {
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        return taskMapper.selectList(wrapper);
    }
}
