package com.zust.service;

import com.zust.entity.po.Project;

import java.util.Map;

/**
 * 项目;(project)表服务接口
 *
 * @date : 2024-1-3
 */
public interface ProjectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return project对象
     */
    Project getProjectById(Integer id);

    /**
     * 按条件获取项目列表
     *
     * @param isOwner  是否为项目创建者
     * @param name     项目名称
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 项目列表
     */
    Map<String,Object> getProjectList(String isOwner, String name, Integer pageNum, Integer pageSize);
}