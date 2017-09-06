package com.quanshi.ums.constant;

import java.util.List;

import com.google.common.collect.Lists;

public class Constants
{
    public static final String REQUESTID = "req.requestId";

    /** 蜜蜂产品ID */
    public static final int PRODUCT_BEE = 20;

    public static List<Integer> validProducts = Lists.newArrayList( 9, 82 );

    /**
     * 云问服务:appid
     */
    public static String YUNWEN_SERVICE_APPID = "";

    /**
     * 云问服务:secret
     */
    public static String YUNWEN_SERVICE_SECRET = "";

    /**
     * 云问服务地址:获取token
     */
    public static String YUNWEN_SERVICE_TOKEN_URL = "";

    /**
     * 云问服务地址：数据同步
     */
    public static String YUNWEN_SERVICE_SYNC_DATA_PUSH_URL = "";

    /**
     * 云问服务地址：数据查询
     */
    public static String YUNWEN_SERVICE_SYNC_DATA_LIST_URL = "";

}
