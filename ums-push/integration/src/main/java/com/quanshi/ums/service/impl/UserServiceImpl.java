package com.quanshi.ums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quanshi.ums.base.Base;
import com.quanshi.ums.dao.UserMapper;
import com.quanshi.ums.entity.User;
import com.quanshi.ums.entity.UserExample;
import com.quanshi.ums.service.UserService;

/**
 * 用户操作类
 * 
 * @author yanxiang.huang 2017-06-12 16:58:21
 */
@Service
public class UserServiceImpl extends Base implements UserService
{
    
    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo<User> getUserPage( int pageNum, int pageSize )
    {
        UserExample query = new UserExample();
        query.setOrderByClause( "id asc" );
        query.createCriteria().andIdGreaterThan( 10000l );
        PageHelper.startPage( pageNum, pageSize );
        List<User> datas = userMapper.selectByExample( query );
        PageInfo<User> page = new PageInfo<User>( datas );
        logger.info( toJson( page ) );
        return page;
    }

    @Override
    public User getUserById( Long userId )
    {
        return userMapper.selectByPrimaryKey( userId );
    }

}
