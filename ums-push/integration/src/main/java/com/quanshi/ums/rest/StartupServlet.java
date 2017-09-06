package com.quanshi.ums.rest;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.quanshi.ums.constant.Constants;
import com.quanshi.ums.service.EnvService;

@Component
public class StartupServlet extends HttpServlet
{

    private static final long serialVersionUID = 8037235342839583597L;

    private static Logger logger = LoggerFactory.getLogger( StartupServlet.class );

    @Autowired
    private EnvService envService;

    @Resource( name = "restTemplate" )
    private RestTemplate restTemplate;

    @Override
    public void init() throws ServletException
    {
        super.init();
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
        
        Constants.YUNWEN_SERVICE_SYNC_DATA_PUSH_URL = envService.loadEnvVarString( "YUNWEN_SERVICE_SYNC_DATA_PUSH_URL", "" );
        logger.info( "YUNWEN_SERVICE_SYNC_DATA_PUSH_URL : {}", Constants.YUNWEN_SERVICE_SYNC_DATA_PUSH_URL );
        
        Constants.YUNWEN_SERVICE_SYNC_DATA_LIST_URL = envService.loadEnvVarString( "YUNWEN_SERVICE_SYNC_DATA_LIST_URL", "" );
        logger.info( "YUNWEN_SERVICE_SYNC_DATA_LIST_URL : {}", Constants.YUNWEN_SERVICE_SYNC_DATA_LIST_URL );
        
        
        logger.info( "------------------------- " );
        logger.info( "Initial Envionment Variables end" );
    }
}
