package com.ants.project.service.impl;

import com.ants.project.mapper.UserMapper;
import com.ants.project.service.TestService;
import com.ants.project.vo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-04
 */

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserMapper userMapper;

    public List getList(Test test){
        if(test!=null)
        System.out.println("bean ######### ok "+test.getName());
        List list = userMapper.getTagList();
        System.out.println("$$$$$$$$$$$$ "+list.toString());
        return list;
    }

    public List getMap(HashMap map){
        System.out.println("map ######### ok "+map.get("name"));
        List list = userMapper.getTagList();
        System.out.println("$$$$$$$$$$$$ "+list.toString());
        return list;
    }
}
