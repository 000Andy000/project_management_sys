package com.zust.entity;

import lombok.Data;

/**
 * @author Andy
 * @date 2023-7-14 014 16:02
 */
@Data
public class Result {
    //json数据
    private Object data;
    //状态码
    private int code;
    //提示信息
    private String msg;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(int code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回成功方法
     *
     * @param data 返回的数据
     * @return 返回的结果
     */
    public static Result success(Object data) {
        return new Result(Code.SUCCESS, data, "success");
    }

    /**
     * 返回成功方法
     *
     * @return 返回的结果
     */
    public static Result success() {
        return new Result(Code.SUCCESS, "seccess");
    }

    /**
     * 返回失败方法
     *
     * @param msg 返回的信息
     * @return 返回的结果
     */
    public static Result error(String msg) {
        return new Result(Code.ERROR, msg);
    }
}
