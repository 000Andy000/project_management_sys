package com.zust.controller;

import com.zust.entity.Result;
import com.zust.service.LandmarkService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 * @date 2024-1-4 004 12:06
 */
@RestController
@RequestMapping("/landmark")
public class LandmarkController {
    @DubboReference
    private LandmarkService landmarkService;

    /**
     * 单个项目的里程碑信息
     *
     */
    @GetMapping
    public Result getLandmark(@RequestParam(value = "projectId", defaultValue = "") Integer projectId) {

        return new Result(200, landmarkService.getLandmark(projectId), "查询里程碑信息成功");

    }
}
