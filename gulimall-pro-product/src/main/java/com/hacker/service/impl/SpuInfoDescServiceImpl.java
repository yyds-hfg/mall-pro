package com.hacker.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hacker.dao.SpuInfoDescDao;
import com.hacker.entity.SpuInfoDescEntity;
import com.hacker.service.SpuInfoDescService;


@Service("spuInfoDescService")
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescDao, SpuInfoDescEntity> implements SpuInfoDescService {


    @Override
    public void saveSpuInfoDesc(SpuInfoDescEntity descEntity) {
        this.baseMapper.insert(descEntity);
    }

}