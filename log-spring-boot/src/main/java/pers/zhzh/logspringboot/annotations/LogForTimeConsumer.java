package pers.zhzh.logspringboot.annotations;

import pers.zhzh.logspringboot.constant.ResultType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 统计方法执行时间的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD,ElementType.TYPE})
public @interface LogForTimeConsumer {
    long milliseconds() default 0L;
    String description() default "";
    boolean resultNeed() default false;
    int resultType() default ResultType.DEFAULT;
}
