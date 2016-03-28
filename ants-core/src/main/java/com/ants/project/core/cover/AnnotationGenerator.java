package com.ants.project.core.cover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * 自定义生成注解防止注解名称相同抛出异常
 * 项目启动可以打印生成实例名称，查看相关实例是否已经生成
 * @author liushun
 * @version 1.0
 * @Date 2015-12-30
 */
public class AnnotationGenerator extends AnnotationBeanNameGenerator {

    private final Logger logger  =  LoggerFactory.getLogger(AnnotationGenerator.class);


    /**
     * 重写策略
     * @param definition 包元素对象
     * @return
     */
    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String[] packages = definition.getBeanClassName().split("\\.");
        String annotationName = packages[packages.length-3]+"_"+packages[packages.length-2]+"_"+packages[packages.length-1];
        logger.debug(">>> Success :: "+ annotationName+" Generator!");
        return annotationName;
    }
}
