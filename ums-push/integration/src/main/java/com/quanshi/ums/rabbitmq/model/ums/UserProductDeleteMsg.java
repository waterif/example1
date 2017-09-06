/**
 * 
 */
package com.quanshi.ums.rabbitmq.model.ums;

import java.util.Date;

/**
 * 用户产品删除消息体
 * 
 * @author yanxiang.huang 2017-06-12 14:40:20
 */
public class UserProductDeleteMsg
{
    /** id */
    private Long id;

    /** userId */
    private Long userId;

    /** productId */
    private Long productId;

    /** sitesId */
    private Long sitesId;

    /** userStatus */
    private Integer userStatus;

    /** status */
    private Integer status;

    /** createdTime */
    private Date createdTime;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId( Long userId )
    {
        this.userId = userId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId( Long productId )
    {
        this.productId = productId;
    }

    public Long getSitesId()
    {
        return sitesId;
    }

    public void setSitesId( Long sitesId )
    {
        this.sitesId = sitesId;
    }

    public Integer getUserStatus()
    {
        return userStatus;
    }

    public void setUserStatus( Integer userStatus )
    {
        this.userStatus = userStatus;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus( Integer status )
    {
        this.status = status;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime( Date createdTime )
    {
        this.createdTime = createdTime;
    }

}
