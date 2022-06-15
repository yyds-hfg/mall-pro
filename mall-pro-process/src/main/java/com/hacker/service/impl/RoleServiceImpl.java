package com.hacker.service.impl;

import com.hacker.po.Role;
import com.hacker.mapper.RoleMapper;
import com.hacker.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
