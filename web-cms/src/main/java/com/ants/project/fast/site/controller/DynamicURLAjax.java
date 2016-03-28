package com.ants.project.fast.site.controller;

import net.spy.memcached.tapmessage.ResponseMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-02-18
 */

@RestController
public class DynamicURLAjax {

    @RequestMapping(value = "/{table}/add", method = RequestMethod.GET)
    public ResponseMessage add(){
        return null;
    }
}
