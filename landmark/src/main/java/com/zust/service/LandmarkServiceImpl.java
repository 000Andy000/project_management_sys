package com.zust.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zust.entity.dto.LandmarkDto;
import com.zust.entity.po.Landmark;
import com.zust.mapper.LandmarkMapper;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
import java.util.List;

/**
 * @author Andy
 * @date 2024-1-4 004 12:07
 */
@DubboService
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {

    final LandmarkMapper landmarkMapper;
    @DubboReference
    final MessageService messageService;
    @DubboReference
    StatisticsService statisticsService;

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
    public String getLandmarkProgress(Integer projectId) {
        LambdaQueryWrapper<Landmark> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Landmark::getProjectId, projectId);
        long count = landmarkMapper.selectCount(wrapper);
        wrapper.eq(Landmark::getFinishTime, null);
        long unfinishedCount = landmarkMapper.selectCount(wrapper);
        return unfinishedCount + "/" + count;
    }

    @Override
    public void arriveLandmark(Landmark landmark) {
        landmark.setFinishTime(new Date());
        Landmark landmark1 = landmarkMapper.selectById(landmark.getId());
        messageService.arriveLandMark(landmark1, landmark1.getProjectId());
        landmarkMapper.updateById(landmark);
        statisticsService.insertStatistics(landmark1);
    }
}
