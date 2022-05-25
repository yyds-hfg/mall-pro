package com.hacker.order.controller;

import java.util.Arrays;
import java.util.Map;

import com.hacker.common.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hacker.order.entity.OrderEntity;
import com.hacker.order.service.OrderService;

/**
 * 订单
 *
 * @Author: Zero
 * @Date: 2022/5/21 8:53
 * @Description:
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
    public R<?> list(@RequestParam Map<String, Object> params) {
        return R.ok();
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public com.hacker.common.utils.R info(@PathVariable("id") Long id) {
        OrderEntity order = orderService.getById(id);
        return com.hacker.common.utils.R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @PutMapping("/save")
    public R<?> save(@RequestBody OrderEntity order) {
        return R.run(() -> orderService.save(order));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody OrderEntity order) {
        return R.run(() -> orderService.updateById(order));
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public com.hacker.common.utils.R delete(@RequestBody Long[] ids) {
        orderService.removeByIds(Arrays.asList(ids));
        return com.hacker.common.utils.R.ok();
    }

}
