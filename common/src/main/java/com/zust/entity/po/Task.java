package com.zust.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

 /**
 * 任务;
 * @author : http://www.chiner.pro
 * @date : 2024-1-3
 */
@ApiModel(value = "任务",description = "")
public class Task implements Serializable,Cloneable{
    /** 主键 */
    @ApiModelProperty(name = "主键",notes = "")
    private String id ;
    /** 任务名 */
    @ApiModelProperty(name = "任务名",notes = "")
    private String name ;
    /** 任务详情 */
    @ApiModelProperty(name = "任务详情",notes = "")
    private String description ;
    /** 任务状态;0-未完成，1-完成 */
    @ApiModelProperty(name = "任务状态",notes = "0-未完成，1-完成")
    private String stauts ;
    /** 开始时间 */
    @ApiModelProperty(name = "开始时间",notes = "")
    private Date startTime ;
    /** 结束时间 */
    @ApiModelProperty(name = "结束时间",notes = "")
    private Date endTime ;
    /** 所属任务列表id */
    @ApiModelProperty(name = "所属任务列表id",notes = "")
    private Integer missionId ;
    /** 执行者 */
    @ApiModelProperty(name = "执行者",notes = "")
    private Integer executorId ;
    /** 优先级 */
    @ApiModelProperty(name = "优先级",notes = "")
    private String priority ;
    /** 贡献度;用户完成该任务可在对应的项目中增加贡献度 */
    @ApiModelProperty(name = "贡献度",notes = "用户完成该任务可在对应的项目中增加贡献度")
    private Integer score ;

    /** 主键 */
    public String getId(){
        return this.id;
    }
    /** 主键 */
    public void setId(String id){
        this.id=id;
    }
    /** 任务名 */
    public String getName(){
        return this.name;
    }
    /** 任务名 */
    public void setName(String name){
        this.name=name;
    }
    /** 任务详情 */
    public String getDescription(){
        return this.description;
    }
    /** 任务详情 */
    public void setDescription(String description){
        this.description=description;
    }
    /** 任务状态;0-未完成，1-完成 */
    public String getStauts(){
        return this.stauts;
    }
    /** 任务状态;0-未完成，1-完成 */
    public void setStauts(String stauts){
        this.stauts=stauts;
    }
    /** 开始时间 */
    public Date getStartTime(){
        return this.startTime;
    }
    /** 开始时间 */
    public void setStartTime(Date startTime){
        this.startTime=startTime;
    }
    /** 结束时间 */
    public Date getEndTime(){
        return this.endTime;
    }
    /** 结束时间 */
    public void setEndTime(Date endTime){
        this.endTime=endTime;
    }
    /** 所属任务列表id */
    public Integer getMissionId(){
        return this.missionId;
    }
    /** 所属任务列表id */
    public void setMissionId(Integer missionId){
        this.missionId=missionId;
    }
    /** 执行者 */
    public Integer getExecutorId(){
        return this.executorId;
    }
    /** 执行者 */
    public void setExecutorId(Integer executorId){
        this.executorId=executorId;
    }
    /** 优先级 */
    public String getPriority(){
        return this.priority;
    }
    /** 优先级 */
    public void setPriority(String priority){
        this.priority=priority;
    }
    /** 贡献度;用户完成该任务可在对应的项目中增加贡献度 */
    public Integer getScore(){
        return this.score;
    }
    /** 贡献度;用户完成该任务可在对应的项目中增加贡献度 */
    public void setScore(Integer score){
        this.score=score;
    }
}