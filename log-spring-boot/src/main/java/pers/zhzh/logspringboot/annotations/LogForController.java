package pers.zhzh.logspringboot.annotations;

import pers.zhzh.logspringboot.constant.ResultType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 入参出参写日志的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface LogForController {
    boolean argsNeed() default true;
    boolean resultNeed() default true;
    boolean millisecondNeed() default false;
    int resultType() default ResultType.DEFAULT;
}
