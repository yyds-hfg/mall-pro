package com.hacker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.entity.CategoryEntity;

import java.util.List;

/**
 * 商品三级分类
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:48
 */
public interface CategoryService extends IService<CategoryEntity> {


    /**
     * 以树形结构返回商品分类
     *
     * @return
     */
    List<CategoryEntity> listWithTree();


    /**
     * 根据菜单的id进行逻辑删除
     * @param ids
     */
    void removeMenuByIds(List<Long> ids);


    /**
     * 找到catelogId的完整路径；
     * [父/子/孙]
     *
     * @param catelogId
     * @return
     */
    Long[] findCatelogPath(Long catelogId);


    void updateCascade(CategoryEntity category);

}

