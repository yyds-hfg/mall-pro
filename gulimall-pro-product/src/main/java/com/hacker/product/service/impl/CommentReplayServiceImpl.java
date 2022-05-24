package com.hacker.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hacker.product.dao.CommentReplayDao;
import com.hacker.product.entity.CommentReplayEntity;
import com.hacker.product.service.CommentReplayService;


@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplayEntity> implements CommentReplayService {


}