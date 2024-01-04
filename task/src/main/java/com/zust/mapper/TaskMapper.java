package com.zust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.entity.po.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
