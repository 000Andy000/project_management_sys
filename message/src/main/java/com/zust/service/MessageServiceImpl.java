package com.zust.service;

import com.zust.entity.po.Message;
import com.zust.entity.po.Task;
import com.zust.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
@RequiredArgsConstructor
@DubboService
public class MessageServiceImpl implements MessageService {

   final MessageMapper messageMapper;

    @Override
    public int addMessage(String id, String id2, String content) {
        return 0;
    }

    @Override
    public int assignTask(int userId, Task task) {

        Message message = new Message();
        message.setUserId(task.getExecutorId());
        message.setCreatedTime(new Date());
        message.setTitle(userId + "给你分配了任务");
        message.setContent(task.getDescription());
        return messageMapper.insert(message);
    }


}
