package com.hacker.product.controller;

import java.util.Arrays;
import java.util.List;

import com.hacker.common.result.R;
import com.hacker.product.entity.CategoryEntity;
import com.hacker.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品三级分类
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 22:50:32
 */
@RestController
@RequestMapping("product/category")
@Api(value = "商品三级分类控制层")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查出所有分类以及子分类，以树形结构组装起来
     */
    @GetMapping("/list/tree")
    @ApiOperation(value = "查出所有分类以及子分类，以树形结构组装起来")
    public R<List<CategoryEntity>> list(){
        return R.run(()->categoryService.listWithTree());
    }


    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    //@RequiresPermissions("product:category:info")
    public com.hacker.common.utils.R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);
        return com.hacker.common.utils.R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:category:save")
    public com.hacker.common.utils.R save(@RequestBody CategoryEntity category){
		categoryService.save(category);
        return com.hacker.common.utils.R.ok();
    }

    @PostMapping("/update/sort")
    //@RequiresPermissions("product:category:update")
    public com.hacker.common.utils.R updateSort(@RequestBody CategoryEntity[] category){
        categoryService.updateBatchById(Arrays.asList(category));
        return com.hacker.common.utils.R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:category:update")
    public com.hacker.common.utils.R update(@RequestBody CategoryEntity category){
		categoryService.updateCascade(category);
        return com.hacker.common.utils.R.ok();
    }


    /**
     * 删除
     * @RequestBody:获取请求体，必须发送POST请求
     * SpringMVC自动将请求体的数据（json），转为对应的对象
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("product:category:delete")
    public com.hacker.common.utils.R delete(@RequestBody Long[] catIds){
		//categoryService.removeByIds(Arrays.asList(catIds));
        categoryService.removeMenuByIds(Arrays.asList(catIds));
        return com.hacker.common.utils.R.ok();
    }

}
