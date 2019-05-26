package pers.zhzh.logspringboot;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.zhzh.logspringboot.annotations.LogForTimeConsumer;
import pers.zhzh.logspringboot.constant.ResultType;
import pers.zhzh.logspringboot.util.ParseUtil;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LogForTimeConsumerPointcutAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = methodInvocation.proceed();
        long end = System.currentTimeMillis();
        Method method = methodInvocation.getMethod();
        Logger logger = LoggerFactory.getLogger(method.getDeclaringClass());
        String args = Arrays.toString(methodInvocation.getArguments());
        LogForTimeConsumer logForTimeConsumer0 = method.getDeclaringClass().getDeclaredAnnotation(LogForTimeConsumer.class);
        LogForTimeConsumer logForTimeConsumer = method.getDeclaredAnnotation(LogForTimeConsumer.class);
        long milliseconds = this.getMillisecond(logForTimeConsumer0,logForTimeConsumer);
        if(end - start > milliseconds){
            String description = this.getDescription(logForTimeConsumer0,logForTimeConsumer);
            boolean resultNeed = this.getResultNeed(logForTimeConsumer0,logForTimeConsumer);
            if("".equals(description)){
                logger.info("method: {}, args: {}", method.getName(), args);
                logger.info("method: {}, execute : {} milliseconds", method.getName(), end - start);
                if(resultNeed){
                    int resultType = this.getResultType(logForTimeConsumer0,logForTimeConsumer);
                    logger.info("method: {}, result : {}", method.getName(), ParseUtil.toJSONByType(result,resultType));
                }
            }else{
                logger.info("description: {}, method: {}, args: {}", description, method.getName(), args);
                logger.info("description: {}, method: {}, execute : {} milliseconds", description, method.getName(), end - start);
                if(resultNeed){
                    int resultType = this.getResultType(logForTimeConsumer0,logForTimeConsumer);
                    logger.info("description: {}, method: {}, result : {}", description, method.getName(), ParseUtil.toJSONByType(result,resultType));
                }
            }
        }
        return result;
    }

    private String getDescription(LogForTimeConsumer logForTimeConsumer0, LogForTimeConsumer logForTimeConsumer){
        String description = logForTimeConsumer.description();
        if(logForTimeConsumer0 == null) return description;
        String description0 = logForTimeConsumer0.description();
        if("".equals(description0) && "".equals(description)){
            return "";
        }
        if("".equals(description0) && !"".equals(description)){
            return description;
        }
        if(!"".equals(description0) && "".equals(description)){
            return description0;
        }
        return description0 + "(" +  description + ")";
    }

    private long getMillisecond(LogForTimeConsumer logForTimeConsumer0, LogForTimeConsumer logForTimeConsumer){
        long milliseconds = logForTimeConsumer.milliseconds();
        if(logForTimeConsumer0 == null) return milliseconds;
        if(milliseconds == 0L){
            milliseconds = logForTimeConsumer0.milliseconds();
        }
        return milliseconds;
    }

    private boolean getResultNeed(LogForTimeConsumer logForTimeConsumer0, LogForTimeConsumer logForTimeConsumer){
        boolean resultNeed = logForTimeConsumer.resultNeed();
        if(logForTimeConsumer0 == null) return resultNeed;
        if(!resultNeed){
            resultNeed = logForTimeConsumer0.resultNeed();
        }
        return resultNeed;
    }

    private int getResultType(LogForTimeConsumer logForTimeConsumer0, LogForTimeConsumer logForTimeConsumer){
        int resultType = logForTimeConsumer.resultType();
        if(logForTimeConsumer0 == null) return resultType;
        if(resultType == ResultType.DEFAULT){
            resultType = logForTimeConsumer0.resultType();
        }
        return resultType;
    }
}
