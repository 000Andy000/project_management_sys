package com.zust.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Andy
 * @date 2024-1-9 009 22:58
 *
 * 分页数据封装
 */
@Data
@AllArgsConstructor
public class PageData implements Serializable {

    // 总页数
    Long totalPage;

    // 页面数据
    List<?> data;

}
