package com.quanshi.ums.dao;

import com.quanshi.ums.entity.EnvionmentVariables;
import com.quanshi.ums.entity.EnvionmentVariablesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnvionmentVariablesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    long countByExample(EnvionmentVariablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    int deleteByExample(EnvionmentVariablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    int insert(EnvionmentVariables record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    int insertSelective(EnvionmentVariables record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    List<EnvionmentVariables> selectByExample(EnvionmentVariablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    EnvionmentVariables selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    int updateByExampleSelective(@Param("record") EnvionmentVariables record, @Param("example") EnvionmentVariablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    int updateByExample(@Param("record") EnvionmentVariables record, @Param("example") EnvionmentVariablesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    int updateByPrimaryKeySelective(EnvionmentVariables record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tang_ums_envionment_variables
     *
     * @mbg.generated Mon Jun 12 17:59:48 CST 2017
     */
    int updateByPrimaryKey(EnvionmentVariables record);
}