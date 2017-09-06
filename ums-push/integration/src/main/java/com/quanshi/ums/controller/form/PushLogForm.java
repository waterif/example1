/**
 * 
 */
package com.quanshi.ums.controller.form;

/**
 * 云问推送记录表单查询
 * 
 * @author yanxiang.huang 2017-06-15 14:27:24
 */
public class PushLogForm
{

    private Integer page = 1;

    private Integer pageSize = 10;

    private Long userId;

    private Long siteId;

    private Integer status;

    private Integer times;

    private String displayName;

    private String createTimeStart;

    private String createTimeEnd;

    private String updateTimeStart;

    private String updateTimeEnd;

    public Integer getPage()
    {
        if ( page == null || page == 0 )
        {
            page = 1;
        }
        return page;
    }

    public void setPage( Integer page )
    {
        this.page = page;
    }

    public Integer getPageSize()
    {
        if ( pageSize == null || pageSize == 0 )
        {
            pageSize = 50;
        }
        return pageSize;
    }

    public void setPageSize( Integer pageSize )
    {
        this.pageSize = pageSize;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId( Long userId )
    {
        this.userId = userId;
    }

    public Long getSiteId()
    {
        return siteId;
    }

    public void setSiteId( Long siteId )
    {
        this.siteId = siteId;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus( Integer status )
    {
        this.status = status;
    }

    public Integer getTimes()
    {
        return times;
    }

    public void setTimes( Integer times )
    {
        this.times = times;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName( String displayName )
    {
        this.displayName = displayName;
    }

    public String getCreateTimeStart()
    {
        return createTimeStart;
    }

    public void setCreateTimeStart( String createTimeStart )
    {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd()
    {
        return createTimeEnd;
    }

    public void setCreateTimeEnd( String createTimeEnd )
    {
        this.createTimeEnd = createTimeEnd;
    }

    public String getUpdateTimeStart()
    {
        return updateTimeStart;
    }

    public void setUpdateTimeStart( String updateTimeStart )
    {
        this.updateTimeStart = updateTimeStart;
    }

    public String getUpdateTimeEnd()
    {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd( String updateTimeEnd )
    {
        this.updateTimeEnd = updateTimeEnd;
    }

}
