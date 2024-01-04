package com.zust.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zust.entity.po.User;
import com.zust.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author Andy
 * @date 2024-1-3 003 16:14
 */
@DubboService
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final int pageSize = 10;

    final private UserMapper userMapper;

    @DubboReference
    private TaskService taskService;


    @Override
    public List<User> selectPage(int pageNum) {
        // 用page分页查询user
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getRole, "1");
        return userMapper.selectList(wrapper);

    }
}
