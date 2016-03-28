package com.ants.project.fast.app.v1.service;

import com.ants.project.core.utils.Page;
import com.ants.project.fast.app.bean.Test;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-10
 */

public interface ExampleService {

    //hierbernate 操作
    void hibernate_add();

    void hibernate_delete();

    void hibernate_update();

    Page hibernate_query();


    //mybatis 操作
    void mybatis_add();

    void mybatis_delete();

    void mybatis_update();

    Page mybatis_query();

}

