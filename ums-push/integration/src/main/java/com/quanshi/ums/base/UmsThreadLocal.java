package com.quanshi.ums.base;

import java.util.HashMap;
import java.util.Map;

public class UmsThreadLocal
{
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static Map<String, Object> getThreadLocalContext()
    {
        Map<String, Object> context = threadLocal.get();

        if ( context == null )
        {
            context = new HashMap<String, Object>();
            
            threadLocal.set( context );
        }

        return context;
    }
    
    public static void clear()
    {
        threadLocal.remove();
    }
}
