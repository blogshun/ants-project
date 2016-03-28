package com.ants.project.fast.site.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016-03-20.
 */
@Service
public class SystemConfig {

    @Value("${site.pc.template:}")
    private String pc_template;

    @Value("${site.wap.template:}")
    private String wap_template;

    public String getPCTemplate(){
        return this.pc_template;
    }

    public String getWAPTemplate(){
        return this.wap_template;
    }
}
