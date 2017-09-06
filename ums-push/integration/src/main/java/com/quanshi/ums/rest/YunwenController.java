/**
 * 
 */
package com.quanshi.ums.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quanshi.ums.base.ResponseEntity;
import com.quanshi.ums.controller.BaseController;
import com.quanshi.ums.rabbitmq.model.yunwen.UserListRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserListResponse;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushResponse;
import com.quanshi.ums.service.YunwenService;

/**
 * 云问数据接口
 * 
 * @author yanxiang.huang 2017-06-14 12:34:32
 */
@RestController
@RequestMapping( "/yunwen" )
public class YunwenController extends BaseController
{

    @Resource
    private YunwenService yunwenService;

    /**
     * 云问push接口直接调用
     *
     * @param request
     * @return
     */
    @RequestMapping( value = "/push", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public UserPushResponse push( @RequestBody UserPushRequest request )
    {
        logger.info( "direct push." );
        return yunwenService.push( request );
    }

    /**
     * 云问list接口直接调用
     *
     * @param request
     * @return
     */
    @RequestMapping( value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public UserListResponse list( @RequestBody UserListRequest request )
    {
        logger.info( "direct list." );
        return yunwenService.list( request );
    }

    /**
     * 根据站点同步数据
     *
     * @param siteId
     * @return
     */
    @RequestMapping( value = "/site/{siteId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> site( @PathVariable Long siteId )
    {
        logger.info( "post /site/{}.", siteId );
        boolean result = yunwenService.site( siteId );
        if ( result )
        {
            logger.info( "success. result:{}.", result );
            return success();
        }
        logger.info( "failed. result:{}.", result );
        return failed();
    }

    /**
     * 根据站点集同步数据
     *
     * @param sites
     * @return
     */
    @RequestMapping( value = "/sites", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> sites( @RequestBody List<Long> sites )
    {
        logger.info( "post /sites. request:{}.", toJson( sites ) );
        boolean result = yunwenService.sites( sites );
        if ( result )
        {
            logger.info( "success. result:{}.", result );
            return success();
        }
        logger.info( "failed. result:{}.", result );
        return failed();
    }

    /**
     * 根据用户同步数据
     *
     * @param userId
     * @return
     */
    @RequestMapping( value = "/user/{userId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> user( @PathVariable Long userId )
    {
        logger.info( "post /user/{}.", userId );
        boolean result = yunwenService.user( userId );
        if ( result )
        {
            logger.info( "success. result:{}.", result );
            return success();
        }
        logger.info( "failed. result:{}.", result );
        return failed();
    }

    /**
     * 根据用户集同步数据
     *
     * @param users
     * @return
     */
    @RequestMapping( value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> users( @RequestBody List<Long> users )
    {
        logger.info( "post /users. request:{}.", toJson( users ) );
        boolean result = yunwenService.users( users );
        if ( result )
        {
            logger.info( "success. result:{}.", result );
            return success();
        }
        logger.info( "failed. result:{}.", result );
        return failed();
    }
}
