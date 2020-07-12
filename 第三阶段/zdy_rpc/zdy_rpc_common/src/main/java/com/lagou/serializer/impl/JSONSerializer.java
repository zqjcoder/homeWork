package com.lagou.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.lagou.serializer.Serializer;

import java.io.IOException;

/**
 * json序列化实现
 */
public class JSONSerializer implements Serializer {

    public byte[] serialize(Object object) throws IOException {
        return JSON.toJSONBytes(object);
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException {
        return JSON.parseObject(bytes, clazz);
    }
}
