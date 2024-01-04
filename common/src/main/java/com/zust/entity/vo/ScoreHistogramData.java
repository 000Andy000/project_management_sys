package com.zust.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 贡献柱状图
 */
@Getter
@Setter
@AllArgsConstructor
public class ScoreHistogramData {
    String memberName;
    int score;
}
