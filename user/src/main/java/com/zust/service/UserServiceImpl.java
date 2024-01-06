package com.zust.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.entity.po.User;
import com.zust.mapper.UserMapper;
import io.micrometer.common.util.StringUtils;
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
    private MessageService messageService;
    @DubboReference
    private TaskService taskService;


    @Override
    public List<User> selectPage(List<Integer> userIds, String name, int pageNum, String role) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(User::getId, userIds);
        wrapper.like(StringUtils.isNotEmpty(name), User::getUsername, name);
        wrapper.eq(StringUtils.isNotEmpty(role), User::getRole, role);
        Page<User> page = new Page<>(pageNum, pageSize);
        return userMapper.selectPage(page, wrapper).getRecords();
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);

    }

    @Override
    public User getUser(String id) {
        User user = userMapper.selectById(Integer.parseInt(id));
        return user;
    }


}
