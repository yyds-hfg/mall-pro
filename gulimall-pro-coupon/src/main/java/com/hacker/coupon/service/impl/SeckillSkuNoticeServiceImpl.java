package com.hacker.coupon.service.impl;

import com.hacker.coupon.dao.SeckillSkuNoticeDao;
import com.hacker.coupon.entity.SeckillSkuNoticeEntity;
import com.hacker.coupon.service.SeckillSkuNoticeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hacker.common.utils.PageUtils;
import com.hacker.common.utils.Query;


@Service("seckillSkuNoticeService")
public class SeckillSkuNoticeServiceImpl extends ServiceImpl<SeckillSkuNoticeDao, SeckillSkuNoticeEntity>
        implements SeckillSkuNoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSkuNoticeEntity> page = this.page(
                new Query<SeckillSkuNoticeEntity>().getPage(params),
                new QueryWrapper<SeckillSkuNoticeEntity>()
        );
        return new PageUtils(page);
    }

}