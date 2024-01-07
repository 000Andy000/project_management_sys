package com.zust.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 里程碑;
 *
 * @date : 2024-1-4
 */
@Data
@TableName("landmark")
public class Landmark implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 里程碑名称
     */
    private String name;

    /**
     * 所属项目id
     */
    private Integer projectId;

    /**
     * 描述
     */
    private String description;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 总结
     */
    private String summarize;

}