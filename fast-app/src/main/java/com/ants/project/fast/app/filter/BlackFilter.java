package com.ants.project.fast.app.filter;
import com.ants.project.fast.app.config.BlackList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 黑名单过滤器
 * 屏蔽请求或IP设置
 * @author liushun
 * @version 1.0
 * @Date 2016-03-04
 */

@WebFilter(filterName="blackFilter", urlPatterns="/*")
public class BlackFilter implements Filter{

    private final Logger logger  =  LoggerFactory.getLogger(BlackFilter.class);

    //要拦截的url地址
    private String[] urlList = null;

    //要拦截的ip地址
    private String[] ipList = null;

    //跳转到错误地址
    private String errorURL = null;

    //网站根路径
    private String webPath = null;

    //服务或方法适合API接口
    private String[] serviceMethod = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        BeanFactory beans = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        BlackList blackList = beans.getBean(BlackList.class);
        this.urlList = blackList.getUrlList().split(",");
        this.ipList = blackList.getIpList().split(",");
        this.serviceMethod = blackList.getServiceMthod().split(",");
        this.errorURL = blackList.getUrlError();
        this.webPath =  filterConfig.getServletContext().getContextPath();
        logger.debug("BlackFilter is initializing ... ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        if(isFilter(request))
            filterChain.doFilter(request, response);
        else
            response.sendRedirect(webPath+errorURL);
    }

    @Override
    public void destroy() {

    }

    //判断是否拦截
    public boolean isFilter(HttpServletRequest request){
        boolean flag = true;
        if(urlList!=null&&!urlList[0].equals("")) {
            //匹配URI
            for (String uri : urlList) {
                if ((webPath + uri).equalsIgnoreCase(request.getRequestURI())){
                    flag = false;
                    break;
                }
            }
        }
        if(ipList!=null&&!ipList[0].equals("")) {
            //匹配IP
            for (String ip : ipList) {
                if (ip.equalsIgnoreCase(request.getRemoteAddr())) {
                    flag = false;
                    break;
                }
            }
        }
        if(serviceMethod!=null&&!serviceMethod[0].equals("")) {
            String uri = request.getRequestURI();
            for (String sm : serviceMethod) {
                String service = sm.split("\\.")[0];
                String method = sm.split("\\.")[1];
                if("*".equals(method)){
                    if(uri.indexOf(service)!=-1){
                        flag = false;
                        break;
                    }
                }else if (uri.indexOf(service)!=-1&&uri.indexOf(method)!=-1) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
