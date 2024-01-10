package com.zust.controller;


import com.zust.entity.dto.MemberDTO;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.vo.ProjectVo;
import com.zust.entity.vo.ScoreHistogramData;
import com.zust.service.ProjectMemberService;
import com.zust.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/projects")
    public List<ProjectVo> getProjects(@RequestParam("memberId") String memberId) {
        List<ProjectMember> members = projectMemberService.getProjectMemberList(null, memberId);
        List<ProjectVo> projects = new ArrayList<>();
        for (ProjectMember member : members) {
            ProjectVo projectVO = projectService.getProjectById(String.valueOf(member.getProjectId()));
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
