package com.zust.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.entity.PageData;
import com.zust.entity.dto.FileDTO;
import com.zust.entity.po.File;
import com.zust.mapper.FileMapper;
import com.zust.utils.ObjectConverter;
import com.zust.utils.QiniuUtils;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@DubboService
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    final FileMapper fileMapper;

    @Override
    public void uploadFile(FileDTO fileDto) {

        // TODO 获取真实的用户id
        Integer userId = 1;

        // 生成唯一key
        String key = QiniuUtils.generateUniqueKey(fileDto.getName());

        // DTO转为File对象
        File file = ObjectConverter.AToB(fileDto, File.class);
        file.setUploadTime(new Date());
        file.setFileKey(key);
        file.setUserId(userId);
        // 保存文件信息到数据库
        fileMapper.insert(file);

        // 上传
        QiniuUtils.uploadFile(fileDto.getFileBytes(), key);

    }

    @Override
    public PageData getFileList(Integer projectId, Integer pageNum, Integer pageSize, String name) {

        LambdaQueryWrapper<File> wrapper = new LambdaQueryWrapper<>();
        // 筛选
        wrapper.eq(File::getProjectId, projectId);
        wrapper.like(StringUtils.isNotEmpty(name), File::getName, name);
        // 排序
        wrapper.orderByDesc(File::getUploadTime);
        // 分页
        Page<File> page = new Page<>(pageNum, pageSize);
        IPage<File> fileIPage = fileMapper.selectPage(page, wrapper);
        return new PageData(fileIPage.getTotal(), fileIPage.getRecords());

    }

    @Override
    public void deleteFileById(Integer id) {
        // 获取文件信息
        File file = fileMapper.selectById(id);
        // 删除七牛云上的文件
        QiniuUtils.deleteFile(file.getFileKey());
        // 删除数据库中的文件信息
        fileMapper.deleteById(id);
    }
}
