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

    @GetMapping("/score")
    public Result getScore(@RequestParam(value = "landmarkId", defaultValue = "") String landmarkId) {
        return new Result(Code.SUCCESS, statisticsService.getUserScoreByLandmarkId(landmarkId), "获取分数成功");
    }
}
