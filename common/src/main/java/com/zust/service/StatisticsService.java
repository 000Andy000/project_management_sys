package com.zust.service;

import com.zust.entity.po.Landmark;
import com.zust.entity.po.Statistics;
import com.zust.entity.vo.ChartVO;

import java.util.List;

/**
 * 统计数据表;(statistics)表服务接口
 * @date : 2024-1-5
 */
public interface StatisticsService{
    /**
     * 到达里程碑时，插入统计数据
     */
    int insertStatistics(Landmark landmark);

    /**
     * 获取某里程碑时用户的贡献度
     */
    List<ChartVO> getUserScoreByLandmarkId(String landmarkId);
}