package com.hacker.common.annotation;

import io.swagger.annotations.Api;

import javax.validation.groups.Default;

/**
 * @Author: Zero
 * @Date: 2022/5/16 15:38
 * @Description: 验证分类 可以自定义分类在Type接口修改
 */
public interface Type {

    @Api(value = "添加组")
    interface Add extends Default {
    }

    @Api(value = "更新组")
    interface Update extends Default {
    }

    @Api(value = "删除组")
    interface Delete extends Default {
    }

    @Api(value = "查询组")
    interface Query extends Default {
    }

}
