package com.zust.controller;


import com.zust.entity.po.ProjectMember;
import com.zust.entity.vo.ScoreHistogramData;
import com.zust.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class ProjectMemberController {
    @DubboReference
    final ProjectMemberService projectMemberService;


    @GetMapping
    public List<ProjectMember> getMemberList(@RequestParam("projectId") String projectId,
                                             @RequestParam("memberName") String memberName,
                                             @RequestParam("pageNumber") String pageNumber,
                                             @RequestParam("role") String role) {
        return projectMemberService.getMemberList(projectId, memberName, pageNumber, role);
    }

    @GetMapping("/chart")
    public List<ScoreHistogramData> getMemberChart(@RequestParam("projectId") String projectId) {
        return projectMemberService.getMemberChart(projectId);
    }
}
