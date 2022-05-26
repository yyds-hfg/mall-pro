package com.hacker.common.result;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Zero
 * @Date: 2022/3/19 11:34
 * @Description:
 */
@Data
@Slf4j
@SuppressWarnings("unchecked")
@NoArgsConstructor
public class R<T> {

    private String code;

    private String message;

    private T data;

    //构造方法私有化 别人不能使用该类new方法
    private R(ResultCode resultCode) {
        code = resultCode.getCode();
        message = resultCode.getMessage();
    }

    //静态方法
    public static <V> R<V> ok() {
        return new R<>(ResultCode.SUCCESS);
    }

    //静态方法
    public static <V> R<V> ok(V theData) {
        final R<V> ok = ok();
        ok.data = theData;
        return ok;
    }

    public static R<?> error() {
        return new R<>(ResultCode.DEFAULT_ERROR);
    }

    public static R<?> error(ResultCode resultCode) {
        return new R<>(resultCode);
    }

    public static R<?> error(Throwable e) {
        final R<?> result = error();
        result.message = e.getMessage();
        log.error("异常信息:" + JSONUtil.toJsonStr(e));
        return result;
    }


    public static R<?> run(Runnable runnable) {
        try {
            runnable.run();
            return ok();
        } catch (Throwable e) {
            log.error("异常信息:" + JSONUtil.toJsonStr(e));
            return error(e);
        }
    }

    /**
     * @param runnable 有返回值得
     * @param <V>      v
     * @return v
     */
    public static <V> R<V> run(RunnableAndGetResult<V> runnable) {
        try {
            return ok(runnable.run());
        } catch (Throwable e) {
            log.error("异常信息:" + JSONUtil.toJsonStr(e));
            return (R<V>) error(e);
        }
    }


}


