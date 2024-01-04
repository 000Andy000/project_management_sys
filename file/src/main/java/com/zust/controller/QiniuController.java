package com.zust.controller;

import com.zust.entity.Result;
import com.zust.service.FileService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.zust.util.QiniuUtils;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class QiniuController {
    @DubboReference
    FileService fileService;
    /**
     * 处理文件上传
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
       return fileService.uploadFile(file);


    }

    @PostMapping("/delete")
    public String delete(@RequestParam("key") String key) {
        QiniuUtils.deleteFile(key);
        return "redirect:/qiniu/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("files", QiniuUtils.listFiles());
        return "list"; // 返回的是视图名称，例如 "list.html" 位于 "src/main/resources/templates"
    }

}
