package com.hacker.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.product.entity.SpuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * spu图片
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {


    void saveImages(Long id, List<String> images);

}

