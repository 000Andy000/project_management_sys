package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 任务列表;
 * @date : 2024-1-3
 */
@Data
@TableName("list")
public class List implements Serializable {
   
    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    
    /** 所属项目id */
    private String projectId ;
    
    /** 任务列表名 */
    private String name ;
    
    /** 创建时间 */
    private Date createdTime ;
    
}