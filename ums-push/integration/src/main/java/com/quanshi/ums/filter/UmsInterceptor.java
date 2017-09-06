package com.quanshi.ums.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UmsInterceptor extends HandlerInterceptorAdapter
{
    /** logger */
    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
            throws Exception
    {
        String uri = request.getRequestURI();
        String clientIP = getIpAddr( request );
        logger.debug( "start uri:" + uri + " clientIP:" + clientIP + " now:" + new Date() );
        return super.preHandle( request, response, handler );
    }

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView ) throws Exception
    {
        if ( modelAndView != null )
        {
            String ctx = request.getContextPath();
            modelAndView.addObject( "ctx", ctx );
        }
        super.postHandle( request, response, handler, modelAndView );
    }

    @Override
    public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex ) throws Exception
    {
        try
        {
            String uri = request.getRequestURI();
            String clientIP = getIpAddr( request );
            logger.debug( "end！ uri:" + uri + " clientIP:" + clientIP + " now:" + new Date() );
        }
        catch ( Exception e )
        {
            logger.error( "afterCompletion", e );
        }
        super.afterCompletion( request, response, handler, ex );
    }

    /*public String getIpAddr( HttpServletRequest request )
    {
        String ip = request.getHeader( "x-forwarded-for" );
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getHeader( "Proxy-Client-IP" );
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getHeader( "WL-Proxy-Client-IP" );
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }*/
    
    public static String getIpAddr( HttpServletRequest request )
    {
        String ip = request.getHeader( "X-Forwarded-For" );
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getHeader( "Proxy-Client-IP" );
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getHeader( "WL-Proxy-Client-IP" );
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getHeader( "HTTP_CLIENT_IP" );
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getHeader( "HTTP_X_FORWARDED_FOR" );
        }
        if ( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase( ip ) )
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
