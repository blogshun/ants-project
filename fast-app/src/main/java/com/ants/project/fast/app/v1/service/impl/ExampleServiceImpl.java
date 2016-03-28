package com.ants.project.fast.app.v1.service.impl;

import com.alibaba.fastjson.JSON;
import com.ants.project.core.utils.MapUtils;
import com.ants.project.core.utils.Page;
import com.ants.project.fast.app.bean.Test;
import com.ants.project.fast.app.hibernate.dao.BaseDAO;
import com.ants.project.fast.app.mybatis.mapper.UserMapper;
import com.ants.project.fast.app.v1.service.ExampleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-10
 */

@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private BaseDAO baseDAO;

    @Autowired
    private UserMapper userMapper;


    @Override
    public void hibernate_add() {

    }

    @Override
    public void hibernate_delete() {

    }

    @Override
    public void hibernate_update() {

    }

    public Page hibernate_query() {
        return baseDAO.queryByPage("from Example", new Page(1, 1), null);
    }


    @Override
    public void mybatis_add() {

    }

    @Override
    public void mybatis_delete() {

    }

    @Override
    public void mybatis_update() {

    }


    public Page mybatis_query() {
        PageHelper.startPage(2, 2);
        List list = userMapper.getTagList();
        Page page = new Page(list);
        System.out.println("##list "+ JSON.toJSON(page));
        return page;
    }

}
