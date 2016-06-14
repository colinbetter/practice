package com.rabbit.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by xing on 2016/6/9.
 */
public class ReplyConsumer {
    public ReplyProvider.HuangXing handleMessage(Object message){

        System.out.println(message);
        System.out.println(message.getClass());
        return JSON.parseObject(((JSONObject)message).toString(), ReplyProvider.HuangXing.class);
    }
}
