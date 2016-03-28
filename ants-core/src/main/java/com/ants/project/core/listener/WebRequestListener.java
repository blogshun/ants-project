package com.ants.project.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-04
 */

@WebListener
public class WebRequestListener implements ServletRequestListener {

    private final Logger logger  =  LoggerFactory.getLogger(ServletRequestListener.class);

    //添加Request事件
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        logger.debug("request add attribute :: {key:{},value:{}}",servletRequestAttributeEvent.getName(), servletRequestAttributeEvent.getValue());
    }

    //移除Requset事件
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        logger.debug("request remove attribute :: {key:{},value:{}}",servletRequestAttributeEvent.getName(), servletRequestAttributeEvent.getValue());
    }

    //销毁Request事件
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logger.debug("request destroy :: success !");
    }

    //初始化Request事件
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        logger.debug("request initialize :: success !");
    }
}
