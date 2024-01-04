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
    String percentage;
}
