/**
 * 
 */
package com.quanshi.ums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.quanshi.ums.base.Base;
import com.quanshi.ums.constant.Constants;
import com.quanshi.ums.dao.UserProductMapper;
import com.quanshi.ums.entity.UserProduct;
import com.quanshi.ums.entity.UserProductExample;
import com.quanshi.ums.service.UserProductService;

/**
 * 用户产品操作类
 * 
 * @author yanxiang.huang 2017-06-13 10:05:48
 */
@Service
public class UserProductServiceImpl extends Base implements UserProductService
{

    @Resource
    private UserProductMapper userProductMapper;

    @Override
    public UserProduct getUserProduct( long userId, long productId )
    {
        logger.info( "get user product. userId:{}, productId:{}.", userId, productId );
        UserProductExample query = new UserProductExample();
        query.setOrderByClause( "id desc" );
        query.createCriteria().andUserIdEqualTo( userId ).andProductIdEqualTo( productId )
                .andUserStatusIn( Constants.validProducts ).andStatusIn( Constants.validProducts );
        List<UserProduct> datas = userProductMapper.selectByExample( query );
        if ( CollectionUtils.isNotEmpty( datas ) )
        {
            if ( datas.size() > 1 )
            {
                logger.warn( "user product duplicate. userId:{}, productId:{}.", userId, productId );
            }
            return datas.get( 0 );
        }
        return null;
    }

    @Override
    public List<UserProduct> getUserProductBySite( long siteId, long productId )
    {
        logger.info( "get user product. siteId:{}, productId:{}.", siteId, productId );
        UserProductExample query = new UserProductExample();
        query.setOrderByClause( "id desc" );
        query.createCriteria().andSitesIdEqualTo( siteId ).andProductIdEqualTo( productId )
                .andUserStatusIn( Constants.validProducts ).andStatusIn( Constants.validProducts );
        return userProductMapper.selectByExample( query );
    }

}
