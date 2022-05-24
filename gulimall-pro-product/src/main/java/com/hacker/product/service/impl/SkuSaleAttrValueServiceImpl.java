package com.hacker.product.service.impl;

import com.hacker.product.dao.SkuSaleAttrValueDao;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hacker.product.entity.SkuSaleAttrValueEntity;
import com.hacker.product.service.SkuSaleAttrValueService;

/**
 * @Author: Zero
 * @Date: 2022/5/19 14:29
 * @Description:
 */
@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {


}