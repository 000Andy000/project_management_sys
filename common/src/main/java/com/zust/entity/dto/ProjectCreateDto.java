package com.zust.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Andy
 * @date 2024-1-4 004 20:06
 * 用于创建项目的数据传输对象
 */
@Data
public class ProjectCreateDto implements Serializable {
    // 项目名称
    private String name;
    // 项目简介
    private String description;
    // 里程碑列表
    private List<LandmarkDto> landmarks;
}
