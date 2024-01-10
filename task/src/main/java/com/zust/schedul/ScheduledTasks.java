package com.zust.schedul;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zust.entity.po.Task;
import com.zust.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Andy
 * @date 2024-1-10 010 14:13
 */
@EnableScheduling // 启用定时任务
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    final TaskMapper taskMapper;


    // 每天0点执行
    @Scheduled(cron = "0 0 0 * * *")
    public void sayHello() {
        // 将所有逾期的task的状态改为3
        LambdaUpdateWrapper<Task> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Task::getStatus, 0).or().eq(Task::getStatus, 2);
        // 时间小于当前时间
        wrapper.lt(Task::getEndTime, System.currentTimeMillis());
        Task task = new Task();
        task.setStatus("03");
        taskMapper.update(task, wrapper);

        // 将所有今天结束的task的状态改为2
        LambdaUpdateWrapper<Task> wrapper2 = new LambdaUpdateWrapper<>();
        wrapper2.eq(Task::getStatus, 0);
        // 时间大于当前时间，并且小于明天0点
        wrapper2.lt(Task::getEndTime, System.currentTimeMillis());
        wrapper2.gt(Task::getEndTime, System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        Task task2 = new Task();
        task2.setStatus("02");
        taskMapper.update(task2, wrapper2);
    }

}
