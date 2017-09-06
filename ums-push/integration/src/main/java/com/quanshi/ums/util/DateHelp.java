/**
 * 
 */
package com.quanshi.ums.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * @author yanxiang.huang 2017-06-16 14:37:47
 */
public abstract class DateHelp
{

    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将日期字符串转为日期对象<br>
     * format: yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static Date parse( String date )
    {
        if ( StringUtils.isNotBlank( date ) )
        {
            try
            {
                return DateUtils.parseDate( date, DEFAULT_FORMAT );
            }
            catch ( ParseException e )
            {
                // do nothing
            }
        }
        return null;
    }
}
