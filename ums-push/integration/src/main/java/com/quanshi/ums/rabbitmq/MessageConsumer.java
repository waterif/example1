package com.quanshi.ums.rabbitmq;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 消费接收
 * 
 * @author yanxiang.huang 2017-06-12 09:20:34
 */
public class MessageConsumer implements MessageListener
{
    /** logger */
    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    private MessageProcessor processor;

    @Override
    public void onMessage( Message message )
    {
        if ( message == null )
        {
            return;
        }
        try
        {
            processor.process( message );
        }
        catch ( Exception e )
        {
            String err = String.format( "message process error. body:{}.",
                    new String( message.getBody(), Charset.forName( "UTF-8" ) ) );
            logger.error( err, e );
        }
    }

    public void setProcessor( MessageProcessor processor )
    {
        this.processor = processor;
    }

}