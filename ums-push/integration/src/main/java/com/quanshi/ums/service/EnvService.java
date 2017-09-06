package com.quanshi.ums.service;

public interface EnvService
{
    boolean loadEnvVarBoolean( String envKey, boolean defaultValue );

    Long loadEnvVarLong( String envKey, long defaultValue );

    Integer loadEnvVarInteger( String envKey, int defaultValue );

    String loadEnvVarString( String string, String string2 );
}
