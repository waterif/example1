package com.quanshi.ums.base;


public class ErrorCode
{
    /**
     * 成功
     */
    public static final int SUCCESS = 0;

    /**
     * 未知错误
     */
    public static final int ERROR_UNKNOWN = -1;

    /**
     * 通用错误
     */
    public static final int ERROR_COMMON_FAILURE = 1;

    /**
     * 鉴权错误
     */
    public static final int ERROR_AUTH_SUCCESS = 10000;
    
    
    /**
     * 获取云问token失败
     */
    public static final int ERROR_YUNWEN_TOKEN_GET = 20001;

}
