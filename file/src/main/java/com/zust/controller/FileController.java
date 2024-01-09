package com.zust.controller;

import com.zust.entity.Code;
import com.zust.entity.Result;
import com.zust.entity.dto.FileDTO;
import com.zust.service.FileService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author AD
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @DubboReference
    FileService fileService;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("projectId") Integer projectId) {

        // 获取文件
        FileDTO fileDTO = null;
        try {
            // 封装文件信息
            fileDTO = new FileDTO(file.getOriginalFilename(), file.getContentType(), file.getBytes(), String.valueOf(file.getSize()), projectId);
            // 上传文件
            fileService.uploadFile(fileDTO);
        } catch (IOException e) {
            return new Result(Code.ERROR, "文件上传失败");
        }
        return new Result(Code.SUCCESS, "上传成功");

    }

    /**
     * 分页获取文件列表
     */
    @GetMapping
    public Result getFileList(@RequestParam(value = "projectId", defaultValue = "") Integer projectId,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                              @RequestParam(value = "name", defaultValue = "") String name) {

        return new Result(Code.SUCCESS, fileService.getFileList(projectId, pageNum, pageSize, name), "获取文件列表成功");

    }





}
