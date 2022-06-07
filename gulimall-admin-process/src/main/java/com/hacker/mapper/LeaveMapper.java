package com.hacker.mapper;

import com.hacker.po.Leave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 请假业务 Mapper 接口
 * </p>
 *
 * @author Zero
 * @since 2022-06-06
 */
@Mapper
public interface LeaveMapper extends BaseMapper<Leave> {

}
