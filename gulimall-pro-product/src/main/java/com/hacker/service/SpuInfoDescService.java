package com.hacker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.entity.SpuInfoDescEntity;

/**
 * spu信息介绍
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {


    void saveSpuInfoDesc(SpuInfoDescEntity descEntity);


}

