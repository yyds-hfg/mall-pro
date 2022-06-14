package com.hacker.service;

import com.hacker.vo.AttrGroupWithAttrsVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.entity.AttrGroupEntity;

import java.util.List;

/**
 * 属性分组
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {


    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);

}

