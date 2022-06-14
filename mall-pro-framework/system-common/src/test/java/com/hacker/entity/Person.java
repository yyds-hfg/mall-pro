package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hacker.common.annotation.Element;
import lombok.Data;

/**
 * @Author: Zero
 * @Date: 2022/6/9 18:38
 * @Description:
 */
@Data
public class Person {

    @TableField("name")
    @Element(length = "5", order = "1")
    private String name;

    @TableField("address")
    @Element(length = "5", order = "2")
    private String address;
}
