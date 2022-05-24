package com.hacker.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {


    void saveSkuInfo(SkuInfoEntity skuInfoEntity);


}

