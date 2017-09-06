package com.quanshi.ums.ext;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.quanshi.ums.util.SHAEncrypt;

/**
 * 重写PropertyPlaceholderConfigurer的processProperties方法实现
 * 
 * @author xiaoyu.wang
 *
 */
public class AESPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer
{
    @SuppressWarnings( "unused" )
    private static Logger logger = LoggerFactory.getLogger(AESPropertyPlaceholderConfigurer.class);

    @Override
    protected void processProperties( ConfigurableListableBeanFactory beanFactory, Properties props )
            throws BeansException
    {
        /*String username = props.getProperty( "jdbc.username" );
        String password = props.getProperty( "jdbc.password" );
        if ( password != null )
        {
            // 解密jdbc.password属性值，并重新设置
            try
            {
                props.setProperty( "jdbc.username", SHAEncrypt.AES_Decrypt( username ) );
                props.setProperty( "jdbc.password", SHAEncrypt.AES_Decrypt( password ) );
            }
            catch ( Exception e )
            {
                logger.error( "failed to decrypt username and password." );
            }
        }*/
        super.processProperties( beanFactory, props );

    }

    public static void main( String[] args ) throws Exception
    {
        String quanshi = SHAEncrypt.AES_Encrypt( "root" );
        System.out.println( quanshi );
    }
}
