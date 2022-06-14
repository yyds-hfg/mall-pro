package com.hacker.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hacker.dao.SkuImagesDao;
import com.hacker.entity.SkuImagesEntity;
import com.hacker.service.SkuImagesService;


@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {


}