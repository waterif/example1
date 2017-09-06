/**
 * 
 */
package com.quanshi.ums.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quanshi.ums.base.Base;
import com.quanshi.ums.controller.form.PushLogForm;
import com.quanshi.ums.dao.PushLogMapper;
import com.quanshi.ums.entity.PushLog;
import com.quanshi.ums.entity.PushLogExample;
import com.quanshi.ums.entity.PushLogExample.Criteria;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushRequest.UserRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushResponse;
import com.quanshi.ums.service.PushLogService;
import com.quanshi.ums.service.YunwenService;
import com.quanshi.ums.util.DateHelp;

/**
 * 云问推送记录
 * 
 * @author yanxiang.huang 2017-06-13 11:01:10
 */
@Service
public class PushLogServiceImpl extends Base implements PushLogService
{

    @Resource
    private PushLogMapper pushLogMapper;

    @Resource
    private YunwenService yunwenService;

    @Override
    public void success( UserPushRequest request )
    {
        List<UserRequest> datas = request.getUsers();
        if ( CollectionUtils.isNotEmpty( datas ) )
        {
            for ( UserRequest user : datas )
            {
                logger.info( "push success. action:{}, userId:{}.", user.getAction(), user.getUserId() );
                PushLog log = buildLog( user );
                log.setStatus( 1 );
                pushLogMapper.insertSelective( log );
            }
        }
    }

    @Override
    public void fail( UserPushRequest request )
    {
        List<UserRequest> datas = request.getUsers();
        if ( CollectionUtils.isNotEmpty( datas ) )
        {
            for ( UserRequest user : datas )
            {
                logger.info( "push fail. action:{}, userId:{}.", user.getAction(), user.getUserId() );
                PushLog log = buildLog( user );
                log.setStatus( 0 );
                pushLogMapper.insertSelective( log );
            }
        }
    }

    @Override
    public PageInfo<PushLog> getPage( PushLogForm form )
    {
        if ( form == null )
        {
            logger.warn( "form can't be null." );
            return null;
        }
        
        PushLogExample query = new PushLogExample();
        Criteria ctr = query.createCriteria();
        if ( form.getUserId() != null )
        {
            ctr.andUserIdEqualTo( form.getUserId() );
        }
        if ( form.getSiteId() != null )
        {
            ctr.andSiteIdEqualTo( form.getSiteId() );
        }
        if ( StringUtils.isNotEmpty( form.getDisplayName() ) )
        {
            ctr.andDisplayNameLike( "%" + form.getDisplayName() + "%" );
        }
        if ( form.getStatus() != null )
        {
            ctr.andStatusEqualTo( form.getStatus() );
        }
        if ( form.getTimes() != null && form.getTimes() > -1)
        {
            ctr.andTimesEqualTo( form.getTimes() );
        }
        if ( form.getCreateTimeStart() != null )
        {
            Date date = DateHelp.parse( form.getCreateTimeStart() );
            if ( date != null )
            {
                ctr.andCreateTimeGreaterThanOrEqualTo( date );
            }
        }
        if ( form.getCreateTimeEnd() != null )
        {
            Date date = DateHelp.parse( form.getCreateTimeEnd() );
            if ( date != null )
            {
                ctr.andCreateTimeLessThanOrEqualTo( date );
            }
        }
        if ( form.getUpdateTimeStart() != null )
        {
            Date date = DateHelp.parse( form.getUpdateTimeStart() );
            if ( date != null )
            {
                ctr.andUpdateTimeGreaterThanOrEqualTo( date );
            }
        }
        if ( form.getUpdateTimeEnd() != null )
        {
            Date date = DateHelp.parse( form.getUpdateTimeEnd() );
            if ( date != null )
            {
                ctr.andUpdateTimeLessThanOrEqualTo( date );
            }
        }
        PageHelper.startPage( form.getPage(), form.getPageSize() );
        List<PushLog> datas = pushLogMapper.selectByExample( query );
        if ( CollectionUtils.isNotEmpty( datas ) )
        {
            return new PageInfo<PushLog>( datas );
        }
        return null;
    }

    @Override
    public UserPushResponse retry( Long id )
    {
        PushLog log = pushLogMapper.selectByPrimaryKey( id );
        if ( log != null )
        {
            if ( log.getStatus() == 1 )
            {
                UserPushResponse res = new UserPushResponse();
                res.setCode( 0 );
                return res;
            }
            UserPushResponse res = yunwenService.push( buildPushRequest( log ) );
            if ( res != null && res.getCode() == 0 )
            {
                log.setStatus( 1 );
            }
            retry( log );
            return res;
        }
        return null;
    }
    

    @Override
    public void retryInterval()
    {
        PushLogExample query = new PushLogExample();
        query.createCriteria().andStatusEqualTo( 0 ).andTimesLessThan( 10 );
        PageHelper.startPage( 1, 100 );
        List<PushLog> datas = pushLogMapper.selectByExample( query );
        if (CollectionUtils.isEmpty( datas )) {
            return;
        }
        for ( PushLog log : datas )
        {
            UserPushResponse res = yunwenService.push( buildPushRequest( log ) );
            if ( res != null && res.getCode() == 0 )
            {
                log.setStatus( 1 );
            }
            retry( log );
        }
        
    }

    /**
     * 更新日志
     *
     * @param log
     */
    private void retry( PushLog log )
    {
        PushLog selective = new PushLog();
        selective.setId( log.getId() );
        selective.setStatus( log.getStatus() );
        selective.setTimes( log.getTimes() + 1 );
        selective.setUpdateTime( new Date() );
        pushLogMapper.updateByPrimaryKeySelective( selective );
    }

    /**
     * 通过Log构建push请求
     *
     * @param log
     * @return
     */
    private UserPushRequest buildPushRequest( PushLog log )
    {
        UserPushRequest push = new UserPushRequest();
        UserRequest req = push.new UserRequest();
        req.setAction( log.getAction() );
        req.setUserId( log.getUserId() );
        req.setSiteId( log.getSiteId() );
        req.setDisplayName( log.getDisplayName() );
        push.add( req );
        return push;
    }

    /**
     * 构建基础log
     *
     * @param request
     * @return
     */
    private PushLog buildLog( UserRequest request )
    {
        PushLog log = new PushLog();
        log.setAction( request.getAction() );
        log.setUserId( request.getUserId() );
        log.setSiteId( request.getSiteId() );
        log.setDisplayName( request.getDisplayName() );
        log.setTimes( 0 );
        log.setCreateTime( new Date() );
        log.setUpdateTime( new Date() );
        return log;
    }
}
