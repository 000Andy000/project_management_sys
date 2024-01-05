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
     * 获取第pageNum页的数据，
     *
     * @param pageNum 页码
     */
    List<User> selectPage(int pageNum);


    /**
     * 按id查询
     * @param id 用户id
     * @return 用户对象
     *
     */
    User selectById(int id);
}
