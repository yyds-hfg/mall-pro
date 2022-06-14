package com.hacker.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @Author: Zero
 * @Date: 2022/5/27 08:46
 * @Description:
 */
public class StrUtils extends StringUtils {

    /**
     * 所有都不能为空
     *
     * @param str 字符串数组
     * @return
     */
    public static Boolean isAllNotBlank(String... str) {
        if (str == null) {
            return false;
        }
        return Arrays.stream(str).noneMatch(StringUtils::isNotBlank);
    }

    /**
     * 不全为空
     * @param str
     * @return
     */
    public static Boolean isNotAllBlank(String... str) {
        if (str==null) {
            return false;
        }
        for (String s : str) {
            if (!StringUtils.isBlank(s)) {
                return true;
            }
        }
        return false;
    }

}
