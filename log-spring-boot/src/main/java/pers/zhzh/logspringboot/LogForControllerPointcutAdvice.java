package pers.zhzh.logspringboot;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.zhzh.logspringboot.annotations.LogForController;
import pers.zhzh.logspringboot.util.ParseUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LogForControllerPointcutAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        if(clazz.getDeclaredAnnotation(RestController.class) != null || clazz.getDeclaredAnnotation(Controller.class) != null){
            LogForController logForController = method.getDeclaredAnnotation(LogForController.class);
            boolean argsNeed = logForController.argsNeed();
            boolean resultNeed = logForController.resultNeed();
            boolean millisecondNeed = logForController.millisecondNeed();
            //获取request
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        http请求的方法
            ServletRequestAttributes ra = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request;
//        请求路径
            request = ra.getRequest();
            String servletPath = request.getServletPath();
            Logger logger = LoggerFactory.getLogger(method.getDeclaringClass());
            if(argsNeed){
                logger.info("path: {}, args: {}", servletPath, Arrays.toString(methodInvocation.getArguments()));
            }
            long start = System.currentTimeMillis();
            Object result = methodInvocation.proceed();
            if(millisecondNeed){
                long end = System.currentTimeMillis();
                logger.info("path: {}, execute : {} milliseconds", servletPath, end - start);
            }
            if(resultNeed){
                int resultType = logForController.resultType();
                logger.info("path: {}, result : {}", servletPath, ParseUtil.toJSONByType(result,resultType));
            }
            return result;
        }
        return null;
    }
}
