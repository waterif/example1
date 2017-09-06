/**
 * 
 */
package com.quanshi.ums.rabbitmq.model.ums;

import java.util.Date;

/**
 * 用户更新消息体
 * 
 * @author yanxiang.huang 2017-06-12 14:38:03
 */
public class UserUpdateSelectiveMsg
{

    private Long id;

    private String loginName;

    private String password;

    private String namepinyin;

    private String namepinyinFull;

    private String email;

    private Date registertime;

    private Date lastlogintime;

    private Date lastUpdateTime;

    private Integer userstatus;

    private Boolean userforgetflag;

    private Long countryId;

    private String timezone;

    private String mobileNumber;

    private Long organizationId;

    private Integer sex;

    private Boolean register;

    private Integer passType;

    private String firstName;

    private String middleName;

    private String lastName;

    private String externalUserName;

    private String externalUserAddr;

    private Long externalConfigId;

    private String position;

    private String displayName;

    private String office;

    private String officePhone;

    private String iconUrl;

    private String sign;

    private String cityCode;

    private String countryCode;

    private String subCode;

    private String phoneTypeName;

    private Integer role;

    private Date createTime;

    private Date passwordUpdateTime;

    private int passwordLevel;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName( String loginName )
    {
        this.loginName = loginName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getNamepinyin()
    {
        return namepinyin;
    }

    public void setNamepinyin( String namepinyin )
    {
        this.namepinyin = namepinyin;
    }

    public String getNamepinyinFull()
    {
        return namepinyinFull;
    }

    public void setNamepinyinFull( String namepinyinFull )
    {
        this.namepinyinFull = namepinyinFull;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public Date getRegistertime()
    {
        return registertime;
    }

    public void setRegistertime( Date registertime )
    {
        this.registertime = registertime;
    }

    public Date getLastlogintime()
    {
        return lastlogintime;
    }

    public void setLastlogintime( Date lastlogintime )
    {
        this.lastlogintime = lastlogintime;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime( Date lastUpdateTime )
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getUserstatus()
    {
        return userstatus;
    }

    public void setUserstatus( Integer userstatus )
    {
        this.userstatus = userstatus;
    }

    public Boolean getUserforgetflag()
    {
        return userforgetflag;
    }

    public void setUserforgetflag( Boolean userforgetflag )
    {
        this.userforgetflag = userforgetflag;
    }

    public Long getCountryId()
    {
        return countryId;
    }

    public void setCountryId( Long countryId )
    {
        this.countryId = countryId;
    }

    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone( String timezone )
    {
        this.timezone = timezone;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber( String mobileNumber )
    {
        this.mobileNumber = mobileNumber;
    }

    public Long getOrganizationId()
    {
        return organizationId;
    }

    public void setOrganizationId( Long organizationId )
    {
        this.organizationId = organizationId;
    }

    public Integer getSex()
    {
        return sex;
    }

    public void setSex( Integer sex )
    {
        this.sex = sex;
    }

    public Boolean getRegister()
    {
        return register;
    }

    public void setRegister( Boolean register )
    {
        this.register = register;
    }

    public Integer getPassType()
    {
        return passType;
    }

    public void setPassType( Integer passType )
    {
        this.passType = passType;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName( String middleName )
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getExternalUserName()
    {
        return externalUserName;
    }

    public void setExternalUserName( String externalUserName )
    {
        this.externalUserName = externalUserName;
    }

    public String getExternalUserAddr()
    {
        return externalUserAddr;
    }

    public void setExternalUserAddr( String externalUserAddr )
    {
        this.externalUserAddr = externalUserAddr;
    }

    public Long getExternalConfigId()
    {
        return externalConfigId;
    }

    public void setExternalConfigId( Long externalConfigId )
    {
        this.externalConfigId = externalConfigId;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition( String position )
    {
        this.position = position;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName( String displayName )
    {
        this.displayName = displayName;
    }

    public String getOffice()
    {
        return office;
    }

    public void setOffice( String office )
    {
        this.office = office;
    }

    public String getOfficePhone()
    {
        return officePhone;
    }

    public void setOfficePhone( String officePhone )
    {
        this.officePhone = officePhone;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public void setIconUrl( String iconUrl )
    {
        this.iconUrl = iconUrl;
    }

    public String getSign()
    {
        return sign;
    }

    public void setSign( String sign )
    {
        this.sign = sign;
    }

    public String getCityCode()
    {
        return cityCode;
    }

    public void setCityCode( String cityCode )
    {
        this.cityCode = cityCode;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode( String countryCode )
    {
        this.countryCode = countryCode;
    }

    public String getSubCode()
    {
        return subCode;
    }

    public void setSubCode( String subCode )
    {
        this.subCode = subCode;
    }

    public String getPhoneTypeName()
    {
        return phoneTypeName;
    }

    public void setPhoneTypeName( String phoneTypeName )
    {
        this.phoneTypeName = phoneTypeName;
    }

    public Integer getRole()
    {
        return role;
    }

    public void setRole( Integer role )
    {
        this.role = role;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime( Date createTime )
    {
        this.createTime = createTime;
    }

    public Date getPasswordUpdateTime()
    {
        return passwordUpdateTime;
    }

    public void setPasswordUpdateTime( Date passwordUpdateTime )
    {
        this.passwordUpdateTime = passwordUpdateTime;
    }

    public int getPasswordLevel()
    {
        return passwordLevel;
    }

    public void setPasswordLevel( int passwordLevel )
    {
        this.passwordLevel = passwordLevel;
    }

}
