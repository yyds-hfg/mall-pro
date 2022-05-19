package com.hacker.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.hacker.common.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.product.entity.SkuSaleAttrValueEntity;
import com.hacker.product.service.SkuSaleAttrValueService;


/**
 * sku销售属性&值
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 22:50:32
 */
@RestController
@RequestMapping("product/skusaleattrvalue")
public class SkuSaleAttrValueController {

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:skusaleattrvalue:list")
    public R<?> list(@RequestParam Map<String, Object> params){
        return R.run(()->skuSaleAttrValueService.queryPage(params));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:skusaleattrvalue:info")
    public R<?> info(@PathVariable("id") Long id){
        return R.run(()->skuSaleAttrValueService.getById(id));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:skusaleattrvalue:save")
    public R<?> save(@RequestBody SkuSaleAttrValueEntity skuSaleAttrValue){
        return R.run(()->skuSaleAttrValueService.save(skuSaleAttrValue));
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:skusaleattrvalue:update")
    public R<?> update(@RequestBody SkuSaleAttrValueEntity skuSaleAttrValue){
        return R.run(()->skuSaleAttrValueService.updateById(skuSaleAttrValue));
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:skusaleattrvalue:delete")
    public R<?> delete(@RequestBody Long[] ids){
        return R.run(()->skuSaleAttrValueService.removeByIds(Arrays.asList(ids)));
    }

}
