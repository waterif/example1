package com.quanshi.ums.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;

import com.quanshi.ums.dao.AbstractBaseRedisDao;
import com.quanshi.ums.service.CacheService;

@Service
public class CacheServiceImpl extends AbstractBaseRedisDao<String, String> implements CacheService
{

    private static Logger logger = LoggerFactory.getLogger( CacheServiceImpl.class );

    /*
     * (non-Javadoc)
     * 
     * @see com.quanshi.ums.service.RedisCacheManger#remove(byte[])
     */
    @Override
    public void remove( final byte[] keybytes )
    {
        redisTemplate.execute( new RedisCallback<Object>()
        {
            @Override
            public Object doInRedis( RedisConnection connection ) throws DataAccessException
            {
                connection.del( keybytes );
                return null;
            }
        } );
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.quanshi.ums.service.RedisCacheManger#lock(java.lang.String,
     * java.lang.Integer, java.lang.Long)
     */
    @Override
    public boolean lock( String name, Integer timeout, final Long expire )
    {
        if ( name == null || name.equals( "" ) )
            return false;
        Long time = System.currentTimeMillis();
        // 设置默认值
        timeout = timeout == null ? 0 : timeout;
        Long timeoutAt = time + timeout * 1000;
        final String lockKey = "Lock:{" + name + "}";
        while ( true )
        {
            Long lockResult = acquireLock( lockKey, expire );
            // 锁请求成功
            if ( lockResult.compareTo( 1L ) == 0 )
            {
                logger.info( "acquireLock success, lockName : {}", lockKey );
                return true;
            }

            try
            {
                time = System.currentTimeMillis();
                if ( time > timeoutAt )
                {
                    return false;
                }
                // 休眠200毫秒
                Thread.sleep( 200 );
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 请求锁
     * 
     * @param lockName
     * @param expire
     * @return
     */
    private Long acquireLock( final String lockName, final long expire )
    {
        return redisTemplate.execute( new RedisCallback<Long>()
        {
            @Override
            public Long doInRedis( RedisConnection connection ) throws DataAccessException
            {
                byte[] lockBytes = redisTemplate.getStringSerializer().serialize( lockName );
                boolean locked = connection.setNX( lockBytes, lockBytes );
                connection.expire( lockBytes, expire );
                if ( locked )
                {
                    return 1L;
                }
                return 0L;
            }
        } );
    }

    @Override
    public void releaseLock( String lockName )
    {
        final String lockKey = "Lock:{" + lockName + "}";
        byte[] lockBytes = redisTemplate.getStringSerializer().serialize( lockKey );
        remove( lockBytes );
    }

}
