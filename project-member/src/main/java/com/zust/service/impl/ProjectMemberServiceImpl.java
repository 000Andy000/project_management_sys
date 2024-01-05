package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.po.Task;
import com.zust.entity.po.User;
import com.zust.entity.vo.ScoreHistogramData;
import com.zust.mapper.ProjectMemberMapper;
import com.zust.service.ProjectMemberService;
import com.zust.service.TaskService;
import com.zust.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    final ProjectMemberMapper projectMemberMapper;

    /*@DubboReference
    final UserService userService;*/
    @DubboReference
    final TaskService taskService;
    @Override
    public List<ProjectMember> getMembers(String projectId, String memberName, String pageNumber, String role) {
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(projectId), ProjectMember::getProjectId, projectId);
        if (pageNumber != null) {
            Page<ProjectMember> page = new Page<>(Integer.parseInt(pageNumber), 1);
            return projectMemberMapper.selectPage(page, wrapper).getRecords();
        }
        return projectMemberMapper.selectList(wrapper);
    }

    @Override
    public List<ProjectMember> getProjectMemberList(String projectId, String memberId) {
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(projectId), ProjectMember::getProjectId, projectId);
        wrapper.eq(StringUtils.isNotEmpty(memberId), ProjectMember::getMemberId, memberId);
        wrapper.orderByDesc(ProjectMember::getCheckTime);
        List<ProjectMember> projectMembers = projectMemberMapper.selectList(wrapper);
        return projectMembers;
    }

    @Override
    public List<ScoreHistogramData> getMemberChart(String projectId) {
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(projectId), ProjectMember::getProjectId, projectId);
        List<ProjectMember> projectMembers = projectMemberMapper.selectList(wrapper);
        List<ScoreHistogramData> scoreHistogramDataList = new ArrayList<>();
        for (ProjectMember projectMember : projectMembers) {
            int id = projectMember.getMemberId();
            // String name = userService.
            scoreHistogramDataList.add(new ScoreHistogramData("", projectMember.getScore()));
        }
        return scoreHistogramDataList;
    }

    @Override
    public int createProjectMember(ProjectMember projectMember) {
        return projectMemberMapper.insert(projectMember);
    }


    /*根据projectID和memberId修改score字段
     * */
    @Override
    public int addScore(ProjectMember projectMember) {
        /*根据projectID和memberId查询出score字段
        * */
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(String.valueOf(projectMember.getProjectId())), ProjectMember::getProjectId, projectMember.getProjectId());
        wrapper.eq(StringUtils.isNotEmpty(String.valueOf(projectMember.getMemberId())), ProjectMember::getMemberId, projectMember.getMemberId());
        ProjectMember projectMember1 = projectMemberMapper.selectOne(wrapper);
        /*将任务的score和原本的score相加*/
        projectMember1.setScore(projectMember1.getScore()+projectMember.getScore());
        return projectMemberMapper.updateById(projectMember1);




    }
}
