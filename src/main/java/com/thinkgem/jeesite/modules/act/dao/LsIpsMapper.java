package com.thinkgem.jeesite.modules.act.dao;


import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.act.entity.LsIps;
import com.thinkgem.jeesite.modules.act.entity.LsIpsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface LsIpsMapper {
    int countByExample(LsIpsExample example);

    int deleteByExample(LsIpsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LsIps record);

    int insertSelective(LsIps record);

    List<LsIps> selectByExample(LsIpsExample example);

    LsIps selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LsIps record, @Param("example") LsIpsExample example);

    int updateByExample(@Param("record") LsIps record, @Param("example") LsIpsExample example);

    int updateByPrimaryKeySelective(LsIps record);

    int updateByPrimaryKey(LsIps record);
}