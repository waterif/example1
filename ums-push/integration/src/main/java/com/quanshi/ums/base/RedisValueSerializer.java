package com.quanshi.ums.base;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class RedisValueSerializer implements RedisSerializer<Object>
{
    
    @Override
    public byte[] serialize( Object source ) throws SerializationException
    {
        if ( source == null )
        {
            return null;
        }
        // 打印类型以供反序列化
        return JSON.toJSONBytes( source, SerializerFeature.WriteClassName );

    }

    @Override
    public Object deserialize( byte[] source ) throws SerializationException
    {
        if ( source == null || source.length == 0 )
        {
            return null;
        }

        return JSON.parse( source );

    }

}
