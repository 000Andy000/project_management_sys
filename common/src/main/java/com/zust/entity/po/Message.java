package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 动态消息;
 * @date : 2024-1-3
 */
@Data
@TableName("message")
public class Message implements Serializable {
   
    /**  */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    
    /** 用户id */
    private Integer userId ;
    
    /** 动态标题 */
    private String title ;
    
    /** 动态内容 */
    private String content ;
    
    /** 创建时间 */
    private Date createdTime ;
    
    /** 项目id */
    private Integer projectId ;
    
}