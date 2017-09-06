package com.quanshi.ums.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class ServletUtil
{

    private static Logger logger = LoggerFactory.getLogger( ServletUtil.class );

    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    public static final String TYPE_MULTIPART_FORM_DATA = "multipart/form-data";

    public static final String HEADER_AUTHORIZATION = "Authorization";

    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";

    /**
     * Returns <code>true</code> if client supports gzip encoding.
     */
    public boolean isGzipSupported( HttpServletRequest request )
    {
        String browserEncodings = request.getHeader( HEADER_ACCEPT_ENCODING );
        return (browserEncodings != null) && (browserEncodings.contains( "gzip" ));
    }

    public void writeResponse( HttpServletRequest request, HttpServletResponse response, Object resultList )
    {
        try
        {
            response.setHeader( "Content-Type", "application/json;charset=UTF-8" );
            String json = JSON.toJSONString( resultList );
            byte[] result = json.getBytes( "UTF-8" );
            String ae = request.getHeader( "Accept-Encoding" );
            if ( ae != null && ae.indexOf( "gzip" ) != -1 )
            {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                GZIPOutputStream gout = new GZIPOutputStream( out );
                gout.write( json.getBytes( "UTF-8" ) );
                gout.close();
                result = out.toByteArray();

                response.setHeader( "Content-Encoding", "gzip" );
                response.setHeader( "Content-Length", result.length + "" );
            }
            response.getOutputStream().write( result );
        }
        catch ( UnsupportedEncodingException e )
        {
            logger.error( e.getMessage(), e );
        }
        catch ( IOException e )
        {
            logger.error( e.getMessage(), e );
        }
    }
}
