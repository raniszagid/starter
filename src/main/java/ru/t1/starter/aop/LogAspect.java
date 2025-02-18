package ru.t1.starter.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.event.Level;

@Slf4j
@Aspect
public class LogAspect {
    private final LogAspectConfig logAspectConfig;

    public LogAspect(LogAspectConfig logAspectConfig) {
        this.logAspectConfig = logAspectConfig;
    }

    @Pointcut("@annotation(LogAnnotation)")
    public void getPointcut() {}

    @Around("getPointcut()")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) {
        Object result = null;
        String methodName = joinPoint.getSignature().getName();
        Level level = logAspectConfig.getLevel();
        log.makeLoggingEventBuilder(level).log("Попытка вызова метода {}", methodName);
        try {
            result = joinPoint.proceed();
            log.makeLoggingEventBuilder(level).log("Метод {} успешно выполнен", methodName);
        } catch (Throwable e) {
            log.makeLoggingEventBuilder(level)
                    .log("Во время выполнения метода {} произошла ошибка: {}",
                            methodName,
                            e.getMessage());
            System.out.println(e.getMessage());
        }
        return result;
    }
}
