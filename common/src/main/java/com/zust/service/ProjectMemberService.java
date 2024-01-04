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
    List<ProjectMember> getMemberList(String projectId,
                                      String memberName,
                                      String pageNumber,
                                      String role);

    List<ScoreHistogramData> getMemberChart(String projectId);
}