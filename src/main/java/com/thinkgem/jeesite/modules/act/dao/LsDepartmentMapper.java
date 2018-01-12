package com.thinkgem.jeesite.modules.act.dao;


import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.act.entity.LsDepartment;
import com.thinkgem.jeesite.modules.act.entity.LsDepartmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface LsDepartmentMapper {
    int countByExample(LsDepartmentExample example);

    int deleteByExample(LsDepartmentExample example);

    int deleteByPrimaryKey(Integer dId);

    int insert(LsDepartment record);

    int insertSelective(LsDepartment record);

    List<LsDepartment> selectByExample(LsDepartmentExample example);

    LsDepartment selectByPrimaryKey(Integer dId);

    int updateByExampleSelective(@Param("record") LsDepartment record, @Param("example") LsDepartmentExample example);

    int updateByExample(@Param("record") LsDepartment record, @Param("example") LsDepartmentExample example);

    int updateByPrimaryKeySelective(LsDepartment record);

    int updateByPrimaryKey(LsDepartment record);
}