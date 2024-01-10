package com.zust.service;

import com.zust.entity.PageData;
import com.zust.entity.dto.ProjectCreateDto;
import com.zust.entity.vo.ProjectVo;

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
    ProjectVo getProjectById(String id);

    /**
     * 按条件获取项目列表
     *
     * @param isOwner  是否为项目创建者
     * @param name     项目名称
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param status   项目状态
     * @return 项目列表
     */
    PageData getProjectList(String isOwner, String name, Integer pageNum, Integer pageSize, String status);

    /**
     * 创建项目
     *
     * @param projectCreateDto 项目创建dto
     */
    void createProject(ProjectCreateDto projectCreateDto);


    /**
     * 根据项目id获取组长的userId
     *
     * @param projectId 项目id
     */
    String getUserIdByProjectId(String projectId);

    void archiveProject(String id, String status);
}
