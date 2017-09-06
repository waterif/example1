package com.quanshi.ums.service.impl;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.quanshi.ums.base.ErrorCode;
import com.quanshi.ums.base.UmsException;
import com.quanshi.ums.constant.Constants;
import com.quanshi.ums.service.CacheService;
import com.quanshi.ums.service.TokenService;
import com.quanshi.ums.util.RestTemplateUtil;

@Service
public class TokenServiceImpl implements TokenService
{
    private static Logger logger = LoggerFactory.getLogger( TokenServiceImpl.class );

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CacheService cacheService;
    
    @Override
    public String getAccessToken( String appId, String secret ) throws UmsException
    {
        String lockName = "token-get-yunwen" + appId + secret;

        String token = "";

        boolean locked = cacheService.lock( lockName, 20, 10L );

        if ( locked )
        {
            try
            {
                token = getAccessTokenFromYunWen( appId, secret );
            }
            finally
            {
                cacheService.releaseLock( lockName );
            }
        }

        return token;
    }

    private String getAccessTokenFromYunWen( String appId, String secret )
    {
        String url = Constants.YUNWEN_SERVICE_TOKEN_URL + "?appId=" + appId + "&secret=" + secret;

        Map<String, Object> result = restTemplateUtil.getRestTemplate().getForObject( url, Map.class );
//        result = restTemplateUtil.getRestTemplate().postForObject( "https://webchat.faqrobot.org/quanshi/syndata/list?siteId=70804&pageNo=1&pageSize=10",null, Map.class );

        logger.info( "getAccessToken result:{}", result );

        Integer status = ( Integer ) result.get( "status" );

        if ( 0 == status )
        {
            return ( String ) result.get( "access_token" );
        }
        else
        {
            throw new UmsException( ErrorCode.ERROR_YUNWEN_TOKEN_GET, messageSource.getMessage(
                    String.valueOf( ErrorCode.ERROR_YUNWEN_TOKEN_GET ), null, Locale.getDefault() ) );
        }
    }
}
