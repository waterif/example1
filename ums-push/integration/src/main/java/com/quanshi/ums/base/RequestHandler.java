package com.quanshi.ums.base;

import java.util.Map;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.quanshi.ums.constant.Constants;

public class RequestHandler
{
    public static String getRequestId()
    {
        Map<String, Object> context = UmsThreadLocal.getThreadLocalContext();

        String requestId = null;

        if ( context.containsKey( Constants.REQUESTID ) )
        {
            requestId = String.valueOf( context.get( Constants.REQUESTID ) );
        }

        if ( StringUtils.isEmpty( requestId ) )
        {

            requestId = UUID.randomUUID().toString();
            context.put( Constants.REQUESTID, requestId );
        }

        return requestId;

    }
}
