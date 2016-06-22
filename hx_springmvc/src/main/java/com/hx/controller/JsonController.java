package com.hx.controller;

import com.hx.model.bo.ListTestBO;
import com.hx.model.bo.TestBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xing on 2016/6/19.
 */
@Controller
@RequestMapping(path="/json")
public class JsonController {
    @RequestMapping(path ="/test",method = RequestMethod.GET)
    @ResponseBody
    public TestBO getTest(){
        ListTestBO listTestBO=new ListTestBO(1, Arrays.asList("huang","xing"),"huangxing");
        TestBO testBO=new TestBO();
        testBO.setId(1);
        testBO.setDateTime(LocalDateTime.now());
        testBO.setList(Arrays.asList(listTestBO));
        Map<String,ListTestBO> map=new HashMap<>();
        map.put("only one",listTestBO);
        testBO.setMap(map);
        return testBO;

    }
}
