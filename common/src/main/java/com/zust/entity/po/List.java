package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

 /**
 * 任务列表;
 * @date : 2024-1-3
 */
@Data
@TableName("list")
public class List {
   
    /** 主键 */
    @TableId
    private Integer id ;
    
    /** 所属项目id */
    private String projectId ;
    
    /** 任务列表名 */
    private String name ;
    
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime ;
    
}