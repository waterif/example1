package com.quanshi.ums.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.quanshi.ums.base.RequestHandler;
import com.quanshi.ums.base.UmsThreadLocal;
import com.quanshi.ums.constant.Constants;

public class UmsLogFilter implements Filter
{

    /** logger */
    private Logger logger = LoggerFactory.getLogger( this.getClass() );
    
    public void init( FilterConfig fConfig ) throws ServletException
    {
    }

    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
            throws IOException, ServletException
    {
        MDC.put( Constants.REQUESTID, RequestHandler.getRequestId() );
        try
        {
            logger.debug( "umsfilter dofilter." );
            chain.doFilter( request, response );
        }
        finally
        {
            MDC.remove( Constants.REQUESTID );
            UmsThreadLocal.clear();
        }
    }

    public void destroy()
    {
    }
}
