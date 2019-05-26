package pers.zhzh.logspringboot;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import pers.zhzh.logspringboot.annotations.LogForController;

public class LogForControllerPointcutAdvisor extends AbstractPointcutAdvisor {

    public Pointcut getPointcut() {
        return AnnotationMatchingPointcut.forMethodAnnotation(LogForController.class);
    }

    public Advice getAdvice() {
        return new LogForControllerPointcutAdvice();
    }
}
