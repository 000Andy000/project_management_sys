package com.zust.controller;

import com.zust.service.LandmarkService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

        @GetMapping("/test")
        public String test(){
                return " landmarkService.test()";
        }
}
