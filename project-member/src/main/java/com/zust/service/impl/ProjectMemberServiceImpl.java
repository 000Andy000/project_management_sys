package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.vo.ScoreHistogramData;
import com.zust.mapper.ProjectMemberMapper;
import com.zust.service.ProjectMemberService;
import com.zust.service.UserService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    final ProjectMemberMapper projectMemberMapper;

    @DubboReference
    final UserService userService;

    @Override
    public List<ProjectMember> getMemberList(String projectId, String memberName, String pageNumber, String role) {
        QueryWrapper<ProjectMember> wrapper = new QueryWrapper<>();

        wrapper.eq(StringUtils.isNotEmpty(projectId), "project_id", projectId);

        if (memberName != null) {
            wrapper.like("member_name", memberName);
        }
        if (role != null) {
            wrapper.eq("role", role);
        }
        if (pageNumber != null) {
            Page<ProjectMember> page = new Page<>(Integer.parseInt(pageNumber), 1);
            return projectMemberMapper.selectPage(page, wrapper).getRecords();
        }
        return projectMemberMapper.selectList(wrapper);
    }

    @Override
    public List<ProjectMember> getProjectMemberList(Integer projectId, Integer memberId) {
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(String.valueOf(projectId)), ProjectMember::getProjectId, projectId);
        wrapper.eq(StringUtils.isNotEmpty(String.valueOf(memberId)), ProjectMember::getMemberId, memberId);
        wrapper.orderByDesc(ProjectMember::getCheckTime);
        return projectMemberMapper.selectList(wrapper);
    }

    @Override
    public List<ScoreHistogramData> getMemberChart(String projectId) {
        QueryWrapper<ProjectMember> wrapper = new QueryWrapper<>();
        if (projectId != null) {
            wrapper.eq("project_id", projectId);
        }
        List<ProjectMember> projectMembers = projectMemberMapper.selectList(wrapper);
        List<ScoreHistogramData> scoreHistogramDataList = new ArrayList<>();
        for (ProjectMember projectMember : projectMembers) {
            String id = projectMember.getMemberId();
            // String name = userService.
            scoreHistogramDataList.add(new ScoreHistogramData("", projectMember.getScore()));
        }
        return scoreHistogramDataList;
    }
}
