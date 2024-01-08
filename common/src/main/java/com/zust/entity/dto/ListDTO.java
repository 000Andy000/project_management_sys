package com.zust.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListDTO implements Serializable {
    String projectId;
    String name;
}
