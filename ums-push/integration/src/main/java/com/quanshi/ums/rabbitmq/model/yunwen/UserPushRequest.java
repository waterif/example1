/**
 * 
 */
package com.quanshi.ums.rabbitmq.model.yunwen;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 数据同步接口数据
 * 
 * @author yanxiang.huang 2017-06-13 08:59:58
 */
public class UserPushRequest implements Serializable
{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** users */
    private List<UserRequest> users;

    public List<UserRequest> getUsers()
    {
        return users;
    }

    public void setUsers( List<UserRequest> users )
    {
        this.users = users;
    }
    
    public void add(UserRequest request) {
        if (users == null) {
            users = Lists.newArrayList();
        }
        users.add( request );
    }

    public class UserRequest
    {
        /** 操作：add,update,delete */
        private String action;

        /** 用户ID */
        private Long userId;

        /** 用户所属站点ID */
        private Long siteId;

        /** 用户名称 */
        private String displayName;

        public String getAction()
        {
            return action;
        }

        public void setAction( String action )
        {
            this.action = action;
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

        public String getDisplayName()
        {
            return displayName;
        }

        public void setDisplayName( String displayName )
        {
            this.displayName = displayName;
        }
    }

    public static enum Action
    {
        add, update, delete
    }
}
