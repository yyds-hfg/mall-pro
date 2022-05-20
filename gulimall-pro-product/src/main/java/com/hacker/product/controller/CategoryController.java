package com.hacker.product.controller;

import java.util.Arrays;
import java.util.List;

import com.hacker.common.result.R;
import com.hacker.product.entity.CategoryEntity;
import com.hacker.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zero
 * @Date: 2022/5/19 14:30
 * @Description: 商品三级分类
 */
@RestController
@RequestMapping("product/category")
@Api(tags = "商品三级分类")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    /**
     * 查出所有分类以及子分类，以树形结构组装起来
     */
    @GetMapping("/list/tree")
    @ApiOperation(value = "查出所有分类以及子分类，以树形结构组装起来")
    public R<List<CategoryEntity>> list() {
        return R.run(() -> categoryService.listWithTree());
    }

    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    public R<?> info(@PathVariable("catId") Long catId) {
        return R.run(() -> categoryService.getById(catId));
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("")
    public R<?> save(@RequestBody CategoryEntity category) {
        return R.run(() -> categoryService.save(category));
    }

    @PostMapping("/update/sort")
    public R<?> updateSort(@RequestBody CategoryEntity[] category) {
        return R.run(() -> categoryService.updateBatchById(Arrays.asList(category)));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody CategoryEntity category) {
        return R.run(() -> categoryService.updateCascade(category));
    }

    /**
     * 删除
     * SpringMVC自动将请求体的数据（json），转为对应的对象
     *
     * @param catIds 获取请求体，必须发送POST请求
     * @return
     */
    @DeleteMapping("/delete")
    public R<?> delete(@RequestBody Long[] catIds) {
        return R.run(() -> categoryService.removeMenuByIds(Arrays.asList(catIds)));
    }

}
