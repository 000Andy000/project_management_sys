package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

 /**
 * 里程碑;
 * @date : 2024-1-3
 */
@Data
@TableName("landmark")
public class Landmark {
   
    /** 主键 */
    @TableId
    private Integer id ;
    
    /** 里程碑名称 */
    private String name ;
    
    /** 所属项目id */
    private Integer projectId ;
    
    /** 描述 */
    private String description ;
    
    /** 完成时间 */
    private String finishTime ;
    
    /** 总结 */
    private String summarize ;
    
}