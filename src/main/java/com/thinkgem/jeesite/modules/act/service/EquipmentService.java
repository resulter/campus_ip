package com.thinkgem.jeesite.modules.act.service;


import com.thinkgem.jeesite.modules.act.dao.LsDepartmentMapper;
import com.thinkgem.jeesite.modules.act.dao.LsEquipmentMapper;
import com.thinkgem.jeesite.modules.act.entity.LsDepartment;
import com.thinkgem.jeesite.modules.act.entity.LsDepartmentExample;
import com.thinkgem.jeesite.modules.act.entity.LsEquipment;
import com.thinkgem.jeesite.modules.act.entity.LsEquipmentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {



    @Autowired
    LsDepartmentMapper lsDepartmentMapper;
    
    @Autowired
    LsEquipmentMapper lsEquipmentMapper;

    /**
     * 查询所有设备
     *
     * @return
     */
    public List<LsEquipment> getAll() {
        return lsEquipmentMapper.selectByExample(null);
    }

    public LsEquipment getData(Integer id) {
        return lsEquipmentMapper.selectByPrimaryKey(id);
    }
    /**
     * 设备保存
     *
     * @param lsEquipment
     */
    public void saveData(LsEquipment lsEquipment) {
        // TODO Auto-generated method stub
        lsEquipmentMapper.insert(lsEquipment);
    }


    /**
     * 设备更新
     *
     * @param lsEquipment
     */
    public void updateData(LsEquipment lsEquipment) {
        lsEquipmentMapper.updateByPrimaryKeySelective(lsEquipment);
    }

    /**
     * 设备删除
     *
     * @param id
     */
    public void deleteData(Integer id) {
        lsEquipmentMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        // TODO Auto-generated method stub
        LsEquipmentExample example = new LsEquipmentExample();
        LsEquipmentExample.Criteria criteria = example.createCriteria();
        criteria.andEIdIn(ids);
        lsEquipmentMapper.deleteByExample(example);
    }

    /**
     * 检验设备名是否可用
     *
     * @param name
     * @return  true：代表当前姓名可用   fasle：不可用
     */
   /* public boolean checkName(String name) {
        // TODO Auto-generated method stub
        
        LsDepartmentExample example = new LsDepartmentExample();
        LsDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDNameEqualTo(name);
        long count = lsDepartmentMapper.countByExample(example);
        return count == 0;
    }*/

}
