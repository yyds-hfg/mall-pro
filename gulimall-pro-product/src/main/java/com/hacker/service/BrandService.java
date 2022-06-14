package com.hacker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.entity.BrandEntity;


/**
 * 品牌
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface BrandService extends IService<BrandEntity> {


    void updateDetail(BrandEntity brand);

}

