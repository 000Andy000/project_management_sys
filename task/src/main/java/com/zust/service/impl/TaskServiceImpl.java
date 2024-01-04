package com.zust.service.impl;

import com.zust.service.TaskService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author Andy
 * @date 2024-1-3 003 20:49
 */
@DubboService
public class TaskServiceImpl implements TaskService {
    @Override
    public String test() {
        return "test";
    }
}
