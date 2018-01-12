package com.thinkgem.jeesite.modules.act.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.act.entity.LsEquipment;
import com.thinkgem.jeesite.modules.act.entity.LsEquipmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface LsEquipmentMapper {
    int countByExample(LsEquipmentExample example);

    int deleteByExample(LsEquipmentExample example);

    int deleteByPrimaryKey(Integer eId);

    int insert(LsEquipment record);

    int insertSelective(LsEquipment record);

    List<LsEquipment> selectByExample(LsEquipmentExample example);

    LsEquipment selectByPrimaryKey(Integer eId);

    int updateByExampleSelective(@Param("record") LsEquipment record, @Param("example") LsEquipmentExample example);

    int updateByExample(@Param("record") LsEquipment record, @Param("example") LsEquipmentExample example);

    int updateByPrimaryKeySelective(LsEquipment record);

    int updateByPrimaryKey(LsEquipment record);
}