package com.hacker.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hacker.product.dao.SkuImagesDao;
import com.hacker.product.entity.SkuImagesEntity;
import com.hacker.product.service.SkuImagesService;


@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {


}