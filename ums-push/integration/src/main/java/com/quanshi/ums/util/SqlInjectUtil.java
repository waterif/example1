package com.quanshi.ums.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlInjectUtil
{

    private static final Logger logger = LoggerFactory.getLogger( SqlInjectUtil.class );

    public static boolean hasSqlInjectStr( String str )
    {

        if ( str == null )
        {
            return false;
        }

        // 提取至配置文件
        String inj_str = "'|<|>|\"|%27|ascii(|mid(|user()";
        String[] inj_stra = inj_str.split( "\\|" );
        for ( int i = 0; i < inj_stra.length; i++ )
        {
            if ( str.indexOf( inj_stra[i] ) >= 0 )
            {
                logger.warn( "Sql Inject String : {}", inj_stra[i] );
                return true;
            }
        }
        return false;
    }
}
