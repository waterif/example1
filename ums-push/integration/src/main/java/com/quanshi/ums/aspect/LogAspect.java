package com.quanshi.ums.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.quanshi.ums.util.SysLog;

@Aspect
public class LogAspect
{

    /**
     * 在执行方法前后，增加日志
     * 
     * @param jp 切点
     * @return
     * @throws java.lang.Throwable
     */
    @SuppressWarnings( "unused" )
    @Around( "execution(* com.quanshi.ums.*.*Controller.*(..))" )
    public Object logAround( ProceedingJoinPoint jp ) throws java.lang.Throwable
    {
        String targetName = jp.getSignature().toShortString();
        // SysLog.info( "starting to execute [" + targetName + "]");
        if ( SysLog.isDebugEnabled() )
        {
            Object[] args = jp.getArgs();
            StringBuilder sb = new StringBuilder();
            sb.append( "Parameters: " );
            if ( args != null )
            {
                for ( Object obj : args )
                {
                    if ( obj != null )
                    {
                        sb.append( obj + "(" + obj.getClass().getSimpleName() + ")" );
                    }
                }
                SysLog.debug( sb.toString() );
            }
        }
        Long start = System.currentTimeMillis();
        Object rvt = jp.proceed();
        Long runingtime = System.currentTimeMillis() - start;

        SysLog.debug( "running time: " + runingtime + "ms" );
        SysLog.debug( "return: " + rvt );
        // SysLog.info("finished to execute [" + targetName + "]");
        return rvt;
    }
}
