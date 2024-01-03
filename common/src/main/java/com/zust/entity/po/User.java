package com.zust.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

 /**
 * 用户;
 * @author : http://www.chiner.pro
 * @date : 2024-1-3
 */
@ApiModel(value = "用户",description = "")
public class User implements Serializable,Cloneable{
    /** 用户id */
    @ApiModelProperty(name = "用户id",notes = "")
    private Integer userId ;
    /** 用户名 */
    @ApiModelProperty(name = "用户名",notes = "")
    private String username ;
    /** 密码 */
    @ApiModelProperty(name = "密码",notes = "")
    private String password ;
    /** 邮箱 */
    @ApiModelProperty(name = "邮箱",notes = "")
    private String mail ;
    /** 手机号 */
    @ApiModelProperty(name = "手机号",notes = "")
    private String phone ;
    /** 身份;0-admin 1-普通用户 */
    @ApiModelProperty(name = "身份",notes = "0-admin 1-普通用户")
    private String role ;

    /** 用户id */
    public Integer getUserId(){
        return this.userId;
    }
    /** 用户id */
    public void setUserId(Integer userId){
        this.userId=userId;
    }
    /** 用户名 */
    public String getUsername(){
        return this.username;
    }
    /** 用户名 */
    public void setUsername(String username){
        this.username=username;
    }
    /** 密码 */
    public String getPassword(){
        return this.password;
    }
    /** 密码 */
    public void setPassword(String password){
        this.password=password;
    }
    /** 邮箱 */
    public String getMail(){
        return this.mail;
    }
    /** 邮箱 */
    public void setMail(String mail){
        this.mail=mail;
    }
    /** 手机号 */
    public String getPhone(){
        return this.phone;
    }
    /** 手机号 */
    public void setPhone(String phone){
        this.phone=phone;
    }
    /** 身份;0-admin 1-普通用户 */
    public String getRole(){
        return this.role;
    }
    /** 身份;0-admin 1-普通用户 */
    public void setRole(String role){
        this.role=role;
    }
}