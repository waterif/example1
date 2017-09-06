package com.quanshi.ums.service;

import com.quanshi.ums.base.UmsException;

public interface TokenService
{
    public String getAccessToken( String appId, String secret ) throws UmsException;

}
