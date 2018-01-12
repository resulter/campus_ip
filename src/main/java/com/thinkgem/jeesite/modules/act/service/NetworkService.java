package com.thinkgem.jeesite.modules.act.service;


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
        return  lsAddressMapper.selectByPrimaryKey(id);
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
     * 检验网段名是否可用
     *
     * @param name
     * @return  true：代表当前姓名可用   fasle：不可用
     */
  /*  public boolean checkName(String name) {
        // TODO Auto-generated method stub
        LsOfficeExample example1 = new LsOfficeExample();
        LsOfficeExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andONameEqualTo(name);

        long count = lsOfficeMapper.countByExample(example1);
        return count == 0;
    }
*/
}
