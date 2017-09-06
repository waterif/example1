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
import com.quanshi.ums.rabbitmq.handler.UserProductHandler;
import com.quanshi.ums.rabbitmq.model.ums.UserProductCreateMsg;
import com.quanshi.ums.rabbitmq.model.ums.UserProductDeleteMsg;
import com.quanshi.ums.rabbitmq.model.ums.UserProductUpdateSelectiveMsg;
import com.quanshi.ums.service.UserProductService;
import com.quanshi.ums.service.UserService;
import com.quanshi.ums.service.YunwenService;

/**
 * 用户产品操作处理类
 * 
 * @author yanxiang.huang 2017-06-12 15:40:25
 */
@Service
public class UserProductHandlerImpl extends Base implements UserProductHandler
{

    @Resource
    private UserProductService userProductService;

    @Resource
    private UserService userService;

    @Resource
    private YunwenService yunwenService;

    @Override
    public void create( UserProductCreateMsg msg )
    {
        if ( msg == null )
        {
            logger.warn( "msg can't be null." );
            return;
        }
        if ( !validateAdd( msg ) )
        {
            logger.warn( "msg invalid. userId:{}, siteId:{}, userStatus:{}, status:{}.", msg.getUserId(),
                    msg.getSitesId(), msg.getUserStatus(), msg.getStatus() );
            return;
        }
        UserProduct userProduct = userProductService.getUserProduct( msg.getId(), Constants.PRODUCT_BEE );
        User user = userService.getUserById( msg.getId() );
        yunwenService.push( user, userProduct );
    }

    @Override
    public void delete( UserProductDeleteMsg msg )
    {
        if ( msg == null )
        {
            logger.warn( "msg can't be null." );
            return;
        }
        if ( !validateDelete( msg ) )
        {
            logger.warn( "msg invalid. userId:{}, siteId:{}, userStatus:{}, status:{}.", msg.getUserId(),
                    msg.getSitesId(), msg.getUserStatus(), msg.getStatus() );
            return;
        }
        UserProduct userProduct = userProductService.getUserProduct( msg.getId(), Constants.PRODUCT_BEE );
        User user = userService.getUserById( msg.getId() );
        yunwenService.push( user, userProduct );
    }

    @Override
    public void updateSelective( UserProductUpdateSelectiveMsg msg )
    {
        if ( msg == null )
        {
            logger.warn( "msg can't be null." );
            return;
        }
        if ( !validateUpdateSelective( msg ) )
        {
            logger.warn( "msg invalid. userId:{}, siteId:{}, userStatus:{}, status:{}.", msg.getUserId(),
                    msg.getSitesId(), msg.getUserStatus(), msg.getStatus() );
            return;
        }
        UserProduct userProduct = userProductService.getUserProduct( msg.getId(), Constants.PRODUCT_BEE );
        User user = userService.getUserById( msg.getId() );
        yunwenService.push( user, userProduct );
    }

    /**
     * 校验参数
     *
     * @param msg
     * @return
     */
    private boolean validateAdd( UserProductCreateMsg msg )
    {
        if ( msg.getProductId() == null || msg.getProductId() != Constants.PRODUCT_BEE )
        {
            return false;
        }
        if ( msg.getUserId() == null || msg.getUserId() < 1 )
        {
            return false;
        }
        if ( msg.getSitesId() == null || msg.getSitesId() < 1 )
        {
            return false;
        }
        if ( !(Constants.validProducts.contains( msg.getUserStatus() )
                && Constants.validProducts.contains( msg.getStatus() )) )
        {
            return false;
        }
        return true;
    }

    /**
     * 参数校验
     *
     * @param msg
     * @return
     */
    private boolean validateDelete( UserProductDeleteMsg msg )
    {
        if ( msg.getUserId() == null || msg.getUserId() < 1 )
        {
            return false;
        }
        return true;
    }

    /**
     * 参数校验<br>
     * 只关注siteId,userstatus,status变动
     *
     * @param msg
     * @return
     */
    private boolean validateUpdateSelective( UserProductUpdateSelectiveMsg msg )
    {
        if ( msg.getUserId() == null || msg.getUserId() < 1 )
        {
            return false;
        }
        if ( msg.getSitesId() == null && msg.getUserStatus() == null && msg.getStatus() == null )
        {
            return false;
        }
        return true;
    }
}
