/**
 * 
 */
package com.quanshi.ums.controller;

import com.quanshi.ums.base.Base;
import com.quanshi.ums.base.ResponseEntity;

/**
 * 基础控制器
 * 
 * @author yanxiang.huang 2017-06-14 12:33:12
 */
public abstract class BaseController extends Base
{

    /**
     * 返回成功
     *
     * @return
     */
    protected ResponseEntity<?> success()
    {
        ResponseEntity<?> result = new ResponseEntity<Object>( 0, "success." );
        return result;
    }

    /**
     * 返回成功
     *
     * @param obj
     * @return
     */
    protected ResponseEntity<?> success( Object obj )
    {
        ResponseEntity<?> result = new ResponseEntity<Object>( 0, "success.", obj );
        return result;
    }

    /**
     * 返回失败
     *
     * @return
     */
    protected ResponseEntity<?> failed()
    {
        ResponseEntity<?> result = new ResponseEntity<Object>( 1, "failed." );
        return result;
    }

    /**
     * 返回失败
     *
     * @param retMsg
     * @return
     */
    protected ResponseEntity<?> failed( String retMsg )
    {
        ResponseEntity<?> result = new ResponseEntity<Object>( 1, retMsg );
        return result;
    }

    /**
     * 返回失败
     *
     * @param retCode
     * @param retMsg
     * @return
     */
    protected ResponseEntity<?> failed( int retCode, String retMsg )
    {
        ResponseEntity<?> result = new ResponseEntity<Object>( retCode, retMsg );
        return result;
    }
}
