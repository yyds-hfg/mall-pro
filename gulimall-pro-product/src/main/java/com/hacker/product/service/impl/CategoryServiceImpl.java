package com.hacker.product.service.impl;

import com.hacker.common.exception.AccessReason;
import com.hacker.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hacker.common.utils.PageUtils;
import com.hacker.common.utils.Query;

import com.hacker.product.dao.CategoryDao;
import com.hacker.product.entity.CategoryEntity;
import com.hacker.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        if (entities==null) {
            throw AccessReason.NULL_POINT_EXCEPTION.exception("商品分类为空");
        }
        return entities.stream()  //找到所有的一级分类
                    .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                    .peek((menu)-> menu.setChildren(getChildrens(menu,entities)))              //当前菜单的子分类
                    .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                    .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO  1、检查当前删除的菜单，是否被别的地方引用
        //逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    //[2,25,225]
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    /**
     * 级联更新所有关联的数据
     * @param category
     */
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    //225,25,2
    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        //1、收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);
        }
        return paths;
    }


    /**
     * 递归查找所有菜单的子菜单
     * @param root 当前菜单
     * @param all  所有菜单
     * @return 当前菜单子菜单
     */
    private List<CategoryEntity> getChildrens(@NotNull(message = "当前菜单不能为空") CategoryEntity root,
                                              @NotNull(message = "菜单列表不能为空") List<CategoryEntity> all){
        return all.stream().filter(categoryEntity -> categoryEntity.getParentCid()
                .equals(root.getCatId())).peek(categoryEntity ->
                    categoryEntity.setChildren(getChildrens(categoryEntity,all)))//1、找到子菜单
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

}