package com.zust.service;

import com.zust.entity.PageData;
import com.zust.entity.dto.FileDTO;

/**
 * 文件表;(file)表服务接口
 *
 * @author AD
 * @date : 2024-1-3
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param fileDto 文件dto
     * @param userId
     */
    void uploadFile(FileDTO fileDto, String userId);

    /**
     * 根据项目id获取文件列表
     *
     * @param projectId 项目id
     * @param pageNum   页码
     * @param name      文件名
     * @return 文件列表
     */
    PageData getFileList(Integer projectId, Integer pageNum, Integer pageSize, String name);


    /**
     * 根据文件id删除文件
     *
     * @param id 文件id
     */
    void deleteFileById(Integer id);

    /**
     * 根据文件id获取文件下载地址
     *
     * @param id 文件id
     * @return 文件下载地址
     */
    String getDownloadUrl(Integer id);

    /**
     * 根据文件id获取文件名
     *
     * @param id 文件id
     * @return 文件名
     */
    String getFileNameById(Integer id);
}
