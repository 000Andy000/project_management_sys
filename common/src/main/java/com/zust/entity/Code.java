package com.zust.entity;

/**
 * @author Andy
 * @date 2023-7-17 017 16:00
 * <p>
 * 返回状态码枚举类，使用Code.SUCCESS可以直接返回int 200
 */

public class Code {
    //成功
    public static final Integer SUCCESS = 200;
    //查询到的数据为空
    public static final Integer DATA_IS_NULL = 201;
    //失败
    public static final Integer ERROR = 500;
    //未登录
    public static final Integer UNAUTHORIZED = 401;
    //用户名或密码错误
    public static final Integer USERNAME_OR_PASSWORD_ERROR = 40101;
    //没有权限
    public static final Integer NO_PERMISSION = 403;
    //资源不存在
    public static final Integer NOT_FOUND = 404;
    //冲突
    public static final Integer CONFLICT = 409;
    //上传文件中有重复
    public static final Integer REPEAT_WITH_UPLOAD_FILE = 40901;
    //上传文件类型错误
    public static final Integer FILE_TYPE_ERROR = 40902;
    //文件内容错误
    public static final Integer FILE_CONTENT_ERROR = 40903;
    //上传文件与数据库中有重复
    public static final Integer REPEAT_WITH_DATABASE = 40904;
    //用户不存在
    public static final Integer USER_NOT_EXIST = 10001;
    //用户已存在
    public static final Integer USER_HAS_EXISTED = 10002;
    //验证码错误
    public static final Integer VERIFICATION_CODE_ERROR = 10005;
    //数据库查询到的数据不一致
    public static final Integer DAtABASE_DATA_INCONSISTENCY = 10006;


}
