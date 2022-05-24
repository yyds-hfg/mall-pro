package com.hacker.service;

import com.hacker.domain.TaskInfo;

import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/5/24 18:50
 * @Description:
 */
public interface ProcessHistoryService {
    /**
     * 查询已办任务
     * @param userId
     * @return
     */
    public List<TaskInfo> getDoneTaskPage(String userId);
}
