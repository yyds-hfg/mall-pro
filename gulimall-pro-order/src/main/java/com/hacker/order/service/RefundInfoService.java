package com.hacker.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.common.utils.PageUtils;
import com.hacker.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:56:16
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

