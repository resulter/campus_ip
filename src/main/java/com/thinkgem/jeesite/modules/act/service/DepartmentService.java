package com.thinkgem.jeesite.modules.act.service;


import com.thinkgem.jeesite.modules.act.dao.LsDepartmentMapper;
import com.thinkgem.jeesite.modules.act.dao.LsOfficeMapper;
import com.thinkgem.jeesite.modules.act.entity.LsDepartment;
import com.thinkgem.jeesite.modules.act.entity.LsDepartmentExample;
import com.thinkgem.jeesite.modules.act.entity.LsOffice;
import com.thinkgem.jeesite.modules.act.entity.LsOfficeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {



    @Autowired
    LsDepartmentMapper lsDepartmentMapper;

    /**
     * 查询所有部门
     *
     * @return
     */
    public List<LsDepartment> getAll() {
        // TODO Auto-generated method stub
//        return lsOfficeMapper.selectByExample(null);
        return lsDepartmentMapper.selectByExample(null);
    }

    public LsDepartment getData(Integer id) {
        // TODO Auto-generated method stub
        LsDepartment lsDepartment = lsDepartmentMapper.selectByPrimaryKey(id);
        return lsDepartment;
    }
    /**
     * 部门保存
     *
     * @param lsDepartment
     */
    public void saveData(LsDepartment lsDepartment) {
        // TODO Auto-generated method stub
        lsDepartmentMapper.insert(lsDepartment);
    }
    public int saveDataGetId(LsDepartment lsDepartment) {
        return lsDepartmentMapper.insertAndGetId(lsDepartment);
    }

    /**
     * 部门更新
     *
     * @param lsDepartment
     */
    public void updateData(LsDepartment lsDepartment) {
        // TODO Auto-generated method stub
        lsDepartmentMapper.updateByPrimaryKeySelective(lsDepartment);
    }

    /**
     * 部门删除
     *
     * @param id
     */
    public void deleteData(Integer id) {
        // TODO Auto-generated method stub
        lsDepartmentMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        // TODO Auto-generated method stub
        LsDepartmentExample example = new LsDepartmentExample();
        LsDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDIdIn(ids);
        lsDepartmentMapper.deleteByExample(example);
    }

    /**
     * 检验部门名是否存在
     *
     * @param name
     * @return  true：代表当前不存在   fasle：已存在
     */
    public boolean checkName(String name) {
        LsDepartmentExample example = new LsDepartmentExample();
        LsDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDNameEqualTo(name);
        long count = lsDepartmentMapper.countByExample(example);
        return count == 0;
    }

    /**
     * 根据部门名称获取部门id
     * @param depName
     * @return
     */
    public int getId(String depName ){
        LsDepartmentExample example = new LsDepartmentExample();
        LsDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andDNameEqualTo(depName);
        return  lsDepartmentMapper.selectByExample(example).get(0).getdId();
    }

}
