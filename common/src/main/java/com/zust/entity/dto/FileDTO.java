package com.zust.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Andy
 * @date 2024-1-9 009 20:31
 */
@Data
public class FileDTO implements Serializable {
    // 文件名
    private String name;

    // 文件类型
    private String fileType;

    // 文件字节数据
    private byte[] fileBytes;

    // 文件大小
    private String size;

    // 项目id
    private Integer projectId;

    public FileDTO(String name, String fileType, byte[] fileBytes, String size, Integer projectId) {
        this.name = name;
        this.fileType = fileType;
        this.fileBytes = fileBytes;
        this.size = formatFileSize(size);
        this.projectId = projectId;
    }

    public static String formatFileSize(String sizeStr) {
        try {
            long sizeInBytes = Long.parseLong(sizeStr);
            if (sizeInBytes < 1024) {
                return sizeInBytes + " Bytes";
            } else if (sizeInBytes < 1024 * 1024) {
                return String.format("%.2f KB", sizeInBytes / 1024.0);
            } else if (sizeInBytes < 1024 * 1024 * 1024) {
                return String.format("%.2f MB", sizeInBytes / (1024.0 * 1024));
            } else {
                return String.format("%.2f GB", sizeInBytes / (1024.0 * 1024 * 1024));
            }
        } catch (NumberFormatException e) {
            return "无效的输入";
        }
    }


}
