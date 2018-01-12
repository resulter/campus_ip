package com.thinkgem.jeesite.modules.act.dao;


import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.act.entity.BaseData;
import com.thinkgem.jeesite.modules.act.entity.BaseDataExample;
import com.thinkgem.jeesite.modules.act.entity.BaseDataSearchMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface BaseDataMapper {
    int countByExample(BaseDataExample example);

    int deleteByExample(BaseDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseData record);

    int insertSelective(BaseData record);

    List<BaseData> selectByExample(BaseDataExample example);

    List<BaseData> selectConditionSearchMap(BaseDataSearchMap searchMap);

    BaseData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseData record, @Param("example") BaseDataExample example);

    int updateByExample(@Param("record") BaseData record, @Param("example") BaseDataExample example);

    int updateByPrimaryKeySelective(BaseData record);

    int updateByPrimaryKey(BaseData record);
}