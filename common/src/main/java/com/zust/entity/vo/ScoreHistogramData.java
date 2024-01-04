package com.zust.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 贡献柱状图
 */
@Getter
@Setter
@AllArgsConstructor
public class ScoreHistogramData implements Serializable {
    String memberName;
    int score;
}
