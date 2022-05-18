package com.hacker.order.controller;

import java.util.Arrays;
import java.util.Map;

import com.hacker.common.result.ResultT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hacker.order.entity.OrderEntity;
import com.hacker.order.service.OrderService;
import com.hacker.common.utils.PageUtils;
import com.hacker.common.utils.R;

/**
 * 订单
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:56:16
 */
@RestController
@RequestMapping("order/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		OrderEntity order = orderService.getById(id);
        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @PutMapping("/save")
    public ResultT<?> save(@RequestBody OrderEntity order){
		return ResultT.run(()->orderService.save(order));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public ResultT<?> update(@RequestBody OrderEntity order){
        return ResultT.run(()->orderService.updateById(order));
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		orderService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
