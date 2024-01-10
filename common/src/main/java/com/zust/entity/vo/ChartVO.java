package com.zust.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 统计图VO
 */
@Data
public class ChartVO implements Serializable {
    String name;
    int score;
    String scoreStr;
    String percentage;

    public ChartVO(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public ChartVO(String name, String scoreStr) {
        this.name = name;
        this.scoreStr = scoreStr;
    }
}
