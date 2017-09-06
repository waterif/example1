/**
 * 
 */
package com.quanshi.ums.service;

import java.util.List;

import com.quanshi.ums.entity.User;
import com.quanshi.ums.entity.UserProduct;
import com.quanshi.ums.rabbitmq.model.yunwen.UserListRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserListResponse;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushResponse;

/**
 * 云问服务处理
 * 
 * @author yanxiang.huang 2017-06-14 10:30:38
 */
public interface YunwenService
{

    /**
     * 向云问服务器推送变更信息
     *
     * @param request
     * @return
     */
    UserPushResponse push( UserPushRequest request );

    /**
     * 向云问服务器查询信息
     *
     * @param request
     * @return
     */
    UserListResponse list( UserListRequest request );

    /**
     * 云问推送（单条记录）
     *
     * @param user
     * @param userProduct
     */
    void push( User user, UserProduct userProduct );

    /**
     * 根据站点ID同步数据
     *
     * @param siteId
     * @return
     */
    boolean site( Long siteId );

    /**
     * 根据站点ID集同步数据
     *
     * @param sites
     * @return
     */
    boolean sites( List<Long> sites );

    /**
     * 根据用户ID同步数据
     *
     * @param userId
     * @return
     */
    boolean user( Long userId );

    /**
     * 根据用户ID集同步数据
     *
     * @param users
     * @return
     */
    boolean users( List<Long> users );

}
