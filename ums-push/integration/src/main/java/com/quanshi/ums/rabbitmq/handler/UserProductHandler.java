/**
 * 
 */
package com.quanshi.ums.rabbitmq.handler;

import com.quanshi.ums.rabbitmq.model.ums.UserProductCreateMsg;
import com.quanshi.ums.rabbitmq.model.ums.UserProductDeleteMsg;
import com.quanshi.ums.rabbitmq.model.ums.UserProductUpdateSelectiveMsg;

/**
 * 处理用户产品事件
 * 
 * @author yanxiang.huang 2017-06-12 14:54:21
 */
public interface UserProductHandler
{

    /**
     * 新建用户产品
     *
     * @param msg
     */
    void create( UserProductCreateMsg msg );

    /**
     * 删除用户产品
     *
     * @param msg
     */
    void delete( UserProductDeleteMsg msg );

    /**
     * 更新用户产品
     *
     * @param msg
     */
    void updateSelective( UserProductUpdateSelectiveMsg msg );

}
