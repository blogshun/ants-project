//package com.ants.project.site.controller;
//
//import com.alibaba.druid.util.StringUtils;
//import com.alibaba.fastjson.JSON;
//import com.ants.project.core.utils.JsonParams;
//import com.ants.project.core.utils.ResponseUtils;
//import com.ants.project.vo.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Set;
//
///**
// * @author liushun
// * @version 1.0
// * @Date 2016-03-04
// */
//
//@Controller
//@RequestMapping("/v{version}")
//public class TestController {
//
//    @Autowired
//    private Validator validator;
//
//    private static ApplicationContext applicationContext = null;
//
//    static {
//        applicationContext = new ClassPathXmlApplicationContext("application.xml");
//    }
//
//
//    /**
//     * 利用反射动态调用类和方法带数据校验
//     * 多例模式
//     * @param json
//     * @return
//     */
//    public Object dynamicCall(JsonParams json){
//        long startTime = System.currentTimeMillis();
//        Object beanObj = null; // 方法参数当校验时为JavaBean 不校验时为HashMap
//        Method m =null; // 反射方法名称
//        try {
//            Class<?> classzs = Class.forName("com.ants.project.service.impl."+json.getService()+"ServiceImpl");
//            Object bean = applicationContext.getBean(classzs);
//            if(StringUtils.isEmpty(json.getBean())) {
//                beanObj = JSON.parseObject(json.getData(), HashMap.class);
//                m = bean.getClass().getMethod(json.getMethod(), HashMap.class);
//            }else{
//                Class<?> beazs = Class.forName("com.ants.project.vo." + json.getBean());
//                try {
//                    beanObj = JSON.parseObject(json.getData(), beazs);
//                } catch (IllegalArgumentException e) {
//                    return e.getMessage();
//                }
//                Set<ConstraintViolation<Object>> constraintViolations = validator
//                        .validate(beanObj);//验证某个对象,，其实也可以只验证其中的某一个属性的}
//                for (ConstraintViolation<Object> constrain : constraintViolations) {
//                    String message = constrain.getMessage();
//                    System.out.println(constrain.getPropertyPath()+"#####Err ### " + message);
//                    return constrain.getPropertyPath()+"#####Err ### " + message;
//                }
//                m = bean.getClass().getMethod(json.getMethod(), beanObj.getClass());
//            }
//            Object obj = m.invoke(bean, beanObj);
//            return ResponseUtils.jsonSuccess(obj, "测试实例成功", startTime);
//        } catch (Exception e) {
//
//            return ResponseUtils.jsonFail("调用出现异常", e.getMessage());
//        }
//    }
//
//    @RequestMapping(value="/list", method = RequestMethod.GET)
//    @ResponseBody
//    public Object list(@Validated JsonParams json
//            , BindingResult result){
//        if(result.hasErrors())
//            return ResponseUtils.jsonError(result.getFieldError().getField()+" -> "+result.getFieldError().getDefaultMessage());
//        return dynamicCall(json);
//    }
//
//
//    @RequestMapping(value="/dateils", method = RequestMethod.GET)
//    @ResponseBody
//    public Object dateils(@PathVariable String version
//            , @PathVariable String service
//            , @PathVariable String method
//            , Test test){
//        return null;
//    }
//
//    @RequestMapping(value="/delete", method = RequestMethod.GET)
//    @ResponseBody
//    public Object delete(@PathVariable String version
//            , @PathVariable String service
//            , @PathVariable String method
//            , Test test){
//        return null;
//    }
//
//    @RequestMapping(value="/create", method = RequestMethod.POST)
//    @ResponseBody
//    public Object create(@PathVariable String version
//            , @PathVariable String service
//            , @PathVariable String method
//            , Test test){
//        return null;
//    }
//
//    @RequestMapping(value="/modify", method = RequestMethod.POST)
//    @ResponseBody
//    public Object modify(@PathVariable String version
//            , @PathVariable String service
//            , @PathVariable String method
//            , Test test){
//        return null;
//    }
//
//
//
////    @RequestMapping(value="/b/{service}->{method}/list", method = RequestMethod.GET)
////    public String b(@PathVariable String service
////            , @PathVariable String method
////            , String name){
////        System.out.println("#################"+name);
////        return "demo";
////    }
//}
