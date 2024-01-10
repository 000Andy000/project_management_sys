package com.zust.controller;

import com.zust.entity.Code;
import com.zust.entity.Result;
import com.zust.entity.dto.ProjectCreateDto;
import com.zust.service.ProjectService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

/**
 * @author Andy
 * @date 2024-1-4 004 11:50
 */
@EnableTransactionManagement
@RestController
@RequestMapping("/project")
public class ProjectController {
    @DubboReference
    private ProjectService projectService;


    // 按条件获取项目列表
    @GetMapping
    public Result getProjectList(@RequestParam(value = "isOwner", defaultValue = "") String isOwner,
                                 @RequestParam(value = "name", defaultValue = "") String name,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                 @RequestParam(value = "status", defaultValue = "1") String status) {
        return new Result(Code.SUCCESS, projectService.getProjectList(isOwner, name, pageNum, pageSize, status), "获取项目列表成功");
    }

    // 获取项目详情
    @GetMapping("/info")
    public Result getProjectInfo(@RequestParam(value = "id") String id) {
        return new Result(Code.SUCCESS, projectService.getProjectById(id), "获取项目详情成功");
    }

    // 创建项目
    @PostMapping
    public Result createProject(@RequestBody ProjectCreateDto projectCreateDto) {
        try {
            projectService.createProject(projectCreateDto);
            return new Result(Code.SUCCESS, "创建项目成功");
        } catch (Exception e) {
            return new Result(Code.ERROR, "创建项目失败");

        }
    }

    // 归档、取消归档项目
    @PutMapping("/archive")
    public Result archiveProject(@RequestParam(value = "id") String id,
                                 @RequestParam(value = "status") String status) {
        try {
            projectService.archiveProject(id,status);
            return new Result(Code.SUCCESS, "操作成功");
        } catch (Exception e) {
            return new Result(Code.ERROR, "操作失败");
        }
    }


}
