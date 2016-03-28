package com.ants.project.core.utils;

import java.io.Serializable;

/**
 * Created by zhituliu on 2016/3/27.
 */
public class UserInfo implements Serializable {

    //用户名
    private String username;
    //用户密码
    private String password;
    //时间戳
    private String timespan;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTimespan() {
        return timespan;
    }

    public void setTimespan(String timespan) {
        this.timespan = timespan;
    }
}
