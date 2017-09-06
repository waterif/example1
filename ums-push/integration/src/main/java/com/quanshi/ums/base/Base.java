/**
 * 
 */
package com.quanshi.ums.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;

/**
 * 封装公共组件的基类
 * 
 * @author yanxiang.huang 2017-06-08 17:06:37
 */
public abstract class Base implements InitializingBean
{
    /** logger */
    protected Logger logger = LoggerFactory.getLogger( this.getClass() );

    @Override
    public void afterPropertiesSet() throws Exception
    {
        // do as you like
    }

    /**
     * 格式化操作
     *
     * @param format
     * @param args
     * @return
     */
    public String format( String format, Object... args )
    {
        return String.format( format, args );
    }

    /**
     * FastJson序列化对象
     *
     * @param obj
     * @return
     */
    public String toJson( Object obj )
    {
        return JSON.toJSONString( obj );
    }

}
