package com.hacker.service.impl;

import com.hacker.po.User;
import com.hacker.mapper.UserMapper;
import com.hacker.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
