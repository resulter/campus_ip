package com.thinkgem.jeesite.modules.act.service;


import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.LsDepartmentMapper;
import com.thinkgem.jeesite.modules.act.dao.LsEquipmentMapper;
import com.thinkgem.jeesite.modules.act.entity.*;
import com.thinkgem.jeesite.modules.act.vo.LsEquipmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {


    @Autowired
    LsDepartmentMapper lsDepartmentMapper;

    @Autowired
    LsEquipmentMapper lsEquipmentMapper;

    @Autowired
    IpService ipService;
    @Autowired
    NetworkService networkService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    OfficeSchoolService officeSchoolService;

    /**
     * 查询所有设备
     *
     * @return
     */
    public List<LsEquipment> getAll() {
        return lsEquipmentMapper.selectByExample(null);
    }

    public List<LsEquipmentVo> getVoAll(String officeId, String address_search_select, String ip_search_input, String equipment_search_input) {
        return lsEquipmentMapper.selectBySearchMap(new SearchMapNew(officeId, equipment_search_input, address_search_select, ip_search_input));
    }

    public List<LsEquipmentVo> getVoAllEquipment(String officeId, String address_search_select, String ip_search_input, String equipment_search_input) {
        return lsEquipmentMapper.selectBySearchMapForUserUtils(new SearchMapNew(officeId, equipment_search_input, address_search_select, ip_search_input));
    }


    public List<LsEquipmentVo> getResult(String officeId, String address_search_select, String ip_search_input, String equipment_search_input) {
        List<LsEquipment> data = lsEquipmentMapper.selectByExample(null);
        List<LsEquipmentVo> result = new ArrayList<LsEquipmentVo>();

        System.out.println("初始data大小  ： " + data.size());
        System.out.println("data库长度" + data.size());
        String equipmentName = null, ip = null, maxAddress = null, minAddress = null;
        for (int i = 0; i < data.size(); i++) {
            LsEquipment lsEquipment = data.get(i);
            LsEquipmentVo lsEquipmentVo = new LsEquipmentVo();
            String dId = lsEquipment.getdId();
            Integer eId = lsEquipment.geteId();

            equipmentName = lsEquipment.getEquipmentName();
            String iId = lsEquipment.getiId();
            String location = lsEquipment.getLocation();
            String password = lsEquipment.getPassword();
            String remark = lsEquipment.getRemark();
            String username = lsEquipment.getUsername();
            LsDepartment lsDepartment = departmentService.getData(Integer.parseInt(dId));

            String dName = lsDepartment.getdName();

            LsIp lsIp = ipService.getData(Integer.parseInt(iId));

            ip = lsIp.getIp();
            String nId = lsIp.getnId();
            String tag = lsIp.getTag();

            LsAddress lsAddress = networkService.getData(Integer.parseInt(nId));
            String mask = lsAddress.getMask();

            maxAddress = lsAddress.getnMaxAddress();

            minAddress = lsAddress.getnMinAddress();
            String address = StringUtils.substringBeforeLast(minAddress, ".") + "." + "0";
            String oId = lsAddress.getoId();

            LsOffice lsOffice = officeSchoolService.getData(Integer.parseInt(oId));
            String dIds = lsOffice.getdIds();
            String oName = lsOffice.getoName();

            if (Integer.parseInt(officeId) == Integer.parseInt(oId)) {
                result.add(new LsEquipmentVo(eId, iId, oName, oId, address, minAddress, maxAddress, mask, nId, ip, tag, dId, dName, equipmentName, location, username, password, remark));
            }
            System.out.println("  equals数据  oid  =" + oId + "   officeId  " + officeId);
            System.out.println(result.size() + "data前数据  ：" + result.toString());
        }
//        String [] ip = address_search_select.split(" - ");
        if (address_search_select != null && !"".equals(address_search_select) && !address_search_select.equals("全部网段")) {
            for (int i = result.size() - 1; i >= 0; i--) {
                if (!address_search_select.contains(StringUtils.substringBeforeLast(result.get(i).getMaxAddress(), "."))) {
                    System.out.println("啊address" + address_search_select + "  " + StringUtils.substringBeforeLast(result.get(i).getMaxAddress(), "."));
                    result.remove(i);

                }
            }
        }
        if (ip_search_input != null && !"".equals(ip_search_input)) {
            for (int i = result.size() - 1; i >= 0; i--) {
                if (!result.get(i).getIp().contains(ip_search_input)) {
                    System.out.println("移除的数据为" + i);
                    System.out.println("啊ip" + ip_search_input + "  " + result.get(i).getIp());
                    result.remove(i);

                }
            }
        }
        if (equipment_search_input != null && !"".equals(equipment_search_input)) {
            for (int i = result.size() - 1; i >= 0; i--) {
                if (!result.get(i).getEquipmentName().contains(equipment_search_input)) {
                    System.out.println("啊equip" + equipment_search_input + "  " + result.get(i).getEquipmentName());
                    result.remove(i);

                }
            }
        }
        System.out.println(result.size() + "data后数据  ：" + result.toString());
        System.out.println("----------------------> 库长度size" + result.size());
        return result;
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
     * @return true：代表当前姓名可用   fasle：不可用
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
