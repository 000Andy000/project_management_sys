package com.zust.controller;


import com.zust.entity.Result;
import com.zust.entity.dto.MemberDTO;
import com.zust.entity.po.Project;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.po.User;
import com.zust.entity.vo.InvitationVO;
import com.zust.entity.vo.ProjectVo;
import com.zust.entity.vo.ScoreHistogramData;
import com.zust.service.ProjectMemberService;
import com.zust.service.ProjectService;
import com.zust.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class ProjectMemberController {
    @DubboReference
    final ProjectMemberService projectMemberService;
    @DubboReference
    final ProjectService projectService;
    @DubboReference
    final UserService userService;

    @GetMapping
    public List<MemberDTO> getMembers(@RequestParam("projectId") Integer projectId,
                                      @RequestParam("memberName") String memberName,
                                      @RequestParam("pageNumber") Integer pageNumber,
                                      @RequestParam("role") String role) {
        return projectMemberService.getMembers(projectId, memberName, pageNumber, role);
    }

    @PutMapping
    public Result acceptInvitation(@RequestBody ProjectMember projectMember) {
        int accepted = projectMemberService.handleInvitation(projectMember);
        return accepted == 1 ? Result.success(accepted) : Result.error("操作失败");
    }

    @GetMapping("/invitations")
    public Result getInvitations(@RequestParam("projectId") String projectId, @RequestParam("memberId") String memberId) {
        List<ProjectMember> members = projectMemberService.getProjectMemberList(projectId, memberId, "0");
        List<InvitationVO> results = new ArrayList<>();
        for (ProjectMember member : members) {
            Project project = projectService.getProjectById(projectId);
            User inviter = userService.selectById(project.getUserId());
            results.add(new InvitationVO(inviter.getUsername(), project.getName()));
        }
        return Result.success(results);
    }

    @PostMapping
    public Result addInvitation(@RequestBody ProjectMember projectMember) {
        projectMember.setCheckTime(new Date());
        int created = projectMemberService.createProjectMember(projectMember);
        return created == 1 ? Result.success(created) : Result.error("创建失败");
    }

    @GetMapping("/projects")
    public List<ProjectVo> getProjects(@RequestParam("memberId") String memberId) {
        List<ProjectMember> members = projectMemberService.getProjectMemberList(null, memberId, "1");
        List<ProjectVo> projects = new ArrayList<>();
        for (ProjectMember member : members) {
            ProjectVo projectVO = projectService.getProjectVoById(String.valueOf(member.getProjectId()));
            if (projectVO != null) {
                projects.add(projectVO);
            }
        }
        return projects;
    }

    @GetMapping("/chart")
    public List<ScoreHistogramData> getMemberChart(@RequestParam("projectId") String projectId) {
        return projectMemberService.getMemberChart(projectId);
    }
}
