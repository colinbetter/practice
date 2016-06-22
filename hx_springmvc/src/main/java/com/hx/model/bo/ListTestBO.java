package com.hx.model.bo;

import java.util.List;

/**
 * Created by xing on 2016/6/19.
 */
public class ListTestBO {
    private String name;
    private int age;
    private List<String> strList;

    public ListTestBO() {
    }

    public ListTestBO(int age, List<String> strList, String name) {
        this.age = age;
        this.strList = strList;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }
}
