package com.quanshi.ums.service;


public interface CacheService
{

    public final static String PROFILE = "com:quanshi:ums:profile";

    public static final String USER_ORG = "user_org";

    public static final String USER_MAIL = "user_mail";

    public static final String USER_MOBILE = "user_mobile";

    public static final String USER_THIRDPARTY = "user_thirdparty";

    public static final String USER_PHONE = "user_phone";

    public static final String USER_LAST_LOGIN_TIME = "user_last_login_time";

    public static final String USER_ATTRIBUTES = "user_attributes";

    public static final String UserTagCachePrex = "com:quanshi:ums:usertagcache:";

    public static final String SITE_CACHE = "com:quanshi:ums:sitecache:";

    public static final String SITE_TAG_CACHE = "com:quanshi:ums:sitetagcache:";


    void releaseLock( String lockName );

    boolean lock( String name, Integer timeout, Long expire );

    void remove( byte[] keybytes );

}
