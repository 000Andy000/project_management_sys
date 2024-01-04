package com.zust.service;

import com.zust.entity.po.ProjectMember;
import com.zust.entity.vo.ScoreHistogramData;

import java.util.List;

/**
 * 项目成员关系;(project_member)表服务接口
 *
 * @date : 2024-1-3
 */
public interface ProjectMemberService {

    /**
     * 通过ID查询符合条件的数据
     *
     * @param projectId 项目ID
     * @param memberId  成员ID
     * @return projectMember列表
     */
    List<ProjectMember> getMemberList(Integer projectId,
                                      Integer memberId);

    List<ScoreHistogramData> getMemberChart(String projectId);
}