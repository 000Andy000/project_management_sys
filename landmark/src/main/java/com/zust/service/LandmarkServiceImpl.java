package com.zust.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zust.entity.dto.LandmarkDto;
import com.zust.entity.po.Landmark;
import com.zust.entity.po.Statistics;
import com.zust.mapper.LandmarkMapper;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Andy
 * @date 2024-1-4 004 12:07
 */
@DubboService
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {

    @DubboReference
    StatisticsService statisticsService;

    final LandmarkMapper landmarkMapper;

    @DubboReference
    final MessageService messageService;

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

    @Override
    public List<Landmark> getLandmark(Integer projectId) {
        LambdaQueryWrapper<Landmark> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Landmark::getProjectId, projectId);
        return landmarkMapper.selectList(wrapper);
    }

    @Override
    public void arriveLandmark(Landmark landmark) {
        landmark.setFinishTime(new Date());;
        landmarkMapper.updateById(landmark);
    }
}
