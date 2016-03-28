package com.ants.project.core.validator;

/**
 * JSR303 Validator(Hibernate Validator)工具类.
 * <p/>
 * ConstraintViolation中包含propertyPath, message 和invalidValue等信息.
 * 提供了各种convert方法，适合不同的i18n需求:
 * 1. List<String>, String内容为message
 * 2. List<String>, String内容为propertyPath + separator + message
 * 3. Map<propertyPath, message>
 * JSR 验证定义详情: http://sishuok.com/forum/blogPost/list/7798.html
 * 详情见wiki: https://github.com/springside/springside4/wiki/HibernateValidator
 * @author liushun
 * @version 1.0
 * @Date 2016-01-06
 */
public class BeanValidators {


}
