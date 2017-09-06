/**
 * 
 */
package com.quanshi.ums.util;

/**
 * 通用方法抽象类
 * 
 * @author yanxiang.huang 2017-05-24 14:33:21
 */
public abstract class Common
{
    public final static String DEFAULT_CODE = "86";

    /**
     * 国家码处理<br>
     * 默认：86
     *
     * @param code
     * @return
     */
    public static String countryCode(String code) {
        if (code == null || "".equals( code )) {
            return DEFAULT_CODE;
        }
        return code;
    }
}
