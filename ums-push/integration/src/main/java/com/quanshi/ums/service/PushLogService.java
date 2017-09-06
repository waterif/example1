/**
 * 
 */
package com.quanshi.ums.service;

import com.github.pagehelper.PageInfo;
import com.quanshi.ums.controller.form.PushLogForm;
import com.quanshi.ums.entity.PushLog;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushRequest;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushResponse;

/**
 * 云问推送记录
 * 
 * @author yanxiang.huang 2017-06-13 10:55:35
 */
public interface PushLogService
{

    /**
     * 推送成功
     *
     * @param request
     */
    void success( UserPushRequest request );

    /**
     * 推送失败
     *
     * @param request
     */
    void fail( UserPushRequest request );

    /**
     * 获取分页数据
     *
     * @param form
     * @return
     */
    PageInfo<PushLog> getPage( PushLogForm form );

    /**
     * 重新推送
     *
     * @param id
     * @return
     */
    UserPushResponse retry( Long id );
    
    /**
     * 定时重试失败推送
     *
     */
    void retryInterval();

}
