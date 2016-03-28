package com.ants.project.site.tags;

import com.alibaba.fastjson.JSON;
import com.ants.project.service.TestService;
import freemarker.core.Environment;
import freemarker.template.*;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-01-18
 */
public class TestTag implements TemplateDirectiveModel {

    @Autowired
    private TestService testService;
//    @Override
//    protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        System.out.println("################# "+ model);
//        System.out.println("################# Start Tags ##"+getUrl()+" - " + getBeanName());
//        super.doRender(model, request, response);
//    }

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        System.out.println("################# Start env ##"+env);
        System.out.println("################# Start params ##" + JSON.toJSON(params)+"- "+(SimpleNumber)params.get("length")+"- "+(SimpleScalar)params.get("text"));
        System.out.println("################# Start loopVars ##"+JSON.toJSON(loopVars));
        System.out.println("################# Start body ##" + body.toString());
        System.out.println("@@@:" + env.getOut().toString());
        List list = testService.getList(null);
        System.out.println("#######:"+list);

        //  System.out.println("################# Start body ##"+body.render(new UpperCaseFilterWriter(env.getOut()));
        // 指令内容体不为空
        if (body != null) {
            // Executes the nested body. Same as <#nested> in FTL, except
            // that we use our own writer instead of the current output writer.
            body.render(env.getOut());
        } else {
            throw new RuntimeException("missing body");
        }
    }


}
