/**
 * 
 */
package com.quanshi.ums.service;

import com.github.pagehelper.PageInfo;
import com.quanshi.ums.entity.User;

/**
 * 用户操作类
 * 
 * @author yanxiang.huang 2017-06-12 16:56:05
 */
public interface UserService
{

    PageInfo<User> getUserPage(int pageNum, int pageSize);

    /**
     * 通过ID查询用户
     *
     * @param userId
     * @return
     */
    User getUserById( Long userId ); 
}
