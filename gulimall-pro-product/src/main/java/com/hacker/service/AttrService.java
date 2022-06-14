package com.hacker.service;

import com.hacker.vo.AttrGroupRelationVo;
import com.hacker.vo.AttrRespVo;
import com.hacker.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.entity.AttrEntity;

import java.util.List;

/**
 * 商品属性
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface AttrService extends IService<AttrEntity> {


    void saveAttr(AttrVo attr);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);


}

