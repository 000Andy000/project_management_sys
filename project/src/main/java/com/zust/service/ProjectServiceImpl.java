package com.zust.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.entity.po.Project;
import com.zust.entity.po.ProjectMember;
import com.zust.mapper.ProjectMapper;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 * @date 2024-1-4 004 11:50
 */
@DubboService
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    final private ProjectMapper projectMapper;

    @DubboReference
    final private ProjectMemberService projectMemberService;


    @Override
    public Project getProjectById(Integer id) {
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Project::getId, id);
        return projectMapper.selectOne(queryWrapper);
    }

    @Override
    public Map<String,Object> getProjectList(String isOwner, String name, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        // TODO 获取真实的用户id
        String userId = "1";
        List<ProjectMember> projectMembers = projectMemberService.getProjectMemberList(null, userId);
        List<String> projectIds = projectMembers.stream().map(ProjectMember::getProjectId).toList();

        if (projectIds.isEmpty()) {
            Map<String,Object> map = new HashMap<>();
            map.put("totalPage", 0);
            map.put("list", null);
            return map;
        }
        // 筛选用户创建的项目（isOwner为1时）
        wrapper.eq("1".equals(isOwner),Project::getUserId, userId);

        // 筛选出用户参与的项目
        wrapper.in(Project::getId, projectIds);

        // 筛选出项目名称like name的项目
        wrapper.like(StringUtils.isNotEmpty(name),Project::getName, name);

        // 分页
        Page<Project> page = new Page<>(pageNum, pageSize);
        IPage<Project> projectPage = projectMapper.selectPage(page, wrapper);
        Map<String,Object> map = new HashMap<>();

        // 总页数
        long totalPage = projectPage.getPages();
        map.put("totalPage", totalPage);
        // 记录本身
        map.put("list", projectPage.getRecords());
        return map;

    }

}
