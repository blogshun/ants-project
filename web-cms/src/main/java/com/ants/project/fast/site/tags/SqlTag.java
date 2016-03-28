package com.ants.project.fast.site.tags;

import com.alibaba.fastjson.JSON;
import com.ants.project.fast.site.mapper.SqlMapper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-01-18
 */
public class SqlTag implements TemplateDirectiveModel {

    private final Logger logger = LoggerFactory.getLogger("SqlTag");

    @Autowired
    private SqlMapper sqlMapper;


    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        System.out.println("################# Start env ##"+env);
        try {
            String check = RandTagProperty(params);
            logger.info("## "+JSON.toJSON(params) );
           // logger.info("## "+env.getOut());
           // if(!"".equals(check))
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("################# Start params ##" + JSON.toJSON(params)+"- "+params.get("condition")+"- "+params.get("pagesize"));
       // System.out.println("################# Start loopVars ##"+JSON.toJSON(loopVars));

        List<Map> list = sqlMapper.getDataList(linkMapSql(params));
        env.setVariable("list", DEFAULT_WRAPPER.wrap(list));
        // 指令内容体不为空
        if (body != null) {
            // Executes the nested body. Same as <#nested> in FTL, except
            // that we use our own writer instead of the current output writer.
//            Writer out = env.getOut();
//            out.write(env.getTemplate().toString());
            body.render(env.getOut().append("<div style='height:50px;width:100px;border:1px solid red'>sss</div>"));
        } else {
            throw new RuntimeException("missing body");
        }
    }

    public String RandTagProperty(Map map){
        if(map.get("data")==null||"".equals(map.get("data")))
            return "data 数据对象属性不能为空";
        else if(map.get("table")==null||"".equals(map.get("table")))
            return "table 表名称属性不能为空";
        else if("".equals(map.get("fields")))
            return "fields 字段属性要么省略要么填写";
        else if("".equals(map.get("condition")))
            return "condition 条件属性要么省略要么填写";
        else if(map.get("debug")!=null||Integer.getInteger(map.get("debug").toString())!=0||Integer.getInteger(map.get("debug").toString())!=1)
            return "debug 调试属性要么省略是0或1";
        else
            return "";
    }
    /**
     * 拼接成sql语句
     * @param params
     * @return
     */
    public Map linkMapSql(Map params){
        Map map = new HashMap();
        String[] tables = String.valueOf(params.get("tables")).split(",");
        Object fields = params.get("fields");
        Object conditions = params.get("conditions");
        map.put("fields", fields==null||fields.equals("")?"*":fields);
        //for(String table:tables)
        map.put("tables", params.get("tables"));
        if(conditions!=null&&!conditions.equals("")) {
            String condition = String.valueOf(conditions).split("#")[0];
            map.put("condition",condition.replaceAll("&", " and ").replaceAll("\\|", "or"));
            String postfix =String.valueOf(conditions).split("#")[1];
            map.put("postfix",postfix.replaceAll("&", " ").replaceAll("order=", "order by ").replaceAll("limit=", " limit "));
        }
        return map;
    }

    public static void main(String[] agrs) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        Map map = new HashMap();
    map.put("data", "list");
    map.put("table", "destoon_member m,destoon_company c");
    map.put("fields", "m.userid=c.userid&m.username='destoon'");
    System.out.println(JSON.toJSON(map));
    System.out.println(String.valueOf(null));
}
}
