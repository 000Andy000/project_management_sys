package com.zust.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zust.entity.po.Landmark;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.po.Statistics;
import com.zust.entity.vo.ChartVO;
import com.zust.mapper.StatisticsMapper;
import com.zust.utils.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andy
 * @date 2024-1-5 005 0:55
 */
@DubboService
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    final StatisticsMapper statisticsMapper;
    @DubboReference
    private ProjectMemberService projectMemberService;
    @DubboReference
    private UserService userService;

    @DubboReference
    private TaskService taskService;

    @DubboReference
    private LandmarkService landmarkService;

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

    @Override
    public List<ChartVO> getUserScoreByLandmarkId(String landmarkId) {
        LambdaQueryWrapper<Statistics> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Statistics::getLandmarkId, landmarkId);
        wrapper.eq(Statistics::getType, "1");
        List<Statistics> statisticsList = statisticsMapper.selectList(wrapper);
        return ObjectConverter.listAToB(statisticsList, ChartVO.class);
    }

    @Override
    public List<ChartVO> getProjectStatistics(String projectId) {
        List<ChartVO> chartVOS = new ArrayList<>();

        // 获取已发布的任务数
        chartVOS.add(new ChartVO("已发布任务", (int) taskService.getTaskNumByProjectId(projectId, null)));
        // 获取未完成的任务数
        chartVOS.add(new ChartVO("未完成任务", (int) taskService.getTaskNumByProjectId(projectId, "0")));
        // 获取已完成的任务数
        chartVOS.add(new ChartVO("已完成任务", (int) taskService.getTaskNumByProjectId(projectId, "1")));
        // 获取今日到期任务数
        chartVOS.add(new ChartVO("今日到期任务", (int) taskService.getTaskNumByProjectId(projectId, "2")));
        // 获取逾期任务数
        chartVOS.add(new ChartVO("逾期任务", (int) taskService.getTaskNumByProjectId(projectId, "3")));
        // 获取里程碑进度信息
        chartVOS.add(new ChartVO("里程碑进度", landmarkService.getLandmarkProgress(Integer.valueOf(projectId))));

        return chartVOS;


    }
}
