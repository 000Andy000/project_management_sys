package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

 /**
 * 任务;
 * @date : 2024-1-3
 */
@Data
@TableName("task")
public class Task {
   
    /** 主键 */
    @TableId
    private String id ;
    
    /** 任务名 */
    private String name ;
    
    /** 任务详情 */
    private String description ;
    
    /** 任务状态;0-未完成，1-完成 */
    private String stauts ;
    
    /** 开始时间 */
    private Date startTime ;
    
    /** 结束时间 */
    private Date endTime ;
    
    /** 所属任务列表id */
    private Integer missionId ;
    
    /** 执行者 */
    private Integer executorId ;
    
    /** 优先级 */
    private String priority ;
    
    /** 贡献度;用户完成该任务可在对应的项目中增加贡献度 */
    private Integer score ;
    
}