package com.zust.entity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
public class TaskDTO implements Serializable {
    String listId;
    String name;
    String description;
    String endTime;
    String executorId;
}
