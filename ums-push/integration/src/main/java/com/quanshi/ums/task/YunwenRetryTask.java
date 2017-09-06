/**
 * 
 */
package com.quanshi.ums.task;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import com.quanshi.ums.base.Base;
import com.quanshi.ums.service.PushLogService;
import com.quanshi.ums.util.DateHelp;

/**
 * 
 * @author yanxiang.huang 2017-06-16 16:34:00
 */
@Component
public class YunwenRetryTask extends Base
{

    @Resource
    private PushLogService pushLogService;

    public synchronized void run()
    {
        logger.info( "push log retry interval. time:{}.",
                DateFormatUtils.format( new Date(), DateHelp.DEFAULT_FORMAT ) );
        try
        {
            pushLogService.retryInterval();
        }
        catch ( Exception e )
        {
            logger.error( "push log retry interval error.", e );
        }
    }
}
