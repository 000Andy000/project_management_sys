package com.zust.controller;

import com.zust.entity.Result;
import com.zust.entity.po.Landmark;
import com.zust.service.LandmarkService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 更新里程碑
     */
    @PutMapping
    public Result arriveLandmark(@RequestBody Landmark landmark) {
        landmarkService.arriveLandmark(landmark);
        return new Result(200, "更新里程碑成功");
    }

}
