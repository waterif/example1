/**
 * 
 */
package com.quanshi.ums.rabbitmq.handler;

import com.quanshi.ums.rabbitmq.model.ums.UserUpdateSelectiveMsg;

/**
 * 用户事件处理
 * 
 * @author yanxiang.huang 2017-06-12 14:54:33
 */
public interface UserHandler
{

    /**
     * 更新用户
     *
     * @param msg
     */
    void updateSelective( UserUpdateSelectiveMsg msg );
}
