package com.atguigu.common.result;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ResultT<T> {

    private String code;

    private String message;

    private T data;

    //构造方法私有化 别人不能使用该类new方法
    private ResultT(ResultCode resultCode){
        code = resultCode.getCode();
        message = resultCode.getMessage();
    }

    //静态方法
    public static <V>  ResultT<V> ok(){
        return new ResultT<>(ResultCode.SUCCESS);
    }

    //静态方法
    public static <V>  ResultT<V> ok(V theData){
        final ResultT<V> ok = ok();
        ok.data = theData;
        return ok;
    }

    public static ResultT<?> error(){
        return new ResultT<>(ResultCode.DEFAULT_ERROR);
    }

    public static ResultT<?> error(ResultCode resultCode){
        return new ResultT<>(resultCode);
    }

    public static ResultT<?> error(Throwable e){
        final ResultT<?> result = error();
        result.message = e.getMessage();
        log.error("异常信息:"+JSONUtil.toJsonStr(e));
        return result;
    }


    public static ResultT<?> run(Runnable runnable){
        try{
            runnable.run();
            return ok();
        }catch (Throwable e){
            log.error("异常信息:"+JSONUtil.toJsonStr(e));
            return error(e);
        }
    }

    public static <V>  ResultT<V> run(RunnableAndGetResult<V> runnable){
        try{
            return ok(runnable.run());
        }catch (Throwable e){
            log.error("异常信息:"+JSONUtil.toJsonStr(e));
            return (ResultT<V>)error(e);
        }
    }
}


