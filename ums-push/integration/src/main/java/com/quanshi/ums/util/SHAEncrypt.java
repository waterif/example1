/**  
 * @Description: TODO
 * @Title: SHAEncrypt.java
 * @Package : com.gnet.tang.usermanager.util
 * @author : haijun.jiang 
 * @date : 2012-12-28 上午10:07:11
 * @version : V1.4
 */
package com.quanshi.ums.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quanshi.ums.base.UmsException;

/**
 * 
 * @ClassName SHAEncrypt
 * @Package com.gnet.tang.usermanager.util
 * @author haijun.jiang
 * @since JDK1.6.0_01
 * @date 2012-12-28 上午10:07:11
 * @version V1.4
 * 
 */
public class SHAEncrypt
{

    private static Logger logger = LoggerFactory.getLogger( SHAEncrypt.class );

    static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /*
     * 此处使用AES-128-ECB加密模式，key需要为16位。
     */
    private static final String AES_Key = "xihmP8EWXIF8GBlg";

    private static String encryptPwd( String password )
    {
        MessageDigest ssha = null;
        try
        {
            ssha = MessageDigest.getInstance( "SHA" );
            ssha.update( password.getBytes() );
        }
        catch ( NoSuchAlgorithmException e )
        {
            SysLog.error( "None of the encryption algorithm(没有该加密算法)" );
            return null;
        }
        return ssha.digest().toString();
    }

    private static String encryptPwd( String password, String algorithm ) throws Exception
    {
        try
        {
            MessageDigest ssha = MessageDigest.getInstance( algorithm );
            ssha.update( password.getBytes() );
            byte[] bytes = ssha.digest();
            int j = bytes.length;
            char str[] = new char[j * 2];
            int k = 0;
            for ( int i = 0; i < j; i++ )
            {
                byte byte0 = bytes[i];
                str[k++] = hexDigits[(byte0 >>> 4) & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String( str );
        }
        catch ( NoSuchAlgorithmException e )
        {
            SysLog.error( "None of the encryption algorithm(没有该加密算法)" );
            e.printStackTrace();
            throw new Exception( "encryptPwd failed" );
        }
    }

    /**
     * MD5二次加密
     * 
     * @date 2013-1-4 下午03:32:54
     * @param password
     * @return
     * @throws Exception
     * @return String
     */
    public static String MD5SecondaryEncrypt( String password ) throws Exception
    {
        return encryptPwd( encryptPwd( password, "MD5" ), "MD5" );
    }

    /**
     * 根据密码明文和密码类型获取密码
     * 
     * @date 2013-1-4 下午03:12:10
     * @param passType 0=明文， 1=MD5， 2=crypt， 3=ssha ，4=MD5*3，5=LDAP， 6=external
     *            WEB
     * @return
     * @return String
     * @throws Exception 加密失败是否需要抛出异常
     */
    public static String encryptPwd( String password, int passType )
    {
        String encryptPwd = null;
        try
        {
            switch ( passType )
            {
            case PassType.plaintext:
                encryptPwd = password;
                break;
            case PassType.md5:
                encryptPwd = encryptPwd( password, "MD5" );
                break;
            case PassType.crypt:
                encryptPwd = password;
                break;
            case PassType.md5_3:
                encryptPwd = encryptPwd( password, "MD5" );
                break;
            case PassType.secondary_md5:
                encryptPwd = MD5SecondaryEncrypt( password );
                break;
            case PassType.crypt_5:
                encryptPwd = password;
                break;
            case PassType.md5_7:
                encryptPwd = encryptPwd( password, "MD5" );
                break;
            case PassType.AES:
                encryptPwd = AES_Encrypt( password, AES_Key );
                break;
            default:
                throw new UmsException( 0, "Unsupport Encryption type" );
            }
        }
        catch ( Exception e )
        {
            logger.error( "encryptPwd failed!" + e.getMessage(), e );
            // XXX check this
            // throw new Exception(e);
        }

        return encryptPwd;
    }

    /**
     * AES加密
     */
    public static String AES_Encrypt( String sSrc ) throws Exception
    {
        return AES_Encrypt( sSrc, AES_Key );
    }

    /**
     * AES加密
     */
    private static String AES_Encrypt( String sSrc, String sKey ) throws Exception
    {
        if ( sKey == null )
        {
            System.out.print( "Key为空null" );
            return null;
        }
        // 判断Key是否为16位
        if ( sKey.length() != 16 )
        {
            System.out.print( " Key长度不是16位 " );
            return null;
        }
        byte[] raw = sKey.getBytes( "utf-8" );
        SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
        Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );// "算法/模式/补码方式"
        cipher.init( Cipher.ENCRYPT_MODE, skeySpec );
        byte[] encrypted = cipher.doFinal( sSrc.getBytes( "utf-8" ) );

        String result = new Base64().encodeToString( encrypted );// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return result.replaceAll( "\r\n", "" ).replaceAll( "\r", "" ).replaceAll( "\n", "" );
    }

    /**
     * AES解密
     */
    public static String AES_Decrypt( String sSrc ) throws Exception
    {
        return AES_Decrypt( sSrc, AES_Key );
    }

    /**
     * AES解密
     */
    private static String AES_Decrypt( String sSrc, String sKey ) throws Exception
    {
        try
        {
            // 判断Key是否正确
            if ( sKey == null )
            {
                System.out.print( " Key为空null " );
                return null;
            }
            // 判断Key是否为16位
            if ( sKey.length() != 16 )
            {
                System.out.print( " Key长度不是16位 " );
                return null;
            }
            byte[] raw = sKey.getBytes( "utf-8" );
            SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
            Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );
            cipher.init( Cipher.DECRYPT_MODE, skeySpec );
            byte[] encrypted1 = new Base64().decode( sSrc );// 先用base64解密
            try
            {
                byte[] original = cipher.doFinal( encrypted1 );
                String originalString = new String( original, "utf-8" );
                return originalString;
            }
            catch ( Exception e )
            {
                System.out.println( e.toString() );
                return null;
            }
        }
        catch ( Exception ex )
        {
            System.out.println( ex.toString() );
            return null;
        }
    }

    public class PassType
    {
        /** 明文 **/
        public static final int plaintext = 0;

        /** md5加密 **/
        public static final int md5 = 1;

        /** 明文 **/
        public static final int crypt = 2;

        /** md5加密 同类型1 **/
        public static final int md5_3 = 3;

        /** 2次md5加密 **/
        public static final int secondary_md5 = 4;

        /** 使用ldap登陆 **/
        public static final int ldap = 6;

        /** 明文 **/
        public static final int crypt_5 = 5;

        /** md5加密 同类型1 **/
        public static final int md5_7 = 7;

        /** AES加密 **/
        public static final int AES = 8;
    }

    /**
     * 验证密码类型是否是有效类型
     * 
     * @param passType 密码类型
     * @return true:有效, false:失效
     */
    public static boolean validPassType( int passType )
    {
        int[] types = { PassType.plaintext, PassType.md5, PassType.crypt, PassType.md5_3, PassType.secondary_md5,
                PassType.crypt_5, PassType.md5_7, PassType.AES };
        for ( int i = 0; i < types.length; i++ )
        {
            if ( passType == types[i] )
            {
                return true;
            }
        }
        return false;
    }

    public static void main( String[] args ) throws Exception
    {
        String password = "q111111";
        System.out.println( "####" + SHAEncrypt.encryptPwd( password, SHAEncrypt.PassType.md5 ) + "%%%%%" );
    }
}
