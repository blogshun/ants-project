package com.ants.project.core.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.JSON;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * 利用阿里连接池+DBUtils操作数据库
 * Created by liushun on 2016/3/10.
 */
public class MyDBUtils extends QueryRunner {

    private static final Logger logger  =  LoggerFactory.getLogger(MyDBUtils.class);

    private static DruidDataSource dataSource = null;

    static {
        Properties properties = new Properties();
        InputStream is = MyDBUtils.class.getResourceAsStream("/dbconfig.properties");
        try {
            properties.load(is);
            try {
                dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
                logger.info("connecting to the DruidDataSource is success!");
            } catch (Exception e) {
                logger.error("connecting to the DruidDataSource is error " + e.getMessage());
            }
        } catch (IOException e) {
            logger.error("dbconfig.properties is no found !"+ e.getMessage());
        }

    }

    public MyDBUtils() {
        super(dataSource);
    }

}
