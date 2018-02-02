package com.thinkgem.jeesite.modules.act.service;


import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.LsAddressMapper;
import com.thinkgem.jeesite.modules.act.dao.LsOfficeMapper;
import com.thinkgem.jeesite.modules.act.entity.LsAddress;
import com.thinkgem.jeesite.modules.act.entity.LsAddressExample;
import com.thinkgem.jeesite.modules.act.entity.LsOffice;
import com.thinkgem.jeesite.modules.act.entity.LsOfficeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetworkService {


    @Autowired
    LsAddressMapper lsAddressMapper;



    /**
     * 查询所有网段
     *
     * @return
     */
    public List<LsAddress> getAll() {
        // TODO Auto-generated method stub
        return lsAddressMapper.selectByExample(null);
    }

    public LsAddress getData(Integer id) {
        return lsAddressMapper.selectByPrimaryKey(id);
    }

    /**
     * 网段保存
     *
     * @param lsAddress
     */
    public void saveData(LsAddress lsAddress) {
        // TODO Auto-generated method stub
        lsAddressMapper.insert(lsAddress);
    }

    public int saveDataGetId(LsAddress lsAddress) {
        // TODO Auto-generated method stub
        return lsAddressMapper.insertAndGetId(lsAddress);
    }


    /**
     * 网段更新
     *
     * @param lsAddress
     */
    public void updateData(LsAddress lsAddress) {
        // TODO Auto-generated method stub
        lsAddressMapper.updateByPrimaryKeySelective(lsAddress);
    }

    /**
     * 网段删除
     *
     * @param id
     */
    public void deleteData(Integer id) {
        // TODO Auto-generated method stub
        lsAddressMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        // TODO Auto-generated method stub
        LsAddressExample example = new LsAddressExample();
        LsAddressExample.Criteria criteria = example.createCriteria();
        criteria.andNIdIn(ids);
        lsAddressMapper.deleteByExample(example);
    }

    /**
     * 检验校区名是否可用
     *
     * @param name
     * @return true：代表当前姓名可用   fasle：不可用
     */
    public boolean checkName(String name) {
        // TODO Auto-generated method stub

        LsAddressExample example = new LsAddressExample();
        LsAddressExample.Criteria criteria = example.createCriteria();
        criteria.andNMinAddressEqualTo(name);
        long count = lsAddressMapper.countByExample(example);
        return count == 0;
    }

    public boolean checkNameByStr(String name) {
        LsAddressExample example = new LsAddressExample();
        LsAddressExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            name = "%" + name + "%";
        }
        criteria.andNMinAddressLike(name);
        long count = lsAddressMapper.countByExample(example);
        return count == 0;
    }


    public boolean checkNetwork(String name) {
        List<LsAddress> list =lsAddressMapper.selectByExample(null);
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.substringBeforeLast(list.get(i).getnMaxAddress(),".").equals(name) && StringUtils.substringBeforeLast(list.get(i).getnMinAddress(),".").equals(name)) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 根据部门名称获取部门id
     *
     * @param name
     * @return
     */
    public int getId(String name) {
        LsAddressExample example = new LsAddressExample();
        LsAddressExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(name)){
            name = "%" + name + "%";
        }
        criteria.andNMinAddressLike(name);
        return lsAddressMapper.selectByExample(example).get(0).getnId();
    }

}
