package com.hacker.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import com.hacker.common.annotation.Type;
import com.hacker.common.result.ResultT;
import com.hacker.coupon.entity.CouponEntity;
import com.hacker.coupon.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hacker.common.utils.PageUtils;
import com.hacker.common.utils.R;

import javax.annotation.Resource;
/**
 * 优惠券信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:36:40
 */
@RefreshScope
@RestController
@RequestMapping("coupon/coupon")
@Api(value = "优惠券信息")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Value("${coupon.user.name}")
    private String name;

    @Value("${coupon.user.age}")
    private Integer age;

    @GetMapping("/test")
    public R test(){
        return R.ok().put("name",name).put("age",age);
    }

    @GetMapping("/member/list")
    @ApiOperation("会员服务")
    public ResultT<CouponEntity> membercoupons(){
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满100减10");
        return ResultT.ok(couponEntity);
    }

    @Resource
    private Validator validator;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CouponEntity coupon = couponService.getById(id);
        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存coupon
     */
    @PostMapping("/save")
    public ResultT<?> save(@Validated({Type.Add.class}) @RequestBody CouponEntity coupon){
        return ResultT.run(()->couponService.save(coupon));
    }

    /**
     * 修改
     */
    @GetMapping("/update")
    public R update(@RequestBody CouponEntity coupon){
		couponService.updateById(coupon);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		couponService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @SneakyThrows
    @GetMapping("/hello")
    public ResultT<String> hello() {
        couponService.hellp();
        return ResultT.ok();
    }

}
