package io.renren.common.validator;

import io.renren.common.exception.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @Author: Zero
 * @Date: 2022/6/2 15:23
 * @Description: 数据校验
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object,Exception e) throws Exception {
        if (object == null) {
            throw e;
        }
    }
}
