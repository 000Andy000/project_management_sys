package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

import java.util.Date;

 /**
 * 统计数据表;
 * @date : 2024-1-5
 */
@Data
@TableName("statistics")
public class Statistics implements Serializable{
   
    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    
    /** 名称 */
    private String name ;
    
    /** 数量 */
    private Integer score ;
    
    /** 百分比 */
    private String percentage ;
    
    /** 数据类型;1-单个项目的里程碑阶段的贡献度 */
    private String type ;
    
    /** 相关的项目id */
    private Integer projectId ;
    
    /** 相关的用户id */
    private Integer userId ;
    
    /** 相关的里程碑id */
    private Integer landmarkId ;
    
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime ;
    
}