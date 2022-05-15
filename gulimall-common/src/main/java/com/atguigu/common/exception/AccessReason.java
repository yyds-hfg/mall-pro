package com.atguigu.common.exception;

/**
 * @Author: Zero
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
    TYPE_CHECK_EXCEPTION("500002","类型检查异常");

    /**
     * code
     */
    private String errorCode;

    /**
     * 异常描述
     */
    private String errorMsg;


    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }


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
