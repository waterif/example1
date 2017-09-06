/**
 * 
 */
package com.quanshi.ums.rabbitmq.handler.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.quanshi.ums.base.Base;
import com.quanshi.ums.constant.Constants;
import com.quanshi.ums.entity.User;
import com.quanshi.ums.entity.UserProduct;
import com.quanshi.ums.rabbitmq.handler.UserHandler;
import com.quanshi.ums.rabbitmq.model.ums.UserUpdateSelectiveMsg;
import com.quanshi.ums.service.UserProductService;
import com.quanshi.ums.service.UserService;
import com.quanshi.ums.service.YunwenService;

/**
 * 用户操作处理
 * 
 * @author yanxiang.huang 2017-06-12 15:32:09
 */
@Service
public class UserHandlerImpl extends Base implements UserHandler
{

    @Resource
    private UserProductService userProductService;

    @Resource
    private UserService userService;

    @Resource
    private YunwenService yunwenService;

    @Override
    public void updateSelective( UserUpdateSelectiveMsg msg )
    {
        if ( msg == null )
        {
            logger.warn( "msg can't be null." );
            return;
        }
        if ( !validate( msg ) )
        {
            logger.warn( "msg invalid. id:{}, displayName:{}, userstatus:{}.", msg.getId(), msg.getDisplayName(),
                    msg.getUserstatus() );
            return;
        }
        UserProduct userProduct = userProductService.getUserProduct( msg.getId(), Constants.PRODUCT_BEE );
        User user = userService.getUserById( msg.getId() );
        if ( userProduct == null || user == null )
        {
            return;
        }
        yunwenService.push( user, userProduct );
    }

    /**
     * 验证基本信息<br>
     * id必须可用，displayName和userstatus有一个被更改。
     *
     * @param msg
     * @return
     */
    private boolean validate( UserUpdateSelectiveMsg msg )
    {
        if ( msg.getId() == null || msg.getId() < 1 )
        {
            return false;
        }
        if ( msg.getDisplayName() == null && msg.getUserstatus() == null )
        {
            return false;
        }
        return true;
    }

}
