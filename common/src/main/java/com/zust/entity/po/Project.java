package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 项目;
 * @date : 2024-1-3
 */
@Data
@TableName("project")
public class Project implements Serializable {
   
    /** 项目ID */
    @TableId
    private Integer id ;
    
    /** 创建者的用户ID */
    private Integer userId ;
    
    /** 项目名称 */
    private String name ;
    
    /** 项目简介 */
    private String description ;
    
    /** 当前项目状态;0归档、1进行中 */
    private String status ;
    
    /** 项目创建时间 */
    private Date createdAt ;
    
}