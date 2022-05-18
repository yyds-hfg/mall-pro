package com.hacker.common.config;

import com.hacker.common.utils.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zero
 * @Date: 2022/5/16 11:03
 * @Description:
 */
@Configuration
public class SpringContextConfig {
    /**
     * 必须要把它加入到Spring容器中
     * @return SpringContextUtil
     */
    @Bean
    public SpringContextUtil getSpringContextUtil() {
        return new SpringContextUtil();
    }

}
