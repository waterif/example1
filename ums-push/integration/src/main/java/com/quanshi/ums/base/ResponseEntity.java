package com.quanshi.ums.base;

public class ResponseEntity<T>
{
    private int retCode;

    private String retMsg;

    private String requestId;

    private T retObj;

    public ResponseEntity( int retCode )
    {
        super();
        this.retCode = retCode;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity( int retCode, String retMsg )
    {
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity( int retCode, String retMsg, T retObj )
    {
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.retObj = retObj;
        this.requestId = RequestHandler.getRequestId();
    }

    public ResponseEntity( T retObj )
    {
        super();
        this.retCode = ErrorCode.SUCCESS;
        this.retMsg = "SUCCESS";
        this.retObj = retObj;
        this.requestId = RequestHandler.getRequestId();
    }

    public int getRetCode()
    {
        return retCode;
    }

    public void setRetCode( int retCode )
    {
        this.retCode = retCode;
    }

    public String getRetMsg()
    {
        return retMsg;
    }

    public void setRetMsg( String retMsg )
    {
        this.retMsg = retMsg;
    }

    public T getRetObj()
    {
        return retObj;
    }

    public void setRetObj( T retObj )
    {
        this.retObj = retObj;
    }

    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId( String requestId )
    {
        this.requestId = requestId;
    }

    @Override
    public String toString()
    {
        return "ResponseEntity [retCode=" + retCode + ", retMsg=" + retMsg + ", requestId=" + requestId + ", retObj="
                + retObj + "]";
    }

}
