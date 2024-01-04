package com.zust.controller;

import com.zust.service.ProjectService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 * @date 2024-1-4 004 11:50
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    @DubboReference
    private ProjectService projectService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
