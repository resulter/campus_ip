package com.thinkgem.jeesite.modules.act.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.act.entity.LsAddress;
import com.thinkgem.jeesite.modules.act.entity.LsAddressExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@MyBatisDao
public interface LsAddressMapper {
    int countByExample(LsAddressExample example);

    int deleteByExample(LsAddressExample example);

    int deleteByPrimaryKey(Integer nId);

    int insert(LsAddress record);

    int insertAndGetId(LsAddress record);

    int insertSelective(LsAddress record);

    List<LsAddress> selectByExample(LsAddressExample example);

    LsAddress selectByPrimaryKey(Integer nId);

    int updateByExampleSelective(@Param("record") LsAddress record, @Param("example") LsAddressExample example);

    int updateByExample(@Param("record") LsAddress record, @Param("example") LsAddressExample example);

    int updateByPrimaryKeySelective(LsAddress record);

    int updateByPrimaryKey(LsAddress record);
}