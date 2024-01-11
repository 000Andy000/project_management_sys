package com.zust.controller;


import com.zust.entity.Result;
import com.zust.entity.dto.MemberDTO;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.vo.ProjectVo;
import com.zust.entity.vo.ScoreHistogramData;
import com.zust.service.ProjectMemberService;
import com.zust.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class ProjectMemberController {
    @DubboReference
    final ProjectMemberService projectMemberService;
    @DubboReference
    final ProjectService projectService;

    @GetMapping
    public List<MemberDTO> getMembers(@RequestParam("projectId") Integer projectId,
                                      @RequestParam("memberName") String memberName,
                                      @RequestParam("pageNumber") Integer pageNumber,
                                      @RequestParam("role") String role) {
        return projectMemberService.getMembers(projectId, memberName, pageNumber, role);
    }

    @PutMapping("/accept")
    public Result acceptInvitation(@RequestParam("projectId") Integer projectId,
                                   @RequestParam("memberId") Integer memberId) {
        int accepted = projectMemberService.acceptInvitation(projectId, memberId);
        return accepted == 1 ? Result.success(accepted) : Result.error("接受邀请失败");
    }

    @PutMapping("/refuse")
    public Result refuseInvitation(@RequestParam("projectId") Integer projectId,
                                   @RequestParam("memberId") Integer memberId) {
        int refused = projectMemberService.acceptInvitation(projectId, memberId);
        return refused == 1 ? Result.success(refused) : Result.error("拒绝邀请失败");
    }

    @GetMapping("/projects")
    public List<ProjectVo> getProjects(@RequestParam("memberId") String memberId) {
        List<ProjectMember> members = projectMemberService.getProjectMemberList(null, memberId,"1");
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
