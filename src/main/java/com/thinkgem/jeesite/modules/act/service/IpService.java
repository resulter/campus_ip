package com.thinkgem.jeesite.modules.act.service;


import com.thinkgem.jeesite.modules.act.dao.LsAddressMapper;
import com.thinkgem.jeesite.modules.act.dao.LsIpMapper;
import com.thinkgem.jeesite.modules.act.entity.LsAddress;
import com.thinkgem.jeesite.modules.act.entity.LsAddressExample;
import com.thinkgem.jeesite.modules.act.entity.LsIp;
import com.thinkgem.jeesite.modules.act.entity.LsIpExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpService {


    @Autowired
    LsAddressMapper lsAddressMapper;
    @Autowired
    LsIpMapper lsIpMapper;

    /**
     * 查询所有IP
     *
     * @return
     */
    public List<LsIp> getAll() {
        return lsIpMapper.selectByExample(null);
    }

    public LsIp getData(Integer id) {
        return lsIpMapper.selectByPrimaryKey(id);
    }

    /**
     * IP保存
     *
     * @param lsIp
     */
    public void saveData(LsIp lsIp) {
        lsIpMapper.insert(lsIp);
    }

    public int saveDataGetId(LsIp lsIp) {
        return lsIpMapper.insertAndGetId(lsIp);
    }


    /**
     * IP更新
     *
     * @param lsIp
     */
    public void updateData(LsIp lsIp) {
        lsIpMapper.updateByPrimaryKeySelective(lsIp);
    }

    /**
     * IP删除
     *
     * @param id
     */
    public void deleteData(Integer id) {
        lsIpMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        LsIpExample example1 = new LsIpExample();
        LsIpExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andIIdIn(ids);
        lsIpMapper.deleteByExample(example1);
    }

    /**
     * 检验校区名是否可用
     *
     * @param name
     * @return  true：代表当前姓名可用   fasle：不可用
     */
    public boolean checkName(String name) {
        // TODO Auto-generated method stub



        LsIpExample example = new LsIpExample();
        LsIpExample.Criteria criteria = example.createCriteria();
        criteria.andIpEqualTo(name);
        long count = lsIpMapper.countByExample(example);
        return count == 0;
    }

    /**
     * 根据部门名称获取部门id
     * @param name
     * @return
     */
    public int getId(String name ){
        LsIpExample example = new LsIpExample();
        LsIpExample.Criteria criteria = example.createCriteria();
        criteria.andIpEqualTo(name);
        return  lsIpMapper.selectByExample(example).get(0).getiId();
    }
}
