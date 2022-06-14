package com.hacker.controller;

import java.util.Arrays;

import com.hacker.common.result.R;
import com.hacker.entity.SkuInfoEntity;
import com.hacker.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * sku信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 22:50:32
 */
@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuInfoService;


    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    //@RequiresPermissions("product:skuinfo:info")
    public R<SkuInfoEntity> info(@PathVariable("skuId") Long skuId) {
        return R.run(() -> skuInfoService.getById(skuId));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:skuinfo:save")
    public R<?> save(@RequestBody SkuInfoEntity skuInfo) {
        skuInfoService.save(skuInfo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:skuinfo:update")
    public R<?> update(@RequestBody SkuInfoEntity skuInfo) {
        skuInfoService.updateById(skuInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:skuinfo:delete")
    public R<?> delete(@RequestBody Long[] skuIds) {
        skuInfoService.removeByIds(Arrays.asList(skuIds));
        return R.ok();
    }

}
