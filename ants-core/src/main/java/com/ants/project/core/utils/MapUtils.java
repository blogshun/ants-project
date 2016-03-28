package com.ants.project.core.utils;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;

/**
 * Map 数据类型转换
 * Created by liushun on 2016/3/19 0019.
 */
public class MapUtils extends HashMap{

    /**
     * 获取字符串数据
     * @param map 数据map
     * @param key 键值
     * @return
     */
    public static String getString(Map map, String key){
        try {
            return String.valueOf(map.get(key));
        }catch (IllegalFormatException e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取整形数据
     * @param map 数据map
     * @param key 键值
     * @return
     */
    public static Integer getInteger(Map map, String key){
        try {
            return Integer.parseInt(map.get(key).toString());
        }catch (IllegalFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}
