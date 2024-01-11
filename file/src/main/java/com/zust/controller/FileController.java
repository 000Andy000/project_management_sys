package com.zust.controller;

import com.zust.entity.Code;
import com.zust.entity.Result;
import com.zust.entity.dto.FileDTO;
import com.zust.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
                         @RequestParam("projectId") Integer projectId,
                         @RequestHeader("userId") String userId) {

        // 获取文件
        FileDTO fileDTO = null;
        try {
            // 封装文件信息
            fileDTO = new FileDTO(file.getOriginalFilename(), file.getContentType(), file.getBytes(), String.valueOf(file.getSize()), projectId);
            // 上传文件
            fileService.uploadFile(fileDTO,userId);
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


    /**
     * 删除文件
     */
    @DeleteMapping
    public Result deleteFile(@RequestParam(value = "id") Integer id) {

        fileService.deleteFileById(id);
        return new Result(Code.SUCCESS, "删除文件成功");

    }


    /**
     * 下载文件
     *
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<UrlResource> downloadFile(@RequestParam(value = "id") Integer id) {
        String downloadUrl = fileService.getDownloadUrl(id);
        String fileName = fileService.getFileNameById(id);

        try {
            // 对文件名进行 URL 编码
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");

            // 创建资源对象
            URL url = new URL(downloadUrl);
            UrlResource resource = new UrlResource(url);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
