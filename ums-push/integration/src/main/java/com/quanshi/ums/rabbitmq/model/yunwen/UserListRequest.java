/**
 * 
 */
package com.quanshi.ums.rabbitmq.model.yunwen;

import java.io.Serializable;

/**
 * 
 * @author yanxiang.huang 2017-06-13 09:02:49
 */
public class UserListRequest implements Serializable
{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 页码 */
    private int pageNo;

    /** 每页数量 */
    private int pageSize;

    /** 站点ID */
    private Long siteId;

    /** 用户ID */
    private Long userId;

    /** 显示名 */
    private String displayName;

    /** 排序类型：1,正序,2,倒序 */
    private Integer orderType;

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo( int pageNo )
    {
        this.pageNo = pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize( int pageSize )
    {
        this.pageSize = pageSize;
    }

    public Long getSiteId()
    {
        return siteId;
    }

    public void setSiteId( Long siteId )
    {
        this.siteId = siteId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId( Long userId )
    {
        this.userId = userId;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName( String displayName )
    {
        this.displayName = displayName;
    }

    public Integer getOrderType()
    {
        return orderType;
    }

    public void setOrderType( Integer orderType )
    {
        this.orderType = orderType;
    }

}
