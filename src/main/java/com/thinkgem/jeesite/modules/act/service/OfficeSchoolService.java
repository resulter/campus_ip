package com.thinkgem.jeesite.modules.act.service;


import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.*;
import com.thinkgem.jeesite.modules.act.entity.*;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OfficeSchoolService {


    @Autowired
    LsOfficeMapper lsOfficeMapper;

    @Autowired
    LsAddressMapper lsAddressMapper;
    @Autowired
    LsIpMapper lsIpMapper;
    @Autowired
    LsEquipmentMapper lsEquipmentMapper;

    /**
     * 查询所有校区
     *
     * @return
     */
    public List<LsOffice> getAll() {
        // TODO Auto-generated method stub
        return lsOfficeMapper.selectByExample(null);
    }

    public List<LsOffice> getAllWithCondition(String campus_search) {
        if (isContainChinese(campus_search)) {
            LsOfficeExample lsOfficeExample = new LsOfficeExample();
            if(StringUtils.isNotBlank(campus_search)){
                campus_search = "%" + campus_search + "%";
            }
            lsOfficeExample.createCriteria().andONameLike(campus_search);
            return lsOfficeMapper.selectByExample(lsOfficeExample);
        } else {
            List<LsOffice> result = new ArrayList<LsOffice>();
            List<LsOffice> beginData = lsOfficeMapper.selectByExample(null);
            for (LsOffice lsoffice : beginData) {

                try {
                    String shortPinyin = PinyinHelper.getShortPinyin(lsoffice.getoName());
                    if (shortPinyin.contains(campus_search)) {
                        result.add(lsoffice);
                    }
                } catch (PinyinException e) {
                    e.printStackTrace();
                    System.out.println("----------------------->>>>汉字转拼音失败");
                }
            }
            return result;
        }
    }

    public LsOffice getData(Integer id) {
        return lsOfficeMapper.selectByPrimaryKey(id);
    }

    /**
     * 校区保存
     *
     * @param lsOffice
     */
    public void saveData(LsOffice lsOffice) {
        // TODO Auto-generated method stub
        lsOfficeMapper.insert(lsOffice);
    }

    public int saveDataGetId(LsOffice lsOffice) {
        return lsOfficeMapper.insertAndGetId(lsOffice);
    }


    /**
     * 校区更新
     *
     * @param lsOffice
     */
    public void updateData(LsOffice lsOffice) {
        // TODO Auto-generated method stub
        lsOfficeMapper.updateByPrimaryKeySelective(lsOffice);
    }

    /**
     * 校区删除
     *
     * @param id
     */
    public void deleteData(Integer id) {
        LsOffice lsOffice = lsOfficeMapper.selectByPrimaryKey(id);
        LsAddressExample lsAddressExample = new LsAddressExample();
        lsAddressExample.createCriteria().andOIdEqualTo(lsOffice.getoId().toString());
        List<LsAddress> lsAddresses = lsAddressMapper.selectByExample(lsAddressExample);
        for (LsAddress lsAddress:lsAddresses) {
            LsIpExample lsIpExample = new LsIpExample();
            lsIpExample.createCriteria().andNIdEqualTo(lsAddress.getnId().toString());
            List<LsIp> lsIps = lsIpMapper.selectByExample(lsIpExample);
            for (LsIp lsIp:lsIps) {
                LsEquipmentExample lsEquipmentExample = new LsEquipmentExample();
                lsEquipmentExample.createCriteria().andIIdEqualTo(lsIp.getiId().toString());
                lsEquipmentMapper.deleteByExample(lsEquipmentExample);
            }
            lsIpMapper.deleteByExample(lsIpExample);
        }
        lsAddressMapper.deleteByExample(lsAddressExample);
        lsOfficeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        // TODO Auto-generated method stub
        LsOfficeExample example1 = new LsOfficeExample();
        LsOfficeExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andOIdIn(ids);

        List<LsOffice> lsOffices = lsOfficeMapper.selectByExample(example1);
        for (LsOffice lsOffice:lsOffices) {
            LsAddressExample lsAddressExample = new LsAddressExample();
            lsAddressExample.createCriteria().andOIdEqualTo(lsOffice.getoId().toString());
            List<LsAddress> lsAddresses = lsAddressMapper.selectByExample(lsAddressExample);
            for (LsAddress lsAddress:lsAddresses) {
                LsIpExample lsIpExample = new LsIpExample();
                lsIpExample.createCriteria().andNIdEqualTo(lsAddress.getnId().toString());
                List<LsIp> lsIps = lsIpMapper.selectByExample(lsIpExample);
                for (LsIp lsIp:lsIps) {
                    LsEquipmentExample lsEquipmentExample = new LsEquipmentExample();
                    lsEquipmentExample.createCriteria().andIIdEqualTo(lsIp.getiId().toString());
                    lsEquipmentMapper.deleteByExample(lsEquipmentExample);
                }
                lsIpMapper.deleteByExample(lsIpExample);
            }
            lsAddressMapper.deleteByExample(lsAddressExample);
        }
        lsOfficeMapper.deleteByExample(example1);
    }

    /**
     * 检验校区名是否可用
     *
     * @param name
     * @return true：代表当前姓名可用   fasle：不可用
     */
    public boolean checkName(String name) {
        // TODO Auto-generated method stub
        LsOfficeExample example1 = new LsOfficeExample();
        LsOfficeExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andONameEqualTo(name);

        long count = lsOfficeMapper.countByExample(example1);
        return count == 0;
    }

    /**
     * 根据部门名称获取部门id
     *
     * @param officeName
     * @return
     */
    public int getId(String officeName) {
        LsOfficeExample example = new LsOfficeExample();
        LsOfficeExample.Criteria criteria = example.createCriteria();
        criteria.andONameEqualTo(officeName);
        return lsOfficeMapper.selectByExample(example).get(0).getoId();
    }

    // 判断一个字符串是否含有汉字
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
