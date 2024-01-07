package com.zust.service;

import com.zust.entity.po.User;

import java.util.List;

/**
 * 用户;(user)表服务接口
 *
 * @date : 2024-1-3
 */
public interface UserService {


    /**
     * 获取第pageNum页的数据，也可以指定角色
     *
     * @param name    用户名
     * @param pageNum 页码
     * @param role    角色
     */
    List<User> selectPage(List<Integer> userIds, String name, Integer pageNum, String role);


    /**
     * 按id查询
     *
     * @param id 用户id
     * @return 用户对象
     */
    User selectById(int id);

    int updateUser(User user);
}
