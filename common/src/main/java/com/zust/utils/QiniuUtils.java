package com.zust.utils;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QiniuUtils {
    //鉴权数据 ACCESS_KEY、SECRET_KEY、BUCKET_NAME
    // 定义七牛云服务的认证信息
    private static final String ACCESS_KEY = "2BcgmVCVH8f5KYtI0bN0hhtQLIF0vMsHJ207sauA";
    private static final String SECRET_KEY = "Uw6B4kE0B3T4TrTWOwliqzvg8KzM3gd3m2-vQp1b";
    private static final String BUCKET_NAME = "czgex5";


    // Auth 对象用于后续操作
    private static final Auth AUTH = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 空间对应的机房，根据需要选择
    private static final Configuration CFG = new Configuration(Zone.autoZone());
    // 创建管理对象
    private static final BucketManager BUCKET_MANAGER = new BucketManager(AUTH, CFG);
    // 创建上传对象
    private static final UploadManager UPLOAD_MANAGER = new UploadManager(CFG);

    /**
     * 获取上传文件的 token
     *
     * @return 上传文件的 token 字符串
     */
    public static String getUploadToken() {
        return AUTH.uploadToken(BUCKET_NAME);
    }

    /**
     * 上传文件
     *
     * @param inputStream 文件数据流
     * @param key         文件在七牛云存储的名字
     */
    public static void uploadFile(InputStream inputStream, String key) {
        try {
            String upToken = getUploadToken();
            // 调用put方法上传
            Response response = UPLOAD_MANAGER.put(inputStream, key, upToken, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件列表
     */
    public static List<String> listFiles() {
        try {
            FileListing files = BUCKET_MANAGER.listFiles(BUCKET_NAME, null, null, 1000, null);
            List<String> list = new ArrayList<>();
            // 遍历文件，获取文件名
            for (FileInfo file : files.items) {
                list.add(file.key);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param key 文件在七牛云存储的名字
     */
    public static void deleteFile(String key) {
        try {
            BUCKET_MANAGER.delete(BUCKET_NAME, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
