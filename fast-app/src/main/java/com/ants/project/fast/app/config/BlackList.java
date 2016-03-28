package com.ants.project.fast.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-04
 */

@Service
public class BlackList {

    //拦截URI已逗号分割
    @Value("${black.url.list:}")
    private String urlList;

    //拦截IP已逗号分割
    @Value("${black.ip.list:}")
    private String ipList;

    //拦截Service名称
    @Value("${black.service.list:}")
    private String serviceList;

    //拦截到后跳转页面
    @Value("${black.url.error:}")
    private String urlError;

    //拦截服务或方法适合API接口
    @Value("${black.service.method:}")
    private String serviceMthod;

    public String getUrlList() {
        return urlList;
    }

    public void setUrlList(String urlList) {
        this.urlList = urlList;
    }

    public String getIpList() {
        return ipList;
    }

    public void setIpList(String ipList) {
        this.ipList = ipList;
    }

    public String getServiceList() {
        return serviceList;
    }

    public void setServiceList(String serviceList) {
        this.serviceList = serviceList;
    }

    public String getUrlError() {
        return urlError==null?"":urlError;
    }

    public void setUrlError(String urlError) {
        this.urlError = urlError;
    }

    public String getServiceMthod() {
        return serviceMthod;
    }

    public void setServiceMthod(String serviceMthod) {
        this.serviceMthod = serviceMthod;
    }
}
