package com.hacker.order.service.impl;

import com.hacker.order.service.OrderReturnReasonService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hacker.order.dao.OrderReturnReasonDao;
import com.hacker.order.entity.OrderReturnReasonEntity;


@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonDao, OrderReturnReasonEntity> implements OrderReturnReasonService {


}