/**
 * 
 */
package com.quanshi.ums.rabbitmq;

/**
 * 消息类型枚举
 * 
 * @author yanxiang.huang 2017-06-12 14:20:05
 */
public enum MessageEnum
{
    /** 更新用户信息 */
    USER_UPDATE_SELECTIVE( "ums.user.update.selective", 103 ),
    /** 新增用户产品 */
    USER_PRODUCT_CREATE( "ums.user.product.create", 200 ),
    /** 删除用户产品 */
    USER_PRODUCT_DELETE( "ums.user.product.delete", 202 ),
    /** 更新用户产品 */
    USER_PRODUCT_UPDATE_SELECTIVE( "ums.user.product.update.selective", 203 );

    private String key;

    private int code;

    private MessageEnum( String key, int code )
    {
        this.key = key;
        this.code = code;
    }

    /**
     * 通过key确认消息类型
     *
     * @param key
     * @return
     */
    public static MessageEnum getByKey( String key )
    {
        if ( key != null )
        {
            for ( MessageEnum msg : MessageEnum.values() )
            {
                if ( msg.getKey().equals( key ) )
                {
                    return msg;
                }
            }
        }
        return null;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey( String key )
    {
        this.key = key;
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
