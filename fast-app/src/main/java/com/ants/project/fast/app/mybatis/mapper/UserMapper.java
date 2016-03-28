package com.ants.project.fast.app.mybatis.mapper;


import com.ants.project.core.annotation.MyBatisMapper;
import com.ants.project.fast.app.mybatis.dao.BaseMapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-02-16
 */
@MyBatisMapper
public interface UserMapper extends BaseMapper{
    List<HashMap> getTagList();
}
