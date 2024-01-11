package com.zust.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Andy
 * @date 2024-1-10 010 2:39
 */
@Data
public class FileVO implements Serializable {

    /**
     * 文件id
     */
    private Integer id;

    /**
     * 文件显示名称
     */
    private String name;

    /**
     * 文件大小（单位：字节）
     */
    private String size;

    /**
     * 文件类型
     */
    private String fileType;


    /**
     * 上传者
     */
    private String user;


    /**
     * 上传时间
     */
    private String uploadTime;

    public static String convertMimeTypeToSimpleType(String mimeType) {
        switch (mimeType) {
            case "application/pdf":
                return "PDF";
            case "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return "Word";
            case "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return "Excel";
            case "application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation":
                return "PowerPoint";
            case "text/plain":
                return "Text";
            case "image/jpeg":
            case "image/png":
                return "Image";
            // 添加更多的映射
            default:
                return "other";
        }
    }
}
