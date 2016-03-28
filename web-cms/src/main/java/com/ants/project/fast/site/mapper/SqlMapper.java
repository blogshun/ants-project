package com.ants.project.fast.site.mapper;


import com.ants.project.core.annotation.MyBatisMapper;
import java.util.List;
import java.util.Map;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-02-16
 */
@MyBatisMapper
public interface SqlMapper {
    List<Map> getDataList(Map map);
}
