package com.hacker.product.service;

import com.hacker.product.vo.SpuSaveVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.product.entity.SpuInfoEntity;


/**
 * spu信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {


    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity infoEntity);


}

