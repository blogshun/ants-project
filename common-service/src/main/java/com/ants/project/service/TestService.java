package com.ants.project.service;

import com.ants.project.vo.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-02-18
 */

public interface TestService {

    List getList(Test test);

    List getMap(HashMap map);
}
