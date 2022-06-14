package com.hacker.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hacker.common.annotation.Element;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zero
 * @Date: 2022/6/7 15:23
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Custr {

    private static final long serialVersionUID = 1L;

    @TableField("school_id")
    @Element(length = "10", order = "1")
    private String schoolId;

    @TableField("name")
    @Element(length = "3", order = "2")
    private String name;

    @TableField("age")
    @Element(length = "2", order = "3")
    private String age;

    @TableField("address")
    @Element(length = "6", order = "4")
    private String address;

    @TableField("school")
    @Element(length = "6", order = "5")
    private String school;

    public static final String BUSINESS_KEY = "business_key";

    public static final String PROCESS_INSTANCE_ID = "process_instance_id";

    public static final String BUSINESS_TYPE = "business_type";

    public static final String BUSINESS_TITLE = "business_title";

    public static final String BUSINESS_STATER = "business_stater";

    public static final String CREATE_TIME = "create_time";
}
