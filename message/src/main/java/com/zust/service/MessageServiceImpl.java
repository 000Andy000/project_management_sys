package com.zust.service;

import com.zust.entity.dto.TaskDTO;
import com.zust.entity.po.*;
import com.zust.mapper.MessageMapper;
import com.zust.utils.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
@RequiredArgsConstructor
@DubboService
public class MessageServiceImpl implements MessageService {

   final MessageMapper messageMapper;

   @DubboReference
   ProjectService projectService;

   @DubboReference
   TaskService taskService;


   @DubboReference
   UserService userService;

    @Override
    public int addMessage(String id, String id2, String content) {
        return 0;
    }
    /*
    * @Param 分配者id（userId）
    * Task 任务信息
    *
    *
    * */
    @Override
    public int assignTask(int userId, Task task) {
        /*projectService.getUserIdByProjectId();*/
        Message message = new Message();
        message.setUserId(task.getExecutorId());
        message.setCreatedTime(new Date());
        User user = userService.selectById(userId);
        message.setTitle(user.getUsername() + "给你分配了任务");
        message.setContent(task.getDescription());
        return messageMapper.insert(message);
    }

    @Override
    public int completeTask(Task task) {
        Message message = new Message();
        // 获取task所属任务的拥有者id
        String projectId = taskService.getProjectIdByTaskId(String.valueOf(task.getId()));
        // 获取project对象
        Project project = projectService.getProjectById(projectId);
        // 获取project拥有者id
        Integer userId = project.getUserId();
        message.setUserId(userId);

        message.setCreatedTime(new Date());

        // 获取Exectutor的name
        String executorName = userService.selectById(task.getExecutorId()).getUsername();
        message.setTitle(executorName + "完成了任务");
        message.setContent(task.getDescription());
        return messageMapper.insert(message);
    }

    @Override
    public int arrriveLandMark(Landmark landmark) {
        Message message = new Message();
        /*获取项目所有人员*/

        message.setCreatedTime(new Date());
        message.setTitle("到达了" + landmark.getName());
        message.setContent(landmark.getDescription());

        return messageMapper.insert(message);
    }

    @Override
    public int projectAssignTask(Task task) {
        String projectIdByTaskId = taskService.getProjectIdByTaskId(String.valueOf(task.getId()));
        String userId = projectService.getUserIdByProjectId(projectIdByTaskId);
        Message message = new Message();
        message.setProjectId(Integer.valueOf(projectIdByTaskId));
        message.setCreatedTime(new Date());
        User user = userService.selectById(Integer.parseInt(userId));
        User user1 = userService.selectById(task.getExecutorId());
        message.setTitle( user.getUsername()+ "分配了任务给"+user1.getUsername());
        message.setContent(task.getDescription());
        return messageMapper.insert(message);
    }

    @Override
    public int projectCompleteTask(Task task) {
        Message message = new Message();
        // 获取task所属任务的拥有者id
        String projectId = taskService.getProjectIdByTaskId(String.valueOf(task.getId()));
        // 获取project对象
        Project project = projectService.getProjectById(projectId);
        // 获取project拥有者id
       message.setProjectId(Integer.valueOf(projectId));

        message.setCreatedTime(new Date());

        // 获取Exectutor的name
        String executorName = userService.selectById(task.getExecutorId()).getUsername();
        message.setTitle(executorName + "完成了任务");
        message.setContent(task.getDescription());
        return messageMapper.insert(message);
    }

    @Override
    public int projectArriveLandMark(Landmark landmark) {
        Message message = new Message();
        message.setProjectId(landmark.getProjectId());
        message.setCreatedTime(new Date());
        message.setTitle("到达了" + landmark.getName());
        message.setContent(landmark.getDescription());

        return messageMapper.insert(message);
    }


}
