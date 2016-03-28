package com.ants.project.fast.app.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.ants.project.core.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * 动态调用Service里面的方法
 * 前台必须传入Service名称和方法名称以及数据对象
 *
 * @author liushun
 * @version 1.0
 * @Date 2016-03-10
 */

@Controller
@RequestMapping("/v{version}")
public class DynamicCallController {

    @Autowired
    private Validator validator;

    private static ApplicationContext applicationContext = null;

    static {
        applicationContext = new ClassPathXmlApplicationContext("application.xml");
    }

    /* URL请求匹配
     * 利用反射动态调用类和方法带数据校验
     * 多例模式
     * @param version 接口版本
     * @param service 业务名称
     * @param method 业务方法
     * @param data Json数据对象
     * @param bean 校验bean对象
     * @return
     */
    @RequestMapping(value = "/{service}/{method}", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@PathVariable String version
            , @PathVariable String service
            , @PathVariable String method
            , String jsonData
            , String validBean) {
        long startTime = System.currentTimeMillis();
        Object beanObj = null; // 方法参数当校验时为JavaBean 不校验时为HashMap
        Method m = null; // 反射方法名称
        try {

            Class<?> classzs = Class.forName("com.ants.project.fast.app.v" + version + ".service." + service + "Service");
            Object bean = applicationContext.getBean(classzs);
            if (StringUtils.isEmpty(validBean)) {
                if(StringUtils.isEmpty(jsonData)) {
                    try {
                        m = bean.getClass().getMethod(method);
                    } catch (NoSuchMethodException e) {
                        return ResponseUtils.jsonFail("method is no found!");
                    }
                    try {
                        Object obj = m.invoke(bean);
                        return ResponseUtils.jsonSuccess(obj, startTime);
                    } catch (IllegalAccessException e) {
                        return ResponseUtils.jsonFail("method parameters is error!");
                    } catch (InvocationTargetException e) {
                        return ResponseUtils.jsonFail("method call is exception!");
                    }
                }else {
                    try {
                        beanObj = JSON.parseObject(jsonData, Map.class);
                    } catch (Exception e) {
                        return ResponseUtils.jsonFail("parameters is error , no json!");
                    }

                    try {
                        m = bean.getClass().getMethod(method, Map.class);
                    } catch (NoSuchMethodException e) {
                        return ResponseUtils.jsonFail("method is no found!");
                    }
                }
            } else {
                Class<?> beazs = Class.forName("com.ants.project.fast.app.bean." + validBean);
                try {
                    beanObj = JSON.parseObject(jsonData, beazs);
                } catch (IllegalArgumentException e) {
                    return ResponseUtils.jsonFail("parameters is error , no json!");
                }
                Set<ConstraintViolation<Object>> constraintViolations = validator
                        .validate(beanObj);//验证某个对象,，其实也可以只验证其中的某一个属性的}
                for (ConstraintViolation<Object> constrain : constraintViolations) {
                    String message = constrain.getMessage();
                    return ResponseUtils.jsonFail(constrain.getPropertyPath()+" -> "+message);
                }

                try {
                    m = bean.getClass().getMethod(method, beanObj.getClass());
                } catch (NoSuchMethodException e) {
                    return ResponseUtils.jsonFail("method is no found!");
                }
            }
            try {
                Object obj = m.invoke(bean, beanObj);
                return ResponseUtils.jsonSuccess(obj, startTime);
            } catch (IllegalAccessException e) {
                return ResponseUtils.jsonFail("method parameters is error!");
            } catch (InvocationTargetException e) {
                return ResponseUtils.jsonFail("method call is exception!");
            }
        } catch (ClassNotFoundException e) {
            return ResponseUtils.jsonFail(service+" is no found !");
        }
    }
}
