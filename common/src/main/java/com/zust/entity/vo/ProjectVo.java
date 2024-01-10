package com.zust.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Andy
 * @date 2024-1-10 010 16:08
 */
@Data
public class ProjectVo implements Serializable {
    /**
     * 项目ID
     */
    private Integer id;

    /**
     * 创建者
     */
    private String  user;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目简介
     */
    private String description;

    /**
     * 当前项目状态;0归档、1进行中
     */
    private String status;

    /**
     * 项目创建时间
     */
    private String createdAt;

    /**
     * 项目进度
     */
    private String progress;
}
