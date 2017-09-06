/**
 * 
 */
package com.quanshi.ums.controller.plugins;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.quanshi.ums.base.ResponseEntity;
import com.quanshi.ums.controller.BaseController;
import com.quanshi.ums.controller.form.PushLogForm;
import com.quanshi.ums.entity.PushLog;
import com.quanshi.ums.rabbitmq.model.yunwen.UserPushResponse;
import com.quanshi.ums.service.PushLogService;

/**
 * 云问数据同步记录
 * 
 * @author yanxiang.huang 2017-06-15 14:09:16
 */
@Controller( "pluginYunwenController" )
@RequestMapping( "/admin/plugins/yunwen" )
public class YunwenController extends BaseController
{

    @Resource
    private PushLogService pushLogService;

    /**
     * 同步日志
     *
     * @param form
     * @param model
     * @return
     */
    @RequestMapping
    public String index( PushLogForm form, Model model )
    {
        logger.debug( "push log form:{}.", toJson( form ) );
        PageInfo<PushLog> page = pushLogService.getPage( form );
        model.addAttribute( "page", page );
        model.addAttribute( "form", form );
        return "plugins/yunwen/index";
    }

    /**
     * 重新同步
     *
     * @param id
     * @return
     */
    @RequestMapping( value = "/refresh", method = RequestMethod.POST )
    @ResponseBody
    public ResponseEntity<?> refresh( Long id )
    {
        if ( id == null || id < 1 )
        {
            return failed( "id invalid." );
        }
        UserPushResponse response = pushLogService.retry( id );
        if ( response == null )
        {
            return failed( "can not find push log with id:" + id );
        }
        if ( response.getCode() == 0 )
        {
            return success( response.getMessage() );
        }
        else
        {
            return failed( response.getMessage() );
        }
    }
}
