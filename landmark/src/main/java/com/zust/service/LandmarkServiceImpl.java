package com.zust.service;

import com.zust.entity.dto.LandmarkDto;
import com.zust.entity.po.Landmark;
import com.zust.mapper.LandmarkMapper;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author Andy
 * @date 2024-1-4 004 12:07
 */
@DubboService
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {

    final LandmarkMapper landmarkMapper;

    @Override
    public int insertLandmark(List<LandmarkDto> landmarkDtos, String projectId) {
        int sum = 0;
        for (LandmarkDto landmarkDto : landmarkDtos) {
            Landmark landmark = new Landmark();
            landmark.setProjectId(Integer.valueOf(projectId));
            landmark.setName(landmarkDto.getName());
            landmark.setDescription(landmarkDto.getDescription());
            landmarkMapper.insert(landmark);
            sum++;
        }
        return sum;
    }
}
