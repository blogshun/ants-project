package com.ants.project.core.filter;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 配置整个项目启动时候的编码
 * @author liushun
 * @version 1.0
 * @Date 2016-03-04
 */

@WebFilter(filterName="encodingFilter"
        , urlPatterns="/*"
        , initParams = {@WebInitParam(name="encoding", value = "utf-8")
        , @WebInitParam(name="forceEncoding", value = "true")})
public class EncodingFilter extends CharacterEncodingFilter{

}
