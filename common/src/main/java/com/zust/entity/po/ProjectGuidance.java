package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目指导关系;
 *
 * @date : 2024-1-3
 */
@Data
@TableName("project_guidance")
public class ProjectGuidance implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 被指导的项目id
     */
    private Integer projectId;

    /**
     * 指导老师id
     */
    private Integer userId;

    /**
     * 状态（是否归档，0否 1是）
     */
    private String status;

}