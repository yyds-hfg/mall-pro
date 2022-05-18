package com.hacker.coupon.service.impl;

import com.hacker.common.annotation.SystemLog;
import com.hacker.coupon.dao.CouponDao;
import com.hacker.coupon.entity.CouponEntity;
import com.hacker.coupon.service.CouponService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hacker.common.utils.PageUtils;
import com.hacker.common.utils.Query;


@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity> implements CouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponEntity> page = this.page(
                new Query<CouponEntity>().getPage(params),
                new QueryWrapper<CouponEntity>()
        );

        return new PageUtils(page);
    }

    @SneakyThrows
    @Override
    @SystemLog
    public String hellp() {
        for (int i = 0; i < 5; i++) {
            System.out.println("执行第"+i+++"次");
            Thread.sleep(2000);
        }
        return "hahsah";
    }

}