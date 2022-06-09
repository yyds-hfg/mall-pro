package com.hacker.mapper;

import com.hacker.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zero
 * @since 2022-06-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
