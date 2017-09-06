package com.quanshi.ums.rest;

import java.util.Locale;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanshi.ums.base.ErrorCode;
import com.quanshi.ums.base.ResponseEntity;
import com.quanshi.ums.base.UmsException;
import com.quanshi.ums.constant.Constants;
import com.quanshi.ums.service.TokenService;
import com.quanshi.ums.util.MediaTypes;

/**
 * @author junqing.song
 *
 */
@RequestMapping( value = "/token" )
@Controller
public class TokenController
{

    private static Logger logger = LoggerFactory.getLogger( TokenController.class );

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private Validator validator;
    
    @Autowired
    private TokenService tokenService;

    /**
     * 获取access_token
     * @param appId
     * @param secret
     * @return
     */
    @RequestMapping( value = "/getAccessToken  ", method = RequestMethod.GET, produces = { MediaTypes.JSON_UTF_8 } )
    @ResponseBody
    public ResponseEntity<String> getAccessToken()
    {
        // 接口日志1： request信息
        logger.info( "/token/getAccessToken getAccessToken()");
        try
        {
            String accesToken = tokenService.getAccessToken( Constants.YUNWEN_SERVICE_APPID, Constants.YUNWEN_SERVICE_SECRET );

            // 接口日志2： response信息
            logger.info( "/token/getAccessToken getAccessToken() return : accesToken={}", accesToken );
            return new ResponseEntity<String>( accesToken );
        }
        catch ( UmsException e )
        {
            // 异常日志
            logger.error( e.getMessage(), e );
            return new ResponseEntity<String>( e.getErrorCode(), e.getMessage() );
        }
        catch ( Exception e )
        {
            // 异常日志
            logger.error( e.getMessage(), e );
            return new ResponseEntity<String>( ErrorCode.ERROR_COMMON_FAILURE, messageSource.getMessage(
                    String.valueOf( ErrorCode.ERROR_COMMON_FAILURE ), null, Locale.getDefault() ) );
        }
    }

}
