package com.quanshi.ums.rabbitmq;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * 功能概要：消息产生,提交到队列中去
 * 
 * @author yanxiang.huang 2017-06-12 09:19:56
 */
@Service
public class MessageProducer
{

    private Logger logger = LoggerFactory.getLogger( MessageProducer.class );

    @Resource
    private AmqpTemplate amqpTemplate;

    public void sendMessage( String key, Object message )
    {
        logger.info( "send message. key:{}, message:{}.", key, message );
        amqpTemplate.convertAndSend( key, message );
    }
}