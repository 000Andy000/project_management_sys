package com.zust.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Andy
 * @date 2024-1-3 003 20:07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
