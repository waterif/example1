/**
 * 
 */
package com.quanshi.ums.rabbitmq;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.quanshi.ums.rabbitmq.handler.UserHandler;
import com.quanshi.ums.rabbitmq.handler.UserProductHandler;
//import com.quanshi.ums.rabbitmq.model.NotifyMessage;
import com.quanshi.ums.rabbitmq.model.ums.UserProductCreateMsg;
import com.quanshi.ums.rabbitmq.model.ums.UserProductDeleteMsg;
import com.quanshi.ums.rabbitmq.model.ums.UserProductUpdateSelectiveMsg;
import com.quanshi.ums.rabbitmq.model.ums.UserUpdateSelectiveMsg;

/**
 * 消息处理
 * 
 * @author yanxiang.huang 2017-06-12 13:41:44
 */
@Service
public class MessageProcessor
{

    private Logger logger = LoggerFactory.getLogger( MessageProcessor.class );

    /** userHandler */
    @Resource
    private UserHandler userHandler;

    /** userProductHandler */
    @Resource
    private UserProductHandler userProductHandler;

    /**
     * 消息处理
     *
     * @param msg
     */
    public void process( Message msg )
    {
        MessageProperties props = msg.getMessageProperties();
        byte[] body = msg.getBody();
        String content = new String( body, Charset.forName( "UTF-8" ) );
        logger.info( "receive msg:{}.", content );
        String routingKey = props.getReceivedRoutingKey();
        if (routingKey.startsWith( "event." )) {
            routingKey = StringUtils.substringAfter( routingKey, "event." );
        }
        MessageEnum msgEnum = MessageEnum.getByKey( routingKey );
        if ( msgEnum == null )
        {
            logger.warn( "msg don't process. routingKey:{}.", routingKey );
            return;
        }
//        NotifyMessage message = JSON.parseObject( content, NotifyMessage.class );
//        content = message.getEventData();
//        switch ( msgEnum )
//        {
//        case USER_UPDATE_SELECTIVE:
//            UserUpdateSelectiveMsg userUpdateSelective = JSON.parseObject( content, UserUpdateSelectiveMsg.class );
//            userHandler.updateSelective( userUpdateSelective );
//            break;
//        case USER_PRODUCT_CREATE:
//            UserProductCreateMsg userProductCreate = JSON.parseObject( content, UserProductCreateMsg.class );
//            userProductHandler.create( userProductCreate );
//            break;
//        case USER_PRODUCT_DELETE:
//            UserProductDeleteMsg userProductDelete = JSON.parseObject( content, UserProductDeleteMsg.class );
//            userProductHandler.delete( userProductDelete );
//            break;
//        case USER_PRODUCT_UPDATE_SELECTIVE:
//            UserProductUpdateSelectiveMsg userProductUpdateSelective = JSON.parseObject( content,
//                    UserProductUpdateSelectiveMsg.class );
//            userProductHandler.updateSelective( userProductUpdateSelective );
//            break;
//        default:
//            logger.info( "unresolved msg. msgEnum:{}.", msgEnum );
//            break;
//        }

    }
}
