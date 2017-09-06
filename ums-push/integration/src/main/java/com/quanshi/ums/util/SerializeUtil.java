package com.quanshi.ums.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class SerializeUtil implements RedisSerializer<Object>
{
    // public static synchronized byte[] serialize(Object object) {
    // ObjectOutputStream oos = null;
    // ByteArrayOutputStream baos = null;
    // try {
    // // 序列化
    // baos = new ByteArrayOutputStream();
    // oos = new ObjectOutputStream(baos);
    // oos.writeObject(object);
    // byte[] bytes = baos.toByteArray();
    // return bytes;
    // } catch (Exception e) {
    // throw new RuntimeException(e.getMessage(), e);
    // }
    // }

    // public static synchronized Object unserialize(byte[] bytes) {
    // ByteArrayInputStream bais = null;
    // try {
    // // 反序列化
    // bais = new ByteArrayInputStream(bytes);
    // ObjectInputStream ois = new ObjectInputStream(bais);
    // return ois.readObject();
    // } catch (Exception e) {
    // throw new RuntimeException(e.getMessage(), e);
    // }
    // }

    @Override
    public byte[] serialize( Object t ) throws SerializationException
    {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream( baos );
            oos.writeObject( t );
            byte[] bytes = baos.toByteArray();
            return bytes;
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getMessage(), e );
        }
    }

    @Override
    public Object deserialize( byte[] bytes ) throws SerializationException
    {
        ByteArrayInputStream bais = null;
        try
        {
            // 反序列化
            bais = new ByteArrayInputStream( bytes );
            ObjectInputStream ois = new ObjectInputStream( bais );
            return ois.readObject();
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e.getMessage(), e );
        }
    }
}
