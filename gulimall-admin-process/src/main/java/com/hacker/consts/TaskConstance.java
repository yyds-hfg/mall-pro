package com.hacker.consts;

import lombok.Data;

/**
 * @Author: Zero
 * @Date: 2022/5/25 15:55
 * @Description:
 */
@Data
public class TaskConstance {
    /**
     * 1：起草节点
     */
    public static String REJECT_TO_START ="1";
    /**
     * 2：上一节点
     */
    public static String REJECT_TO_LAST ="2";
    /**
     * 3：目标节点
     */
    public static String REJECT_TO_TARGET ="3";


    /**
     * 1：往前跳转
     */
    public static String JUMP_FORWARD ="1";
    /**
     * 2：往回跳转
     */
    public static  String JUMP_BACK ="2";
}
