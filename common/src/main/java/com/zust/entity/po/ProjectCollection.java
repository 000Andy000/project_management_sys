package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 项目收藏关系;
 * @date : 2024-1-3
 */
@Data
@TableName("project_collection")
public class ProjectCollection  implements Serializable {
   
    /** 主键 */
    @TableId
    private String collectionId ;
    
    /** 被收藏的项目id */
    private String projectId ;
    
    /** 收藏者（用户）id */
    private String userId ;
    
}