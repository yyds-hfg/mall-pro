package com.hacker.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Zero
 * @Date: 2022/5/11 21:11
 * @Description:
 */
public class AccessException extends RuntimeException {

    /**
     * 自定义异常信息
     */
    public String errorMsg;

    /**
     * 返回异常实列
     * @param accessReason accessReason
     */
    public AccessException(AccessReason accessReason) {
        this(accessReason, accessReason.getErrorMsg());
    }

    /**
     * 返回异常实列
     * @param accessReason  accessReason
     * @param errorMsg errorMsg
     */
    public AccessException(AccessReason accessReason, String errorMsg) {
        super(!StringUtils.isNotBlank(errorMsg) ? errorMsg : accessReason.getErrorMsg());
        this.errorMsg = errorMsg;
    }

}
