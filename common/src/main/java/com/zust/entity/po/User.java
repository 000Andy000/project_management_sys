package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 用户;
 * @date : 2024-1-3
 */
@Data
@TableName("user")
public class User implements Serializable {
   
    /** 用户id */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    
    /** 用户名 */
    private String username ;
    
    /** 密码 */
    private String password ;
    
    /** 邮箱 */
    private String mail ;
    
    /** 手机号 */
    private String phone ;
    
    /** 身份;0-admin 1-普通用户 */
    private String role ;
    
}