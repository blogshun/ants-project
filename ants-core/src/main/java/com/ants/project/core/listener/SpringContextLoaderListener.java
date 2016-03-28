package com.ants.project.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * web 容器启动加载spring监听器 通过注解来进行配置
 * 默认配置文件为classpath:application.xml 可以省略
 * @author liushun
 * @version 1.0
 * @Date 2016-01-20
 */

@WebListener
public class SpringContextLoaderListener extends ContextLoaderListener {

    private final  Logger logger  =  LoggerFactory.getLogger(SpringContextLoaderListener.class);

    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
        logger.debug(">>> Web Spring :: 初始化完成 Success!");
        if(servletContext.getInitParameter("contextConfigLocation")==null)
            servletContext.setInitParameter("contextConfigLocation", "classpath:application.xml");
        return super.initWebApplicationContext(servletContext);
    }
}
