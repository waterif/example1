/**
 * 
 */
package com.quanshi.ums.rabbitmq.model;

/**
 * 
 * @author yanxiang.huang 2017-06-22 13:45:13
 */
public class NotifyMessage
{

    /** fromApp */
    private String fromApp;

    /** fromHost */
    private String fromHost;

    /** seq */
    private Long seq;

    /** time */
    private Long time;

    /** type */
    private String type;

    /** uuid */
    private String uuid;

    /** appProduct */
    private String appProduct;

    /** appEnv */
    private String appEnv;

    /** 消息key **/
    private String eventKey;

    /** 指定产品(optional) */
    private String eventProduct;

    /** 站点ID(optional) */
    private Long eventSiteId;

    /** 消息体 */
    private String eventData;

    /**
     * @return the fromApp
     */
    public String getFromApp()
    {
        return fromApp;
    }

    /**
     * @param fromApp the fromApp to set
     */
    public void setFromApp( String fromApp )
    {
        this.fromApp = fromApp;
    }

    /**
     * @return the fromHost
     */
    public String getFromHost()
    {
        return fromHost;
    }

    /**
     * @param fromHost the fromHost to set
     */
    public void setFromHost( String fromHost )
    {
        this.fromHost = fromHost;
    }

    /**
     * @return the seq
     */
    public Long getSeq()
    {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq( Long seq )
    {
        this.seq = seq;
    }

    /**
     * @return the time
     */
    public Long getTime()
    {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime( Long time )
    {
        this.time = time;
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType( String type )
    {
        this.type = type;
    }

    /**
     * @return the uuid
     */
    public String getUuid()
    {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid( String uuid )
    {
        this.uuid = uuid;
    }

    /**
     * @return the appProduct
     */
    public String getAppProduct()
    {
        return appProduct;
    }

    /**
     * @param appProduct the appProduct to set
     */
    public void setAppProduct( String appProduct )
    {
        this.appProduct = appProduct;
    }

    /**
     * @return the appEnv
     */
    public String getAppEnv()
    {
        return appEnv;
    }

    /**
     * @param appEnv the appEnv to set
     */
    public void setAppEnv( String appEnv )
    {
        this.appEnv = appEnv;
    }

    /**
     * @return the eventKey
     */
    public String getEventKey()
    {
        return eventKey;
    }

    /**
     * @param eventKey the eventKey to set
     */
    public void setEventKey( String eventKey )
    {
        this.eventKey = eventKey;
    }

    /**
     * @return the eventProduct
     */
    public String getEventProduct()
    {
        return eventProduct;
    }

    /**
     * @param eventProduct the eventProduct to set
     */
    public void setEventProduct( String eventProduct )
    {
        this.eventProduct = eventProduct;
    }

    /**
     * @return the eventSiteId
     */
    public Long getEventSiteId()
    {
        return eventSiteId;
    }

    /**
     * @param eventSiteId the eventSiteId to set
     */
    public void setEventSiteId( Long eventSiteId )
    {
        this.eventSiteId = eventSiteId;
    }

    /**
     * @return the eventData
     */
    public String getEventData()
    {
        return eventData;
    }

    /**
     * @param eventData the eventData to set
     */
    public void setEventData( String eventData )
    {
        this.eventData = eventData;
    }

}
