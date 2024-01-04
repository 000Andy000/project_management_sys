package com.zust.service;

import com.zust.mapper.StatisticsMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author Andy
 * @date 2024-1-5 005 0:55
 */
@DubboService
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    @DubboReference
    private ProjectMemberService projectMemberService;

    final StatisticsMapper statisticsMapper;


}
