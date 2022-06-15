package com.hacker.order.service.impl;

import com.hacker.order.dao.OrderDao;
import com.hacker.order.entity.OrderEntity;
import com.hacker.order.service.OrderService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {


}