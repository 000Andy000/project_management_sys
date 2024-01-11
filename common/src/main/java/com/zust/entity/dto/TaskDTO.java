package com.zust.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TaskDTO implements Serializable {
    Integer listId;
    String name;
    String description;
    String endTime;
    Integer executorId;
    String status;
}
