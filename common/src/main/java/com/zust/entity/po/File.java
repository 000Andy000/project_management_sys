package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 文件表;
 * @date : 2024-1-3
 */
@Data
@TableName("file")
public class File  implements Serializable {
   
    /** 文件id */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    
    /** 文件显示名称 */
    private Integer name ;
    
    /** 文件大小（单位：字节） */
    private String size ;
    
    /** 文件类型 */
    private String fileType ;
    
    /** 七牛云文件key */
    private String fileKey ;
    
    /** 上传者id */
    private Integer userId ;
    
    /** 所属项目id */
    private Integer projectId ;
    
    /** 上传时间 */
    private Date uploadTime ;
    
}