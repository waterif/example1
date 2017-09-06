package com.quanshi.ums.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author junqing.song
 * 用于数据加密和解密
 * 适用场景：需要反解密码的数据
 */
public class AES128Util
{
    private static Logger logger = LoggerFactory.getLogger( AES128Util.class );

    private static final int AES_LENGTH = 16;

    private static final String AES_KEY = "4h6mxj85vdq57rd7";

    // ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个参数iv
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public static final String RANDOM_SCOPE = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 数据加密
     * @param sSrc 明文
     * @return 密文
     * @throws Exception
     */
    public static String encrypt( String sSrc ) throws Exception
    {
        byte[] raw = AES_KEY.getBytes( "UTF-8" );
        SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );

        // "算法/模式/补码方式"
        Cipher cipher = Cipher.getInstance( CIPHER_ALGORITHM );

        String ivStr = StringUtil.randomStringSpecialScope( AES_LENGTH, RANDOM_SCOPE );

        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec( ivStr.getBytes() );

        cipher.init( Cipher.ENCRYPT_MODE, skeySpec, iv );
        byte[] encrypted = cipher.doFinal( sSrc.getBytes( "UTF-8" ) );

        return Base64.encodeBase64URLSafeString( encrypted ) + ivStr;
    }

    /**
     * 数据解密
     * @param sSrc 密文
     * @return 明文
     * @throws Exception
     */
    public static String decrypt( String sSrc ) throws Exception
    {
        if ( StringUtils.isEmpty( sSrc ) || sSrc.length() <= AES_LENGTH )
        {
            return null;
        }

        try
        {
            byte[] raw = AES_KEY.getBytes( "UTF-8" );
            SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );

            Cipher cipher = Cipher.getInstance( CIPHER_ALGORITHM );

            String pwdBase64Str = sSrc.substring( 0, sSrc.length() - AES_LENGTH );
            String ivStr = sSrc.substring( sSrc.length() - AES_LENGTH );

            IvParameterSpec iv = new IvParameterSpec( ivStr.getBytes( "UTF-8" ) );

            cipher.init( Cipher.DECRYPT_MODE, skeySpec, iv );

            byte[] decrypted = Base64.decodeBase64( pwdBase64Str );

            byte[] original = cipher.doFinal( decrypted );

            return new String( original, "UTF-8" );
        }
        catch ( Exception ex )
        {
            logger.error( ex.getMessage(), ex );
            return null;
        }
    }

    public static void main( String args[] )
    {
        try
        {
            System.out.println( encrypt( "9c922313-d67b-474e-a700-de3c49f32a49" ) );
            System.out
                    .println( decrypt( "1a2YosqHED3oQO6BByzVg9Bn-9LoqVlTh319WuMr5wAMgTDp3Vvzbg3A1LxRW-6Bd8ltu6f5r6m9u9of" ) );
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
