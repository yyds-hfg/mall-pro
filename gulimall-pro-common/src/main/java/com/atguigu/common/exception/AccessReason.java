package com.atguigu.common.exception;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Getter;

/**
 * @author: Zero
 * @Date: 2022/5/11 21:08
 * @Description:运行时异常访问报错原因
 */
public enum AccessReason {

    /**
     * 参数检查异常
     */
    PARAM_CHECK_EXCEPTION("500001","参数异常异常"),

    /**
     * 类型检查异常
     */
    TYPE_CHECK_EXCEPTION("500002","类型检查异常"),

    RATE_LIMITER("500003","接口被限流了");

    /**
     * code
     */
    @Getter
    private final String errorCode;

    /**
     * 异常描述
     */
    @Getter
    private final String errorMsg;

    /**
     * @param errorCode 错误吗
     * @param errorMsg 错误信息
     */
    AccessReason(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     *
     * @return
     */
    public AccessException exception() {
        return new AccessException(this);
    }

    /**
     *
     * @param errorMsg
     * @return
     */
    public AccessException exception(String errorMsg) {
        return new AccessException(this,errorMsg);
    }

}
