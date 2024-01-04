package com.zust.service;

import com.zust.entity.dto.LandmarkDto;
import com.zust.entity.po.Landmark;

import java.util.List;

/**
 * 里程碑;(landmark)表服务接口
 *
 * @author AD
 * @date : 2024-1-3
 */
public interface LandmarkService {

    /**
     * 插入一条新的里程碑信息
     * @param landmarkDtos 里程碑信息列表
     * @param projectId    项目id
     * @return 插入的里程碑条数
     */
    int insertLandmark(List<LandmarkDto> landmarkDtos, String projectId);


    List<Landmark> getLandmark(Integer projectId);

    void arriveLandmark(Landmark landmark);
}