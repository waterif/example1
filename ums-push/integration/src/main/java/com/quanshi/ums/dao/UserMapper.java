package com.quanshi.ums.dao;

import com.quanshi.ums.entity.User;
import com.quanshi.ums.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    long countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    User selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_user
     *
     * @mbg.generated Mon Jun 12 16:13:31 CST 2017
     */
    int updateByPrimaryKey(User record);
}