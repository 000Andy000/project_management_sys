package com.zust.service;

import com.zust.entity.po.Landmark;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.po.Statistics;
import com.zust.mapper.StatisticsMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
import java.util.List;

/**
 * @author Andy
 * @date 2024-1-5 005 0:55
 */
@DubboService
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    @DubboReference
    private ProjectMemberService projectMemberService;

    @DubboReference
    private UserService userService;

    final StatisticsMapper statisticsMapper;


    @Override
    public int insertStatistics(Landmark landmark) {
        int sum = 0;
        // 1.获取项目id
        Integer projectId = landmark.getProjectId();
        // 2.获取相关的projectMember
        List<ProjectMember> projectMemberList = projectMemberService.getProjectMemberList(String.valueOf(projectId), null);
        for (ProjectMember projectMember : projectMemberList) {
            // 3.获取相关的userId
            Integer userId = projectMember.getMemberId();
            // 4.获取相关的landmarkId
            Integer landmarkId = landmark.getId();
            // 5.设置相关的type
            String type = "1";
            // 6.获取用户的score
            Integer score = projectMember.getScore();
            // 7.获取用户的name
            String name = userService.selectById(userId).getUsername();


            // 8.插入statistics
            Statistics statistics = new Statistics();
            statistics.setUserId(userId);
            statistics.setProjectId(projectId);
            statistics.setLandmarkId(landmarkId);
            statistics.setType(type);
            statistics.setScore(score);
            statistics.setName(name);
            statistics.setCreatedTime(landmark.getFinishTime());
            statisticsMapper.insert(statistics);
            sum++;

        }
        return sum;
    }
}
