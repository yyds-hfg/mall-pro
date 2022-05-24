package com.hacker.result;

import lombok.Getter;

/**
 * @Author: Zero
 * @Date: 2022/4/20 08:30
 * @Description:
 */
@Getter
public enum ResultCode {

    SUCCESS("000000", "成功"),

    DEFAULT_ERROR("400000", "业务错误");

    private final String code;

    private final String message;

    ResultCode(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

}

