package com.zust.service;

import com.zust.entity.po.ProjectMember;
import com.zust.entity.po.User;
import com.zust.entity.vo.ScoreHistogramData;

import java.util.List;

/**
 * 项目成员关系;(project_member)表服务接口
 *
 * @date : 2024-1-3
 */
public interface ProjectMemberService {

    /**
     * 获取项目人员列表
     *
     * @param projectId
     * @param memberName
     * @param pageNumber
     * @param role       身份
     * @return List of ProjectMember
     */
    List<User> getMembers(String projectId, String memberName, String pageNumber, String role);

    /**
     * 通过ID查询符合条件的数据
     *
     * @param projectId 项目ID
     * @param memberId  成员ID
     * @return projectMember列表
     */
    List<ProjectMember> getProjectMemberList(String projectId, String memberId);

    /**
     * 获取贡献柱状图
     *
     * @param projectId
     * @return List of ScoreHistogramData
     */
    List<ScoreHistogramData> getMemberChart(String projectId);


    /**
     * 创建项目成员关系
     *
     * @param projectMember
     * @return int 1:成功 0:失败
     */
    int createProjectMember(ProjectMember projectMember);

    /*
    * 增加贡献度
    */
    int addScore(ProjectMember projectMember);
}
