package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

import java.util.Date;

 /**
 * 项目指导关系;
 * @date : 2024-1-3
 */
@Data
@TableName("project_guidance")
public class ProjectGuidance implements Serializable{
   
    /** 主键 */
    @TableId
    private Integer id ;
    
    /** 被指导的项目id */
    private Integer projectId ;
    
    /** 指导老师id */
    private Integer userId ;
    
    /** 状态（是否归档，0否 1是） */
    private String status ;
    
}