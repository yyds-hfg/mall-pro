package com.hacker.order.dao;

import com.hacker.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 *
 * @Author: Zero
 * @Date: 2022/5/21 9:23
 * @Description:
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {

}
