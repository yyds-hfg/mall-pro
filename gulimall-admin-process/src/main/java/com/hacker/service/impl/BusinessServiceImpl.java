package com.hacker.service.impl;

import com.hacker.po.Business;
import com.hacker.mapper.BusinessMapper;
import com.hacker.service.BusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zero
 * @since 2022-06-01
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

}
