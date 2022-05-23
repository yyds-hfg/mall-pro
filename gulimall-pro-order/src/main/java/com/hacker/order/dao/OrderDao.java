package com.hacker.order.dao;

import com.hacker.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 *
 * @Author: Zero
 * @Date: 2022/5/21 9:23
 * @Description:
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

}
