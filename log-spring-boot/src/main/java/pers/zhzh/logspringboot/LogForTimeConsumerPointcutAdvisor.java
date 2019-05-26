package pers.zhzh.logspringboot;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import pers.zhzh.logspringboot.annotations.LogForTimeConsumer;

public class LogForTimeConsumerPointcutAdvisor extends AbstractPointcutAdvisor {

    public Pointcut getPointcut() {
        return AnnotationMatchingPointcut.forMethodAnnotation(LogForTimeConsumer.class);
    }

    public Advice getAdvice() {
        return new LogForTimeConsumerPointcutAdvice();
    }
}
