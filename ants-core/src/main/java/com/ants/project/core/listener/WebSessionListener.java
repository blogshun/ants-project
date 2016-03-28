package com.ants.project.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-04
 */
@WebListener
public class WebSessionListener implements HttpSessionListener {

    private final Logger logger  =  LoggerFactory.getLogger(HttpSessionListener.class);

    //添加Session事件
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        logger.debug("session add attribute :: {key:{},value:{}}",httpSessionBindingEvent.getName(), httpSessionBindingEvent.getValue());
    }

    //移除Session事件
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        logger.debug("session remove attribute :: {key:{},value:{}}",httpSessionBindingEvent.getName(), httpSessionBindingEvent.getValue());
    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.debug("session create :: success !");
    }

    //销毁Session事件
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.debug("session destroy :: success !");
    }

}
