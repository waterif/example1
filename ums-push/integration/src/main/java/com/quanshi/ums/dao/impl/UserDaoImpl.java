package com.quanshi.ums.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.quanshi.ums.dao.AbstractBaseRedisDao;
import com.quanshi.ums.dao.IUserDao;
import com.quanshi.ums.entity.User;

public class UserDaoImpl extends AbstractBaseRedisDao<String, User> implements IUserDao
{

    /**
     * 新增 <br>
     * ------------------------------<br>
     * 
     * @param user
     * @return
     */
    public boolean add( final User user )
    {
        boolean result = redisTemplate.execute( new RedisCallback<Boolean>()
        {
            public Boolean doInRedis( RedisConnection connection ) throws DataAccessException
            {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize( String.valueOf( user.getId() ) );
                byte[] value = serializer.serialize( JSON.toJSONString( user ) );
                return connection.setNX( key, value );
            }
        } );
        return result;
    }

    /**
     * 批量新增 使用pipeline方式 <br>
     * ------------------------------<br>
     *
     * @param list
     * @return
     */
    public boolean add( final List<User> list )
    {
        Assert.notEmpty( list );
        boolean result = redisTemplate.execute( new RedisCallback<Boolean>()
        {
            public Boolean doInRedis( RedisConnection connection ) throws DataAccessException
            {
                RedisSerializer<String> serializer = getRedisSerializer();
                for ( User user : list )
                {
                    byte[] key = serializer.serialize( String.valueOf( user.getId() ) );
                    byte[] value = serializer.serialize( JSON.toJSONString( user ) );
                    connection.setNX( key, value );
                }
                return true;
            }
        }, false, true );
        return result;
    }

    /**
     * 删除 <br>
     * ------------------------------<br>
     * 
     * @param key
     */
    public void delete( String key )
    {
        List<String> list = new ArrayList<String>();
        list.add( key );
        delete( list );
    }

    /**
     * 删除多个 <br>
     * ------------------------------<br>
     * 
     * @param keys
     */
    public void delete( List<String> keys )
    {
        redisTemplate.delete( keys );
    }

    /**
     * 修改 <br>
     * ------------------------------<br>
     * 
     * @param user
     * @return
     */
    public boolean update( final User user )
    {
        String key = String.valueOf( user.getId() );
        if ( get( key ) == null )
        {
            throw new NullPointerException( "数据行不存在, key = " + key );
        }
        boolean result = redisTemplate.execute( new RedisCallback<Boolean>()
        {
            public Boolean doInRedis( RedisConnection connection ) throws DataAccessException
            {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize( String.valueOf( user.getId() ) );
                byte[] value = serializer.serialize( JSON.toJSONString( user ) );
                connection.set( key, value );
                return true;
            }
        } );
        return result;
    }

    /**
     * 通过key获取 <br>
     * ------------------------------<br>
     * 
     * @param keyId
     * @return
     */
    public User get( final String keyId )
    {
        User result = redisTemplate.execute( new RedisCallback<User>()
        {
            public User doInRedis( RedisConnection connection ) throws DataAccessException
            {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize( keyId );
                byte[] value = connection.get( key );
                if ( value == null )
                {
                    return null;
                }
                String valueStr = serializer.deserialize( value );
                return JSON.parseObject( valueStr, User.class );
            }
        } );
        return result;
    }

}
