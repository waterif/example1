/**
 * 
 */
package com.quanshi.ums.rabbitmq.model.ums;

import java.util.Date;

/**
 * 更新用户产品消息体
 * 
 * @author yanxiang.huang 2017-06-12 14:40:57
 */
public class UserProductUpdateSelectiveMsg
{
    private Long id;

    private Long userId;

    private Long productId;

    private Long sitesId;

    private Integer userStatus;

    private Integer status;

    private Date createdTime;

    private Date statusModifyTime;

    private Byte isHostAccount;

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

    public Date getStatusModifyTime()
    {
        return statusModifyTime;
    }

    public void setStatusModifyTime( Date statusModifyTime )
    {
        this.statusModifyTime = statusModifyTime;
    }

    public Byte getIsHostAccount()
    {
        return isHostAccount;
    }

    public void setIsHostAccount( Byte isHostAccount )
    {
        this.isHostAccount = isHostAccount;
    }

}
