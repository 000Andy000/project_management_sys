package com.zust.service;

import com.zust.entity.PageData;
import com.zust.entity.dto.FileDTO;
import com.zust.entity.po.File;

import java.util.List;
import java.util.Map;

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
     */
    void uploadFile(FileDTO fileDto);

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
}
