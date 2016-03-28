package com.ants.project.mapper;


import com.ants.project.core.annotation.MyBatisMapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-02-16
 */
@MyBatisMapper
public interface UserMapper {
    List<HashMap> getTagList();
}
