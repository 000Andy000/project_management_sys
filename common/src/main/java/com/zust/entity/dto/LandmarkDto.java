package com.zust.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Andy
 * @date 2024-1-4 004 20:08
 *
 * 用于创建项目的数据传输对象
 */
@Data
public class LandmarkDto implements Serializable {
    // 里程碑名称
    private String name;
    // 里程碑简介
    private String description;
}
