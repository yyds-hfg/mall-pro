package com.hacker.common.constant;

import java.nio.charset.Charset;

/**
 * @Author: Zero
 * @Date: 2022/6/15 09:35
 * @Description: 字符集编码常量
 */
public final class StandardCharsets {

    private StandardCharsets() {
        throw new AssertionError("No java.nio.charset.StandardCharsets instances for you!");
    }


    public static final Charset US_ASCII = java.nio.charset.StandardCharsets.US_ASCII;


    public static final Charset ISO_8859_1 = java.nio.charset.StandardCharsets.ISO_8859_1;


    public static final Charset UTF_8 = java.nio.charset.StandardCharsets.UTF_8;


    public static final Charset UTF_16BE = java.nio.charset.StandardCharsets.UTF_16BE;


    public static final Charset UTF_16LE = java.nio.charset.StandardCharsets.UTF_16LE;


    public static final Charset UTF_16 = java.nio.charset.StandardCharsets.UTF_16;

    public static final Charset GBK = Charset.forName("GBK");

}
