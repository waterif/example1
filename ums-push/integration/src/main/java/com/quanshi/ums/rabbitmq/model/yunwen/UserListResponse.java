/**
 * 
 */
package com.quanshi.ums.rabbitmq.model.yunwen;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 云问数据查询
 * 
 * @author yanxiang.huang 2017-06-13 09:27:07
 */
public class UserListResponse implements Serializable
{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** message */
    private String message;

    /** total */
    private int total;

    /** status */
    private int status;

    /** list */
    private List<UserResponse> list;

    /** currentPage */
    private int currentPage;

    /** totalPages */
    private int totalPages;

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal( int total )
    {
        this.total = total;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus( int status )
    {
        this.status = status;
    }

    public List<UserResponse> getList()
    {
        return list;
    }

    public void setList( List<UserResponse> list )
    {
        this.list = list;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage( int currentPage )
    {
        this.currentPage = currentPage;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages( int totalPages )
    {
        this.totalPages = totalPages;
    }

    public class UserResponse
    {

        private int Del;

        private String Action;

        private Date UpdateTime;

        private Long Id;

        private Long UserId;

        private String displayName;

        private Long SiteId;

        private Date AddTime;

        public int getDel()
        {
            return Del;
        }

        public void setDel( int del )
        {
            Del = del;
        }

        public String getAction()
        {
            return Action;
        }

        public void setAction( String action )
        {
            Action = action;
        }

        public Date getUpdateTime()
        {
            return UpdateTime;
        }

        public void setUpdateTime( Date updateTime )
        {
            UpdateTime = updateTime;
        }

        public Long getId()
        {
            return Id;
        }

        public void setId( Long id )
        {
            Id = id;
        }

        public Long getUserId()
        {
            return UserId;
        }

        public void setUserId( Long userId )
        {
            UserId = userId;
        }

        public String getDisplayName()
        {
            return displayName;
        }

        public void setDisplayName( String displayName )
        {
            this.displayName = displayName;
        }

        public Long getSiteId()
        {
            return SiteId;
        }

        public void setSiteId( Long siteId )
        {
            SiteId = siteId;
        }

        public Date getAddTime()
        {
            return AddTime;
        }

        public void setAddTime( Date addTime )
        {
            AddTime = addTime;
        }

    }
}
