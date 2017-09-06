package com.quanshi.ums.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanshi.ums.base.ResponseEntity;
import com.quanshi.ums.constant.Constants;
import com.quanshi.ums.service.EnvService;
import com.quanshi.ums.util.MediaTypes;

/**
 * 缓存资源类 1、用户缓存 com:quanshi:ums:profilelist: 2、用户标签缓存
 * com:quanshi:ums:usertagcache: 3、用户第三方绑定缓存
 * com:quanshi:ums:userthirdpartycache:
 */
@RequestMapping( value = "/env" )
@Controller
public class EnvController
{

    private static Logger logger = LoggerFactory.getLogger( EnvController.class );

    @Autowired
    private EnvService envService;

    /**
     * 清理缓存
     * 
     * @param ids id列表
     * @param type 清理类型
     */
    @RequestMapping( value = "/reload", method = RequestMethod.POST, consumes = MediaTypes.JSON, produces = {
            MediaTypes.JSON_UTF_8, MediaTypes.APPLICATION_XML_UTF_8 } )
    @ResponseBody
    public ResponseEntity<String> reload()
    {
        logger.info( "reload env setting" );

        logger.info( "Initial Envionment Variables..." );
        logger.info( "------------------------- " );
        // 初始化系统配置

        // 加载云问配置
        Constants.YUNWEN_SERVICE_APPID = envService.loadEnvVarString( "YUNWEN_SERVICE_APPID", "" );
        logger.info( "YUNWEN_SERVICE_APPID : {}", Constants.YUNWEN_SERVICE_APPID );
        
        Constants.YUNWEN_SERVICE_SECRET = envService.loadEnvVarString( "YUNWEN_SERVICE_SECRET", "" );
        logger.info( "YUNWEN_SERVICE_SECRET : {}", Constants.YUNWEN_SERVICE_SECRET );
        
        Constants.YUNWEN_SERVICE_TOKEN_URL = envService.loadEnvVarString( "YUNWEN_SERVICE_TOKEN_URL", "" );
        logger.info( "YUNWEN_SERVICE_TOKEN_URL : {}", Constants.YUNWEN_SERVICE_TOKEN_URL );

        Constants.YUNWEN_SERVICE_SYNC_DATA_PUSH_URL = envService.loadEnvVarString( "YUNWEN_SERVICE_SYNC_DATA_PUSH_URL",
                "" );
        logger.info( "YUNWEN_SERVICE_SYNC_DATA_PUSH_URL : {}", Constants.YUNWEN_SERVICE_SYNC_DATA_PUSH_URL );

        Constants.YUNWEN_SERVICE_SYNC_DATA_LIST_URL = envService.loadEnvVarString( "YUNWEN_SERVICE_SYNC_DATA_LIST_URL",
                "" );
        logger.info( "YUNWEN_SERVICE_SYNC_DATA_LIST_URL : {}", Constants.YUNWEN_SERVICE_SYNC_DATA_LIST_URL );

        logger.info( "------------------------- " );
        logger.info( "Initial Envionment Variables end" );
        
        return new ResponseEntity<String>( "" );
    }

}
