package com.zust.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Andy
 * @date 2024-1-11 011 21:33
 */
@Data
public class ProjectMemberVo implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 项目
     */
    private String projectName;

    /**
     * 成员
     */
    private String memberName;

    /**
     * 接受状态;0 未处理 1已接受 2拒绝
     */
    private String status;

    private String inviter;
}
