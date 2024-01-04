package com.zust.service;

import com.zust.entity.Result;
import com.zust.util.QiniuUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class QiniuServiceImpl implements FileService {

    @Override
    public Result uploadFile(MultipartFile file) {
        String key = file.getOriginalFilename();
        try {
            QiniuUtils.uploadFile(file.getInputStream(), key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Result(200, "上传成功",key);
    }
}
