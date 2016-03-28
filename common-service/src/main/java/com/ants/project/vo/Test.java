package com.ants.project.vo;

import javax.validation.constraints.NotNull;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-04
 */
public class Test {

    @NotNull
    private String name;

    @NotNull
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
