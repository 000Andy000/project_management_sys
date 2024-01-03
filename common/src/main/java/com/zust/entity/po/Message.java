package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

 /**
 * 动态消息;
 * @date : 2024-1-3
 */
@Data
@TableName("message")
public class Message {
   
    /**  */
    @TableId
    private Integer id ;
    
    /** 用户id */
    private Integer userId ;
    
    /** 动态标题 */
    private String title ;
    
    /** 动态内容 */
    private String content ;
    
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime ;
    
    /** 任务id */
    private Integer taskId ;
    
}