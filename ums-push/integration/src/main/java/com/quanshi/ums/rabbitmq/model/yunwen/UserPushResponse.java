/**
 * 
 */
package com.quanshi.ums.rabbitmq.model.yunwen;

import java.io.Serializable;

/**
 * 
 * @author yanxiang.huang 2017-06-13 09:28:36
 */
public class UserPushResponse implements Serializable
{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** message */
    private String message;

    /** code */
    private int code;

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode( int code )
    {
        this.code = code;
    }

}
