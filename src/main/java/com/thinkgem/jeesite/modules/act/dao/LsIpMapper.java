package com.thinkgem.jeesite.modules.act.dao;


import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.act.entity.LsIp;
import com.thinkgem.jeesite.modules.act.entity.LsIpExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface LsIpMapper {
    int countByExample(LsIpExample example);

    int deleteByExample(LsIpExample example);

    int deleteByPrimaryKey(Integer iId);

    int insert(LsIp record);

    int insertAndGetId(LsIp record);

    int insertSelective(LsIp record);

    List<LsIp> selectByExample(LsIpExample example);

    LsIp selectByPrimaryKey(Integer iId);

    int updateByExampleSelective(@Param("record") LsIp record, @Param("example") LsIpExample example);

    int updateByExample(@Param("record") LsIp record, @Param("example") LsIpExample example);

    int updateByPrimaryKeySelective(LsIp record);

    int updateByPrimaryKey(LsIp record);
}