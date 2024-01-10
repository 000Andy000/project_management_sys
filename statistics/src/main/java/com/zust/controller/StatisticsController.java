package com.zust.controller;

import com.zust.entity.Code;
import com.zust.entity.Result;
import com.zust.service.StatisticsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 * @date 2024-1-4 004 22:40
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @DubboReference
    private StatisticsService statisticsService;

    // 获取用户分数
    @GetMapping("/score")
    public Result getScore(@RequestParam(value = "landmarkId", defaultValue = "") String landmarkId) {
        return new Result(Code.SUCCESS, statisticsService.getUserScoreByLandmarkId(landmarkId), "获取分数成功");
    }

    // 获取某项目的统计信息
    @GetMapping("/project")
    public Result getProjectStatistics(@RequestParam(value = "projectId", defaultValue = "") String projectId) {
        return new Result(Code.SUCCESS, statisticsService.getProjectStatistics(projectId), "获取项目统计信息成功");
    }


}
