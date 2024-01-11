package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zust.entity.dto.TaskDTO;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.po.Task;
import com.zust.mapper.TaskMapper;
import com.zust.service.ListService;
import com.zust.service.MessageService;
import com.zust.service.ProjectMemberService;
import com.zust.service.TaskService;
import com.zust.utils.DateUtils;
import com.zust.utils.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.Date;
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
    @DubboReference
    final ProjectMemberService projectMemberService;

    @DubboReference
    final ListService listService;


    @Override
    public int addTask(TaskDTO taskDTO) {
        Task task = ObjectConverter.AToB(taskDTO, Task.class);
        task.setEndTime(DateUtils.StringToDate(taskDTO.getEndTime()));
        /*被分配任务者日志提示*/
        messageService.assignTask(taskDTO.getExecutorId(), task);
        /*项目日志*/
        int i = taskMapper.insert(task);
        messageService.projectAssignTask(task);
        return i;
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
    public List<TaskDTO> getAllTask(String executorId, String status) {
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(executorId != null && !executorId.isEmpty(), Task::getExecutorId, executorId);
        wrapper.eq(status != null && !status.isEmpty(), Task::getStatus, status);
        List<Task> tasks = taskMapper.selectList(wrapper);
        List<TaskDTO> results = new ArrayList<>();
        for (Task task : tasks) {
            results.add(new TaskDTO(
                    task.getListId(),
                    task.getName(),
                    task.getDescription(),
                    task.getEndTime().toString(),
                    task.getExecutorId(),
                    task.getStatus()
            ));
        }
        return results;
    }

    @Override
    public int finishTask(String id, String status) {
        // 任务完成，更新状态
        Task newTask = taskMapper.selectById(id);
        newTask.setStatus(status);
        newTask.setEndTime(new Date());
        int i = taskMapper.updateById(newTask);

        // 任务完成，记录消息
        messageService.completeTask(newTask);
        messageService.projectCompleteTask(newTask);
        // 任务完成，给项目成员加分
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectId(Integer.parseInt(id));
        projectMember.setMemberId(newTask.getExecutorId());
        projectMember.setScore(newTask.getScore());
        projectMemberService.addScore(projectMember);

        return i;
    }

    @Override
    public String getProjectIdByTaskId(String taskId) {
        /*通过taskid查到任务列表id，再查任务列表是属于哪个项目的*/
        Task task = taskMapper.selectById(taskId);
        /*通过任务列表id查到项目id*/
        return listService.getTaskList(String.valueOf(task.getListId()), null).getProjectId();
    }

    @Override
    public long getTaskNumByProjectId(String projectId, String status) {
        List<com.zust.entity.po.List> allTaskList = listService.getAllTaskList(projectId);
        // 把所有任务列表的id放到一个list里
        List<Integer> listIds = new ArrayList<>();
        listIds.add(-1);
        for (com.zust.entity.po.List list : allTaskList) {
            listIds.add(list.getId());
        }
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Task::getListId, listIds);
        wrapper.like(StringUtils.isNotEmpty(status), Task::getStatus, status);
        return taskMapper.selectCount(wrapper);

    }
}
