package com.quanshi.ums.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.quanshi.ums.dao.EnvionmentVariablesMapper;
import com.quanshi.ums.entity.EnvionmentVariables;
import com.quanshi.ums.entity.EnvionmentVariablesExample;
import com.quanshi.ums.service.EnvService;

@Service
@Transactional( readOnly = true, propagation = Propagation.SUPPORTS )
public class EnvServiceImpl implements EnvService
{

    private final Logger logger = LoggerFactory.getLogger( EnvServiceImpl.class );

    @Autowired
    private EnvionmentVariablesMapper envionmentVariablesMapper;

    @Override
    public boolean loadEnvVarBoolean( String envKey, boolean defaultValue )
    {
        EnvionmentVariablesExample example = new EnvionmentVariablesExample();
        example.createCriteria().andEnvKeyEqualTo( envKey );
        List<EnvionmentVariables> envList = envionmentVariablesMapper.selectByExample( example );
        if ( envList == null || envList.isEmpty() )
        {
            return defaultValue;
        }
        EnvionmentVariables envionmentVariables = envList.get( 0 );
        String envValue = envionmentVariables.getEnvValue();
        if ( StringUtils.isBlank( envValue ) )
        {
            return defaultValue;
        }
        return envValue.toLowerCase().equals( "true" );
    }

    @Override
    public Long loadEnvVarLong( String envKey, long defaultValue )
    {
        EnvionmentVariablesExample example = new EnvionmentVariablesExample();
        example.createCriteria().andEnvKeyEqualTo( envKey );
        List<EnvionmentVariables> envList = envionmentVariablesMapper.selectByExample( example );
        if ( envList == null || envList.isEmpty() )
        {
            return defaultValue;
        }
        EnvionmentVariables envionmentVariables = envList.get( 0 );
        String envValue = envionmentVariables.getEnvValue();
        if ( StringUtils.isBlank( envValue ) )
        {
            return defaultValue;
        }
        return Long.parseLong( envValue );
    }

    @Override
    public Integer loadEnvVarInteger( String envKey, int defaultValue )
    {
        EnvionmentVariablesExample example = new EnvionmentVariablesExample();
        example.createCriteria().andEnvKeyEqualTo( envKey );
        List<EnvionmentVariables> envList = envionmentVariablesMapper.selectByExample( example );
        if ( envList == null || envList.isEmpty() )
        {
            return defaultValue;
        }
        EnvionmentVariables envionmentVariables = envList.get( 0 );
        String envValue = envionmentVariables.getEnvValue();
        if ( StringUtils.isBlank( envValue ) )
        {
            return defaultValue;
        }
        return Integer.valueOf( envValue );
    }

    @Override
    public String loadEnvVarString( String envKey, String defaultValue )
    {
        EnvionmentVariablesExample example = new EnvionmentVariablesExample();
        example.createCriteria().andEnvKeyEqualTo( envKey );
        List<EnvionmentVariables> envList = envionmentVariablesMapper.selectByExample( example );
        if ( envList == null || envList.isEmpty() )
        {
            return defaultValue;
        }
        EnvionmentVariables envionmentVariables = envList.get( 0 );
        String envValue = envionmentVariables.getEnvValue();
        if ( StringUtils.isBlank( envValue ) )
        {
            return defaultValue;
        }
        return envValue;
    }
}
