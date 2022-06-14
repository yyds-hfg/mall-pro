package com.hacker.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hacker.dao.CommentReplayDao;
import com.hacker.entity.CommentReplayEntity;
import com.hacker.service.CommentReplayService;


@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplayEntity> implements CommentReplayService {


}