package com.hacker.common.exception;

import org.apache.commons.lang.StringUtils;

/**
 * @Author: Zero
 * @Date: 2022/5/11 21:11
 * @Description:
 */
public class AccessException extends RuntimeException {

    /**
     * 返回accessReason
     */
    private AccessReason accessReason;

    /**
     * 自定义异常信息
     */
    public String errorMsg;

    /**
     *
     */
    public AccessException(AccessReason accessReason) {
        this(accessReason,accessReason.getErrorMsg());
    }


    public AccessException(AccessReason accessReason, String errorMsg) {
        super(!StringUtils.isNotBlank(errorMsg) ?errorMsg:accessReason.getErrorMsg());
        this.accessReason = accessReason;
        this.errorMsg = errorMsg;
    }
}
