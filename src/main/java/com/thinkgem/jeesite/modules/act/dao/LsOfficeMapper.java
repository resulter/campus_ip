package com.thinkgem.jeesite.modules.act.dao;


import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.act.entity.LsOffice;
import com.thinkgem.jeesite.modules.act.entity.LsOfficeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface LsOfficeMapper {
    int countByExample(LsOfficeExample example);

    int deleteByExample(LsOfficeExample example);

    int deleteByPrimaryKey(Integer oId);

    int insert(LsOffice record);

    int insertAndGetId(LsOffice record);

    int insertSelective(LsOffice record);

    List<LsOffice> selectByExample(LsOfficeExample example);

    LsOffice selectByPrimaryKey(Integer oId);

    int updateByExampleSelective(@Param("record") LsOffice record, @Param("example") LsOfficeExample example);

    int updateByExample(@Param("record") LsOffice record, @Param("example") LsOfficeExample example);

    int updateByPrimaryKeySelective(LsOffice record);

    int updateByPrimaryKey(LsOffice record);
}