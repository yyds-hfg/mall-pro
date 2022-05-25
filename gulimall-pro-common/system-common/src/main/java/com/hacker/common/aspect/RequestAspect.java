package com.hacker.common.aspect;

import com.hacker.common.exception.AccessReason;
import com.google.common.util.concurrent.RateLimiter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @Author: Zero
 * @Date: 2022/5/17 18:34
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class RequestAspect {

//    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(3);

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.hacker.common.annotation.SystemLog)")
    public void logPointCut() {

    }

    /**
     * @Before("log()") public void before(JoinPoint joinPoint) {
     * //清理线程变量
     * startTime.remove();
     * setRequestTime();
     * }
     * <p>
     * private void setRequestTime() {
     * log.info("清除本地线程变量");
     * //记录请求时间
     * startTime.set(Instant.now().toEpochMilli());
     * }
     */

    @Around("logPointCut()")
    public Object aroundFilter(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("--------------------------日志记录--------------------------");
        if (!RATE_LIMITER.tryAcquire()) {
            throw AccessReason.RATE_LIMITER.exception("接口流量超限");
        }
        long startTime = Instant.now().toEpochMilli();
        //执行方法
        Object proceed = joinPoint.proceed();

        long endTime = Instant.now().toEpochMilli() - startTime;
        log.info(joinPoint.getSignature().getName() + "执行了" + (endTime - startTime) + "秒");
        log.info("--------------------------日志记录--------------------------");
        return proceed;
    }

}
