package com.ants.project.core.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhituliu on 2016/3/27.
 */
public class TokenUtils {

    /**
     * 账号密码生成token信息
     * @param username
     * @param password
     * @return
     */
    public static String getToken(String username, String password){
        try {
            return StringEncryptUtils.encrypt(username+" "+StringEncryptUtils.MD5(password)+" "+new Date().getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取token信息
     * @param token
     * @return
     */
    public static Map getUserInfo(String token){
        Map map = new HashMap();
        try {
            String userinfo = StringEncryptUtils.decrypt(token);
            map.put("username", userinfo.split(" ")[0]);
            map.put("password", userinfo.split(" ")[1]);
            map.put("timespan", userinfo.split(" ")[2]);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
