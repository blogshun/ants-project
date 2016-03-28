package com.ants.project.fast.site.controller;


import com.ants.project.fast.site.utils.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-04
 */

@Controller
public class DynamicURLController {

    private final Logger logger = LoggerFactory.getLogger("DynamicURLController");

    @Autowired
    private SystemConfig systemConfig;

    private final static String viewpath = "/WEB-INF/views";

    private final static String postfix = ".html";
    /**
     * 判断文件页面是否存在
     */
    public String randRequest(String urlPATH, HttpServletRequest request, Model model) {
        String template = systemConfig.getPCTemplate();
        String agent = request.getHeader("user-agent").toLowerCase();
        if (agent.indexOf("mobile") != -1)
            template = systemConfig.getWAPTemplate();
        ServletContext servletContext = request.getSession().getServletContext();
        String skin_page = template + urlPATH;
        String fileURL = servletContext.getRealPath(viewpath) +File.separator + skin_page + postfix;
        logger.debug("#### file url -> "+fileURL);
        File file = new File(fileURL);
        if (!file.exists()) {
            model.addAttribute("message", skin_page + "模板文件不存在!");
            return null;
        } else {
            request.setAttribute("ctx", request.getContextPath());
            return template;
        }
    }

    /**
     * 判断是否是手机访问
     *
     * @param request
     * @return
     */
    public boolean isPhone(String isPhoneGet, HttpServletRequest request) {
        if ("1".equals(isPhoneGet))
            return true;
        else {
            String agent = request.getHeader("user-agent").toLowerCase();
            if (agent.indexOf("mobile") != -1)
                return false;
            return true;
        }
    }

    /**
     * 一级目录匹配
     *
     * @param page    页面名称
     * @param model   数据模型
     * @param request
     * @return
     */
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String page(@PathVariable String page
            , Model model
            , HttpServletRequest request) {
        String urlPATH = File.separator + page;
        String template = randRequest(urlPATH, request, model);
        if (template == null)
            return "/error/404";
        return template + urlPATH;
    }

    /**
     * 二级目录匹配
     *
     * @param module  模块名称
     * @param page    页面名称
     * @param model   数据模型
     * @param request
     * @return
     */
    @RequestMapping(value = "/{module}/{page}", method = RequestMethod.GET)
    public String module_page(@PathVariable String module
            , @PathVariable String page
            , Model model
            , HttpServletRequest request) {
        String urlPATH = File.separator + module + File.separator + page;
        String template = randRequest(urlPATH, request, model);
        if (template == null)
            return "/error/404";
        return template + urlPATH;
    }
}
