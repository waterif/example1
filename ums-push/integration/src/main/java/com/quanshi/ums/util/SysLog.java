package com.quanshi.ums.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysLog
{

    private static Logger logger = LoggerFactory.getLogger( SysLog.class );

    // private static Log logger = LogFactory.getLog(SysLog.class);
    public static boolean isDebugEnabled()
    {
        return logger.isDebugEnabled();
    }

    public static boolean isInfoEnabled()
    {
        return logger.isInfoEnabled();
    }

    public static boolean isTraceEnabled()
    {
        return logger.isTraceEnabled();
    }

    /**
     * @param logMessage
     * @param e
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-1
     */
    public static void debug( String logMessage, Throwable e )
    {
        logger.debug( logMessage, e );
    }

    /**
     * @param logMessage
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-2
     */
    public static void debug( String logMessage )
    {
        logger.debug( logMessage );
    }

    /**
     * @param className
     * @param logMessage
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-3
     */
    public static void debug( String className, String logMessage )
    {
        debug( "*** " + className + " " + logMessage + " ***" );
    }

    /**
     * @param className
     * @param logMessage
     * @param e
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-4
     */
    public static void debug( String className, String logMessage, Throwable e )
    {
        debug( "*** " + className + " " + logMessage + " ***", e );
    }

    /**
     * @param logMessage
     * @param e
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-5
     */
    public static void info( String logMessage, Throwable e )
    {
        logger.info( logMessage, e );
    }

    /**
     * @param logMessage
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-6
     */
    public static void info( String logMessage )
    {
        logger.info( logMessage );
    }

    /**
     * @param className
     * @param logMessage
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-7
     */
    public static void info( String className, String logMessage )
    {
        info( "*** " + className + " " + logMessage + " ***" );
    }

    /**
     * @param className
     * @param logMessage
     * @param e
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-8
     */
    public static void info( String className, String logMessage, Throwable e )
    {
        info( "*** " + className + " " + logMessage + " ***", e );
    }

    /**
     * @param logMessage
     * @param e
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-9
     */
    public static void warn( String logMessage, Throwable e )
    {
        logger.warn( logMessage, e );
    }

    /**
     * @param logMessage
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-10
     */
    public static void warn( String logMessage )
    {
        logger.warn( logMessage );
    }

    /**
     * @param className
     * @param logMessage
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-11
     */
    public static void warn( String className, String logMessage )
    {
        warn( "*** " + className + " " + logMessage + " ***" );
    }

    /**
     * @param className
     * @param logMessage
     * @param e
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-12
     */
    public static void warn( String className, String logMessage, Throwable e )
    {
        warn( "*** " + className + " " + logMessage + " ***", e );
    }

    /**
     * @param logMessage
     * @param e
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-13
     */
    public static void error( String logMessage, Throwable e )
    {
        logger.error( logMessage, e );
    }

    /**
     * @param logMessage
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-14
     */
    public static void error( String logMessage )
    {
        logger.error( logMessage );
    }

    /**
     * @param className
     * @param logMessage
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-15
     */
    public static void error( String className, String logMessage )
    {
        error( "*** " + className + " " + logMessage + " ***" );
    }

    /**
     * @param className
     * @param logMessage
     * @param e
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-16
     */
    public static void error( String className, String logMessage, Throwable e )
    {
        error( "*** " + className + " " + logMessage + " ***", e );
    }

    /**
     * @param className
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-21
     */
    public static void startLog( String className )
    {
        logger.info( "*** " + className + " Start ***" );
    }

    /**
     * @param className
     * @req ROADSHOW-SRS-recordlog-1
     * @des ROADSHOW-HLD-recordlog-22
     */
    public static void endLog( String className )
    {
        logger.info( "*** " + className + " End ***" );
    }

}
