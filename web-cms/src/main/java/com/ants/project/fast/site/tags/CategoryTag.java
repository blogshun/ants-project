package com.ants.project.fast.site.tags;

import com.alibaba.fastjson.JSON;
import com.ants.project.fast.site.mapper.CategoryMapper;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

/**
 * Created by zhituliu on 2016/3/27.
 */
public class CategoryTag implements TemplateDirectiveModel {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        List<Map> list = categoryMapper.selectCategoryList();
        env.setVariable("list", DEFAULT_WRAPPER.wrap(list));
        System.out.println("##:"+JSON.toJSON(params));
        if (body != null) {
            body.render(env.getOut());
        } else {
            throw new RuntimeException("missing body");
        }
    }
}
