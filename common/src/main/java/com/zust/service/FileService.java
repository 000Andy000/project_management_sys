package com.zust.service;

import com.zust.entity.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件表;(file)表服务接口
 * @date : 2024-1-3
 */
public interface FileService{
 /*上传文件*/
  public Result uploadFile(MultipartFile file);
}
