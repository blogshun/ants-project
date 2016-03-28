package com.ants.project.core.listener;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.InputStream;

/**
 * 容器启动、销毁 ,Session添加、移除、销毁, Request添加、移除、销毁
 * @author liushun
 * @version 1.0
 * @Date 2015-12-29
 */
@WebListener
public class WebContextListener implements ServletContextListener{

    private final  Logger logger  =  LoggerFactory.getLogger(WebContextListener.class);

    /**
     * 服务容器启动初始化加加载日志文件
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //系统启动加载日志配置文件
        InputStream is = this.getClass().getResourceAsStream("/logback.xml");
        try {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(is);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("加载logback.xml 日志配置文件加载异常...",e);
            System.exit(0);
        }

        logger.debug(">>> Web :: 容器初始化完成 Success!");
    }

    /**
     * 服务容器销毁
     * @param servletContextEvent
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug(">>> Web :: 容器销毁 Success !");
    }

}
