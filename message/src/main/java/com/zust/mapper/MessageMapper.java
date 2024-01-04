package com.zust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.entity.po.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
