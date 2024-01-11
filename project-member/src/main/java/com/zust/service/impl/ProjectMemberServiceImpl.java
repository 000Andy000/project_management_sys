package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zust.entity.dto.MemberDTO;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.po.User;
import com.zust.entity.vo.ProjectMemberVo;
import com.zust.entity.vo.ScoreHistogramData;
import com.zust.mapper.ProjectMemberMapper;
import com.zust.service.ProjectMemberService;
import com.zust.service.ProjectService;
import com.zust.service.TaskService;
import com.zust.service.UserService;
import com.zust.utils.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.*;

@DubboService
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    final ProjectMemberMapper projectMemberMapper;

    @DubboReference
    final TaskService taskService;

    @DubboReference
    final UserService userService;

    @DubboReference
    final ProjectService projectService;

    @Override
    public List<MemberDTO> getMembers(Integer projectId, String memberName, Integer pageNumber, String role) {
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(String.valueOf(projectId)), ProjectMember::getProjectId, projectId);

        List<ProjectMember> members = projectMemberMapper.selectList(wrapper);

        Map<Integer, Integer> membersMap = new HashMap<>(); // <memberId,score>
        for (ProjectMember member : members) {
            membersMap.put(member.getMemberId(), member.getScore());
        }

        List<Integer> userIds = members.stream().map(ProjectMember::getMemberId).toList();
        List<User> users = userService.selectPage(userIds, memberName, pageNumber, role);

        List<MemberDTO> results = new ArrayList<>();
        for (User user : users) {
            String userRole = user.getRole();
            userRole = userRole.equals("0") ? "指导老师" : userRole;
            userRole = userRole.equals("1") ? "组员" : userRole;

            results.add(new MemberDTO(user.getId(),
                    user.getUsername(),
                    user.getMail(),
                    user.getPhone(),
                    userRole,
                    membersMap.get(user.getId()))
            );
        }
        return results;
    }

    @Override
    public List<ProjectMember> getProjectMemberList(String projectId, String memberId, String status) {
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(projectId), ProjectMember::getProjectId, projectId);
        wrapper.eq(StringUtils.isNotEmpty(memberId), ProjectMember::getMemberId, memberId);
        wrapper.eq(StringUtils.isNotEmpty(status), ProjectMember::getStatus, status);
        wrapper.orderByDesc(ProjectMember::getCheckTime);
        List<ProjectMember> projectMembers = projectMemberMapper.selectList(wrapper);
        return projectMembers;
    }

    @Override
    public List<ProjectMemberVo> getProjectMemberVoList(String projectId, String memberId, String status) {
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(projectId), ProjectMember::getProjectId, projectId);
        wrapper.eq(StringUtils.isNotEmpty(memberId), ProjectMember::getMemberId, memberId);
        wrapper.eq(StringUtils.isNotEmpty(status), ProjectMember::getStatus, status);

        List<ProjectMember> projectMembers = projectMemberMapper.selectList(wrapper);
        List<ProjectMemberVo> projectMemberVos = new ArrayList<>();
        for (ProjectMember projectMember : projectMembers) {
            ProjectMemberVo projectMemberVo = ObjectConverter.AToB(projectMember, ProjectMemberVo.class);
            projectMemberVo.setProjectName(projectService.getProjectById(String.valueOf(projectMember.getProjectId())).getName());
            projectMemberVo.setMemberName(userService.selectById(projectMember.getMemberId()).getUsername());
            projectMemberVos.add(projectMemberVo);
        }
        return projectMemberVos;
    }

    @Override
    public List<ScoreHistogramData> getMemberChart(String projectId) {
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(projectId), ProjectMember::getProjectId, projectId);
        List<ProjectMember> projectMembers = projectMemberMapper.selectList(wrapper);
        List<ScoreHistogramData> scoreHistogramDataList = new ArrayList<>();
        for (ProjectMember projectMember : projectMembers) {
            int id = projectMember.getMemberId();
            String name = userService.selectById(id).getUsername();
            scoreHistogramDataList.add(new ScoreHistogramData(name, projectMember.getScore()));
        }
        return scoreHistogramDataList;
    }

    @Override
    public int handleInvitation(ProjectMember projectMember) {
        LambdaQueryWrapper<ProjectMember> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(String.valueOf(projectMember.getProjectId())), ProjectMember::getProjectId, projectMember.getProjectId());
        wrapper.eq(StringUtils.isNotEmpty(String.valueOf(projectMember.getMemberId())), ProjectMember::getMemberId, projectMember.getMemberId());
        ProjectMember projectMember1 = projectMemberMapper.selectOne(wrapper);
        projectMember1.setStatus(projectMember.getStatus());
        projectMember1.setCheckTime(new Date());
        return projectMemberMapper.updateById(projectMember1);

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
        projectMember1.setScore(projectMember1.getScore() + projectMember.getScore());
        return projectMemberMapper.updateById(projectMember1);
    }
}
