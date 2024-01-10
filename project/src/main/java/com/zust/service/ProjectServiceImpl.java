package com.zust.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.entity.PageData;
import com.zust.entity.dto.ProjectCreateDto;
import com.zust.entity.po.Project;
import com.zust.entity.po.ProjectMember;
import com.zust.entity.vo.ProjectVo;
import com.zust.mapper.ProjectMapper;
import com.zust.utils.DateUtils;
import com.zust.utils.ObjectConverter;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Andy
 * @date 2024-1-4 004 11:50
 */
@DubboService
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    final private ProjectMapper projectMapper;

    @DubboReference
    final private ProjectMemberService projectMemberService;

    @DubboReference
    private LandmarkService landmarkService;

    @DubboReference
    private UserService userService;


    @Override
    public ProjectVo getProjectById(String id) {
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Project::getId, id);
        Project project = projectMapper.selectOne(queryWrapper);
        ProjectVo projectVo = ObjectConverter.AToB(project, ProjectVo.class);
        // 设置项目创建者
        projectVo.setUser(userService.selectById(project.getUserId()).getUsername());
        // 设置项目进度
        String landmarkProgress = landmarkService.getLandmarkProgress(project.getId());
        String[] parts = landmarkProgress.split("/");
        projectVo.setProgress(String.valueOf(Double.parseDouble(parts[0])*100/Double.parseDouble(parts[1])));
        // 设置时间
        projectVo.setCreatedAt(DateUtils.DateToString(project.getCreatedAt()));
        return projectVo;
    }

    @Override
    public PageData getProjectList(String isOwner, String name, Integer pageNum, Integer pageSize, String status) {

        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        // TODO 获取真实的用户id
        String userId = "1";
        List<ProjectMember> projectMembers = projectMemberService.getProjectMemberList(null, userId);
        List<Integer> projectIds = projectMembers.stream().map(ProjectMember::getProjectId).toList();

        if (projectIds.isEmpty()) {
            return new PageData(0L, null);
        }
        // 筛选用户创建的项目（isOwner为1时）
        wrapper.eq("1".equals(isOwner), Project::getUserId, userId);
        wrapper.ne("0".equals(isOwner), Project::getUserId, userId);

        // 筛选出用户参与的项目
        wrapper.in(Project::getId, projectIds);

        // 筛选出项目名称like name的项目
        wrapper.like(StringUtils.isNotEmpty(name), Project::getName, name);

        // 筛选出项目状态为status的项目
        wrapper.eq(StringUtils.isNotEmpty(status), Project::getStatus, status);

        // 分页
        Page<Project> page = new Page<>(pageNum, pageSize);
        IPage<Project> projectIPage = projectMapper.selectPage(page, wrapper);
        List<Project> projects = projectIPage.getRecords();
        List<ProjectVo> projectVos = new ArrayList<>();
        for (Project project : projects) {
            ProjectVo projectVo = ObjectConverter.AToB(project, ProjectVo.class);
            // 设置项目创建者
            projectVo.setUser(userService.selectById(project.getUserId()).getUsername());
            // 设置项目进度
            String landmarkProgress = landmarkService.getLandmarkProgress(project.getId());
            String[] parts = landmarkProgress.split("/");
            projectVo.setProgress(String.valueOf(Double.parseDouble(parts[0])*100/Double.parseDouble(parts[1])));
            projectVos.add(projectVo);
            // 设置时间
            projectVo.setCreatedAt(DateUtils.DateToString(project.getCreatedAt()));
        }

        return new PageData(projectIPage.getPages(), projectVos );

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProject(ProjectCreateDto projectCreateDto) {
        try {

            // 插入项目相关信息
            // TODO 获取真实的用户id
            String userId = "1";
            Project project = new Project();
            project.setName(projectCreateDto.getName());
            project.setDescription(projectCreateDto.getDescription());
            project.setUserId(Integer.valueOf(userId));
            project.setCreatedAt(new Date());
            projectMapper.insert(project);


            // 插入里程碑相关信息
            // 获取项目id
            Integer projectId = project.getId();
            // 插入里程碑信息
            landmarkService.insertLandmark(projectCreateDto.getLandmarks(), String.valueOf(projectId));


            // 插入项目成员相关信息
            ProjectMember projectMember = new ProjectMember();
            projectMember.setProjectId(projectId);
            projectMember.setMemberId(Integer.valueOf(userId));
            projectMember.setCheckTime(new Date());
            projectMember.setStatus("1");
            projectMemberService.createProjectMember(projectMember);


        } catch (RuntimeException e) {
            throw new RuntimeException("创建项目失败");
        }

    }

    @Override
    public String getUserIdByProjectId(String projectId) {
        Project project = projectMapper.selectById(projectId);
        return String.valueOf(project.getUserId());
    }

    @Override
    public void archiveProject(String id, String status) {
        Project project = projectMapper.selectById(id);
        project.setStatus(status);
        projectMapper.updateById(project);
    }


}
