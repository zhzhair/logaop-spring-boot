package com.example.demo.config.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAspect {

    @Pointcut(value = "execution(* com.example.demo.*.controller.*.*(..))")
    public void logPointCut() {

    }

    /**
     * 记录controller日志环绕通知
     */
    @Around(value = "logPointCut() && @annotation(org.springframework.web.bind.annotation.RequestMapping) && !@annotation(com.example.demo.config.annotation.NotAutoLog)")
    public Object autoLogRecord(ProceedingJoinPoint pjp) throws Throwable {
//        获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes ra = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = ra.getRequest();
//        http请求的方法
        String method = request.getMethod();
//        请求路径
        String servletPath = request.getServletPath();

        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        获取参数名称列表
        String[] parameterNames = signature.getParameterNames();
//        获取方法名
        String name = signature.getName();
//        切入的类
        Class declaringType = signature.getDeclaringType();
//        日志对象
        Logger logger = LoggerFactory.getLogger(declaringType);
//        获取参数列表
        Object[] args = pjp.getArgs();
        Object[] newArr = new Object[args.length+2];
        StringBuilder var1 = new StringBuilder("前端调用方法开始----"+ name + "---->：#{\"URL地址\":{}, \"HTTP方法\":{}，参数：");
        if(args.length != 0) {
            System.arraycopy(args,0,newArr,2, args.length);
            for (String s : parameterNames) {
                var1.append(", \"").append(s).append("\":{}");
            }
        }
        var1.append("}");
        newArr[0] = servletPath;
        newArr[1] = method;
//          记录日志
        logger.info(var1.toString(),newArr);
        Object proceed = pjp.proceed();
        logger.info("前端调用方法结束----"+ name +"---->：返回值: {}",proceed);
        return proceed;
    }

}
