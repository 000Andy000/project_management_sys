package com.zust.controller;

import com.zust.entity.Code;
import com.zust.entity.Result;
import com.zust.service.ProjectService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    // 按条件获取项目列表
    @GetMapping
    public Result getProjectList(@RequestParam(value = "isOwner", defaultValue = "0") String isOwner,
                                 @RequestParam(value = "name", defaultValue = "") String name,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return new Result(Code.SUCCESS, projectService.getProjectList(isOwner, name, pageNum, pageSize), "获取项目列表成功");
    }

    // 获取项目详情
    @GetMapping("/info")
    public Result getProjectInfo(@RequestParam(value = "id") String id) {
        return new Result(Code.SUCCESS, projectService.getProjectById(id), "获取项目详情成功");
    }




}
