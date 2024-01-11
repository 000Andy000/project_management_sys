package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目成员关系;连接表，连接成员和项目
 *
 * @date : 2024-1-4
 */
@Data
@AllArgsConstructor
@TableName("project_member")
public class ProjectMember implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 成员ID
     */
    private Integer memberId;

    /**
     * 最近一次打开时间;用于查看所有任务时的排序依据
     */
    private Date checkTime;

    /**
     * 成员在该项目中的总贡献度
     */
    private Integer score;

    /**
     * 接受状态;0 未处理 1已接受 2拒绝
     */
    private String status;

}