package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

 /**
 * 团队邀请信息;
 * @date : 2024-1-3
 */
@Data
@TableName("team_invitation")
public class TeamInvitation  implements Serializable {
   
    /** 主键 */
    @TableId
    private Integer id ;
    
    /** 项目ID */
    private Integer projectId ;
    
    /** 邀请者ID */
    private Integer inviterId ;
    
    /** 被邀请者ID */
    private Integer invitedId ;
    
    /** 接受状态;0未处理 1接收 2拒绝 */
    private String inviteStatus ;
    
    /** 邀请时间 */
    private Date createdTime ;
    
}