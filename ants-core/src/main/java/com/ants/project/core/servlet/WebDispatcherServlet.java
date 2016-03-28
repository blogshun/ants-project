package com.ants.project.core.servlet;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * springMVC视图派发器
 * @author liushun
 * @version 1.0
 * @Date 2016-03-04
 */
@WebServlet(name = "/dispatcherServlet"
        , urlPatterns = "/"
        , initParams = {@WebInitParam(name = "contextConfigLocation", value = "classpath:spring-mvc.xml")}
        , loadOnStartup = 1)
public class WebDispatcherServlet extends DispatcherServlet {

}
