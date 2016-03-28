package com.ants.project.core.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 用来监控方法的执行时间-- 对应配置文件是spring-method-aop.xml
 * @author wangyong
 */
public class MethodTimeAdvice implements MethodInterceptor {

    private final Logger logger = LoggerFactory.getLogger("Method-Action");

    /**
     * @throws Exception
     * @see MethodInterceptor#invoke(MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //用 commons-lang 提供的 StopWatch 计时，Spring 也提供了一个 StopWatch
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始  
        //监控的类名
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();
        //监控的方法名  
        String methodName = className + "." + invocation.getMethod().getName();
        //监控的参数
        Object[] objs = invocation.getArguments();
        Object result = null;
        try{
            //这个是我们监控的bean的执行并返回结果
            result = invocation.proceed();
        }catch(Throwable e){
            logger.error("["+methodName+"] = >> Transaction Rollback 参数 = >> " + getString(objs));
            logger.error("===================  Service层异常 已经回滚事务 = >> "+methodName+"  ====================");
            throw new RuntimeException();
        }
        clock.stop(); //计时结束  
        if (logger.isInfoEnabled()) {
            logger.debug("执行时间:" + clock.getTime() + " ms [" + methodName + "]");
        }
        return result;
    }

    /**
     * 这个类主要是用于输出方法的参数 
     *
     * @param objs
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getString(Object[] objs) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, len = objs.length; i < len; i++) {
            if (objs[i] instanceof String) {
                stringBuffer.append(objs[i].toString()+"(String类型) ");
            } else if (objs[i] instanceof Map) {
                HashMap<String, Object> hashMap = (HashMap<String, Object>) objs[i];
                HashMap<String, Object> map = hashMap;
                HashSet<String> set = (HashSet<String>) map.keySet();
                for (String str : set) {
                    stringBuffer.append(str + "=" + map.get(str));
                }
                stringBuffer.append("(Map类型) ");
            } else if (objs[i] instanceof Integer) {
                stringBuffer.append(objs[i].toString());
                stringBuffer.append("(Integer类型)");
            } else {
                stringBuffer.append(objs[i].toString());
            }
        }
        return stringBuffer.toString();
    }
}
