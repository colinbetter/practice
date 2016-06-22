package com.hx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xing on 2016/6/16.
 */
@Controller
@RequestMapping(path="/")
class HelloWorldController {
    @ResponseBody()
    @RequestMapping(path="/helloworld")
    public String sayHello2(){
        return "Hello World2";
    }
    @ResponseBody()
    @RequestMapping(path="/helloworld",method = RequestMethod.GET)
    public String sayHello(){
        return "Hello World";
    }
    @ResponseBody()
    @RequestMapping(path="/helloworld.json",method = RequestMethod.GET)
    public String sayHello3(){
        return "Hello World";
    }
}
