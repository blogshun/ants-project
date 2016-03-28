package com.ants.project.fast.site.mapper;

import com.ants.project.core.annotation.MyBatisMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/22.
 */
@MyBatisMapper
public interface CategoryMapper {

    //查询菜单列表
    List<Map> selectCategoryList();
}
