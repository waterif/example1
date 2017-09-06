package com.quanshi.ums.rest;

import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.quanshi.ums.base.BeanValidators;
import com.quanshi.ums.base.ErrorCode;
import com.quanshi.ums.base.ResponseEntity;
import com.quanshi.ums.base.UmsException;
import com.quanshi.ums.entity.User;
import com.quanshi.ums.rabbitmq.MessageProducer;
import com.quanshi.ums.service.UserService;

/**
 * @author junqing.song
 *
 */
@Controller
@RequestMapping( value = "/test" )
public class TestController
{

    private static Logger logger = LoggerFactory.getLogger( TestController.class );

    @Resource
    private MessageSource messageSource;

    @Resource
    private Validator validator;

    @Resource
    private MessageProducer producer;
    
    @Resource
    private UserService userService;
    
    /**
     * log
     * 
     * @param name 名称
     * @return 返回值
     */
    @RequestMapping("/log")
    @ResponseBody
    public ResponseEntity<String> say( @RequestParam( "name" ) String name )
    {
        // 接口日志1： request信息
        logger.info( "/test/log say name:{}.", name );
        try
        {
            producer.sendMessage( "msg", name );
            // 运行日志
            logger.debug( "test debug" );
            logger.info( "test info" );

            // 异常日志
            logger.error( "test error:{}", messageSource.getMessage( String.valueOf( ErrorCode.ERROR_COMMON_FAILURE ),
                    null, Locale.getDefault() ) );

            BeanValidators.validateWithException( validator, name );

            // 接口日志2： response信息
            logger.info( "/test/hello say return : {}", name );
            return new ResponseEntity<String>( name );
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
            return new ResponseEntity<String>( ErrorCode.ERROR_COMMON_FAILURE, messageSource
                    .getMessage( String.valueOf( ErrorCode.ERROR_COMMON_FAILURE ), null, Locale.getDefault() ) );
        }
    }

    @RequestMapping("/mq/{type}")
    @ResponseBody
    public ResponseEntity<String> mq(@PathVariable int type, @RequestBody String msg)
    {
        if (type == 1) {
            producer.sendMessage( "ums.user.update.selective", msg );
        } else if (type == 2) {
            producer.sendMessage( "ums.user.product.create", msg );
        } else if (type == 3) {
            producer.sendMessage( "ums.user.product.delete", msg );
        } else if (type == 4) {
            producer.sendMessage( "ums.user.product.update.selective", msg );
        }
        return new ResponseEntity<String>( "true" );
    }
    
    @RequestMapping( "/page")
    @ResponseBody
    public ResponseEntity<PageInfo<User>> mq()
    {
        return new ResponseEntity<PageInfo<User>>( userService.getUserPage( 1, 5 ) );
    }
    
    

}
