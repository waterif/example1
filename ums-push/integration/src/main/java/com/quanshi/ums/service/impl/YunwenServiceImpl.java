/**
 * 
 */
package com.quanshi.ums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.quanshi.ums.base.Base;
import com.quanshi.ums.constant.Constants;
import com.quanshi.ums.entity.User;
import com.quanshi.ums.entity.UserProduct;
import com.quanshi.ums.rabbitmq.model.yunwen.UserListRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserListResponse;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushRequest.Action;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushRequest.UserRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushResponse;
import com.quanshi.ums.service.PushLogService;
import com.quanshi.ums.service.UserProductService;
import com.quanshi.ums.service.UserService;
import com.quanshi.ums.service.YunwenService;
import com.quanshi.ums.util.RestTemplateUtil;

/**
 * 云问服务处理类
 * 
 * @author yanxiang.huang 2017-06-14 10:32:11
 */
@Service
public class YunwenServiceImpl extends Base implements YunwenService
{
    @Resource
    private RestTemplate rest;
    
    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Resource
    private PushLogService pushLogService;

    @Resource
    private UserProductService userProductService;

    @Resource
    private UserService userService;

    @Override
    public UserPushResponse push( UserPushRequest request )
    {
        logger.debug( "push request:{}.", toJson( request ) );
        MultiValueMap<String, String> heads = new LinkedMultiValueMap<String, String>();
        heads.add( "Content-Type", MediaType.APPLICATION_JSON_VALUE );
        HttpEntity<UserPushRequest> requestEntity = new HttpEntity<UserPushRequest>( request, heads );
        ResponseEntity<UserPushResponse> resp = null;
        try
        {
            long stime = System.currentTimeMillis();
            resp = restTemplateUtil.getRestTemplate().postForEntity( Constants.YUNWEN_SERVICE_SYNC_DATA_PUSH_URL, requestEntity,
                    UserPushResponse.class );
            long cost = System.currentTimeMillis() - stime;
            logger.debug( "request yunwen server cost:{}ms.", cost );
            logger.info( "yunwen server response:{}.", toJson( resp.getBody() ) );
            return resp.getBody();
        }
        catch ( Exception e )
        {
            logger.error( "yunwen server return error. error:{}.", e.getMessage() );
        }
        return null;
    }

    @Override
    public UserListResponse list( UserListRequest request )
    {
        logger.debug( "get list request:{}.", toJson( request ) );
        MultiValueMap<String, String> requestEntity = buildListForm( request );
        ResponseEntity<UserListResponse> resp = null;
        try
        {
            long stime = System.currentTimeMillis();
            resp = restTemplateUtil.getRestTemplate().postForEntity( Constants.YUNWEN_SERVICE_SYNC_DATA_LIST_URL, requestEntity,
                    UserListResponse.class );
            long cost = System.currentTimeMillis() - stime;
            logger.debug( "request yunwen server cost:{}ms.", cost );
            logger.info( "yunwen server response:{}.", toJson( resp.getBody() ) );
            return resp.getBody();
        }
        catch ( Exception e )
        {
            logger.error( "yunwen server return error. error:{}.", e.getMessage() );
        }
        return null;
    }

    @Override
    public void push( User user, UserProduct userProduct )
    {
        UserPushRequest request = buildPushRequest( user, userProduct );
        if ( request == null )
        {
            logger.warn( "user and user product can't be both null." );
            return;
        }
        send( request );
    }

    @Override
    public boolean site( Long siteId )
    {
        List<UserProduct> datas = userProductService.getUserProductBySite( siteId, Constants.PRODUCT_BEE );
        if ( CollectionUtils.isEmpty( datas ) )
        {
            return false;
        }
        for ( UserProduct userProduct : datas )
        {
            User user = userService.getUserById( userProduct.getUserId() );
            send( buildPushRequest( user, userProduct ) );
        }
        return true;
    }

    @Override
    public boolean sites( List<Long> sites )
    {
        for ( Long siteId : sites )
        {
            site( siteId );
        }
        return true;
    }

    @Override
    public boolean user( Long userId )
    {
        User user = userService.getUserById( userId );
        UserProduct userProduct = userProductService.getUserProduct( userId, Constants.PRODUCT_BEE );
        return send( buildPushRequest( user, userProduct ) );
    }

    @Override
    public boolean users( List<Long> users )
    {
        for ( Long userId : users )
        {
            user( userId );
        }
        return true;
    }

    /**
     * 统一发送(单条)
     *
     * @param request
     */
    private boolean send( UserPushRequest request )
    {
        try
        {
            UserPushResponse res = push( request );
            if ( (res != null && res.getCode() == 0 && res.getMessage().contains( "同步成功" )) || (res != null
                    && res.getCode() == 0 && request.getUsers().get( 0 ).getAction().equals( Action.delete.name() )) )
            {
                pushLogService.success( request );
                return true;
            }
            else
            {
                boolean retry = false;
                if ( request.getUsers().get( 0 ).getAction().equals( Action.update.name() ) )
                {
                    logger.info( "update push fail, try add push." );
                    request.getUsers().get( 0 ).setAction( Action.add.name() );
                    retry = send( request );
                }
                if ( !retry )
                {
                    pushLogService.fail( request );
                }
                return retry;
            }
        }
        catch ( Exception e )
        {
            logger.error( "push error.", e );
            pushLogService.fail( request );
            return false;
        }
    }

    /**
     * 构建查询表单
     *
     * @param request
     * @return
     */
    private MultiValueMap<String, String> buildListForm( UserListRequest request )
    {
        if ( request == null )
        {
            return null;
        }

        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        if ( request.getPageNo() < 1 || request.getPageSize() < 1 )
        {
            return null;
        }
        form.add( "pageNo", request.getPageNo() + "" );
        form.add( "pageSize", request.getPageSize() + "" );
        if ( request.getUserId() != null )
        {
            form.add( "userId", request.getUserId().toString() );
        }
        if ( request.getSiteId() != null )
        {
            form.add( "siteId", request.getSiteId().toString() );
        }
        if ( StringUtils.isNotEmpty( request.getDisplayName() ) )
        {
            form.add( "displayName", request.getDisplayName() );
        }
        if ( request.getOrderType() != null && (request.getOrderType() == 1 || request.getOrderType() == 2) )
        {
            form.add( "orderType", request.getOrderType().toString() );
        }
        return form;
    }

    /**
     * 构建请求体(单条数据同步)
     *
     * @param action 操作：add,delete,update
     * @param userId 用户ID
     * @param siteId 站点ID
     * @param name 显示名
     * @return
     */
    private UserPushRequest buildPushRequest( Action action, Long userId, Long siteId, String name )
    {
        UserPushRequest req = new UserPushRequest();
        UserRequest userReq = req.new UserRequest();
        userReq.setAction( action.name() );
        userReq.setUserId( userId );
        userReq.setSiteId( siteId );
        userReq.setDisplayName( name );
        req.add( userReq );
        return req;
    }

    /**
     * 构建请求体(单条数据同步)
     *
     * @param user
     * @param userProduct
     * @return
     */
    private UserPushRequest buildPushRequest( User user, UserProduct userProduct )
    {
        if ( user == null && userProduct == null )
        {
            return null;
        }
        else if ( user == null && userProduct != null )
        {
            return buildPushRequest( Action.delete, userProduct.getUserId(), userProduct.getSitesId(), null );
        }
        else if ( user != null && userProduct == null )
        {
            return buildPushRequest( Action.delete, user.getId(), null, user.getDisplayName() );
        }
        else
        {
            if ( user.getUserstatus() == null || user.getUserstatus() != 1
                    || !Constants.validProducts.contains( userProduct.getUserStatus() )
                    || !Constants.validProducts.contains( userProduct.getStatus() ) )
            {
                return buildPushRequest( Action.delete, user.getId(), userProduct.getSitesId(), user.getDisplayName() );
            }
            else
            {
                return buildPushRequest( Action.update, user.getId(), userProduct.getSitesId(), user.getDisplayName() );
            }
        }
    }

}
