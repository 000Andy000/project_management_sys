package com.zust.utils;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QiniuUtils {
    //鉴权数据 ACCESS_KEY、SECRET_KEY、BUCKET_NAME
    // 定义七牛云服务的认证信息
    private static final String ACCESS_KEY = "2BcgmVCVH8f5KYtI0bN0hhtQLIF0vMsHJ207sauA";
    private static final String SECRET_KEY = "Uw6B4kE0B3T4TrTWOwliqzvg8KzM3gd3m2-vQp1b";
    private static final String BUCKET_NAME = "czgex5";
    private static final String DOMAIN = "s679t975m.hn-bkt.clouddn.com";


    // Auth 对象用于后续操作
    private static final Auth AUTH = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 空间对应的机房，根据需要选择
    private static final Configuration CFG = new Configuration(Zone.autoZone());
    // 创建管理对象
    private static final BucketManager BUCKET_MANAGER = new BucketManager(AUTH, CFG);
    // 创建上传对象
    private static final UploadManager UPLOAD_MANAGER = new UploadManager(CFG);

    /**
     * 生成唯一的文件名
     *
     * @param fileName 文件名
     * @return 唯一的key
     */
    public static String generateUniqueKey(String fileName) {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取上传文件的 token
     * token的作用是让你的服务器上传文件到七牛云存储的时候，七牛云存储可以验证这个文件是你的服务器上传的，而不是别人伪造的。
     *
     * @return 上传文件的 token 字符串
     */
    public static String getUploadToken() {
        return AUTH.uploadToken(BUCKET_NAME);
    }

    /**
     * 上传文件
     *
     * @param fileBytes 文件字节数组
     * @param key       文件在七牛云存储的名字
     */
    public static void uploadFile(byte[] fileBytes, String key) {
        try (InputStream inputStream = new ByteArrayInputStream(fileBytes)) {
            String upToken = getUploadToken();
            // 调用put方法上传
            Response response = UPLOAD_MANAGER.put(inputStream, key, upToken, null, null);

            // 打印是否上传成功
            System.out.println("上传成功: " + response.isOK());
            // 打印上传后的文件名
            System.out.println("文件名: " + response.bodyString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除文件
     *
     * @param key 文件在七牛云存储的key
     *
     * @return 是否删除成功
     *
     */
    public static boolean deleteFile(String key) {
        try {
            BUCKET_MANAGER.delete(BUCKET_NAME, key);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 生成七牛云文件的下载链接
     *
     * @param fileKey 七牛云文件的key
     * @return 下载链接
     */
    public static String getDownloadUrl(String fileKey) {
        // 构造公开空间的文件链接
        String publicFileUrl = String.format("http://%s/%s", DOMAIN, fileKey);

        // 生成下载链接，默认有效期为100秒
        return AUTH.privateDownloadUrl(publicFileUrl, 100);
    }


}
