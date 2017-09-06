/**
 * 
 */
package com.quanshi.ums.service;

import java.util.List;

import com.quanshi.ums.entity.UserProduct;

/**
 * 用户产品操作类
 * 
 * @author yanxiang.huang 2017-06-13 10:04:13
 */
public interface UserProductService
{

    /**
     * 获取用户产品
     *
     * @param userId
     * @param productId
     * @return
     */
    UserProduct getUserProduct(long userId, long productId);

    /**
     * 根据站点ID查询用户产品
     *
     * @param siteId
     * @param productId
     * @return
     */
    List<UserProduct> getUserProductBySite( long siteId, long productId );
}
