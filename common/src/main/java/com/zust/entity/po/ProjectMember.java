package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

import java.util.Date;

 /**
 * 项目成员关系;连接表，连接成员和项目
 * @date : 2024-1-4
 */
@Data
@TableName("project_member")
public class ProjectMember implements Serializable{
   
    /** 主键 */
    @TableId
    private Integer id ;
    
    /** 项目ID */
    private String projectId ;
    
    /** 成员ID */
    private String memberId ;
    
    /** 最近一次打开时间;用于查看所有任务时的排序依据 */
    private Date checkTime ;
    
    /** 成员在该项目中的总贡献度 */
    private Integer score ;
    
}