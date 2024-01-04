package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zust.entity.dto.TaskDTO;
import com.zust.entity.po.Task;
import com.zust.mapper.TaskMapper;
import com.zust.service.MessageService;
import com.zust.service.TaskService;
import com.zust.utils.DateUtils;
import com.zust.utils.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
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

    @DubboReference
    final MessageService messageService;
    @Override
    public int addTask(TaskDTO taskDTO) {

        Task task = ObjectConverter.AToB(taskDTO, Task.class);
        task.setEndTime(DateUtils.StringToDate(taskDTO.getEndTime()));
        messageService.assignTask(1,task);
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
