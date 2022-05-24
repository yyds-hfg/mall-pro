package com.hacker;

import com.hacker.service.ProcessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Zero
 * @Date: 2022/5/24 10:00
 * @Description:
 */
@SpringBootTest
public class ProcessServiceTest {
    @Autowired
    private ProcessService processService;

    @Test
    public void testQueryTaskAgents() {
        String businessKey = "ZT_7238hfd83443";
        processService.queryTaskAgents(businessKey);
    }

}
