package com.thinkgem.jeesite.modules.act.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.Msg;
import com.thinkgem.jeesite.modules.act.entity.*;
import com.thinkgem.jeesite.modules.act.service.*;
import com.thinkgem.jeesite.modules.act.vo.LsEquipmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理基础信息CRUD请求
 *
 * @author lfy
 */
@Controller
@RequestMapping(value = "/a/")
public class OfficeDetailController {

    @Autowired
    OfficeSchoolService officeSchoolService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    NetworkService networkService;
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    IpService ipService;

    /**
     * 页面跳转专用
     *
     * @param pn
     * @return
     */
    @RequestMapping("/officeDetail")
//	@ResponseBody
    public String getOffice(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request, Model model) {
        System.out.println("大大大 +" + request.getParameter("officeId"));
        model.addAttribute("officeId", request.getParameter("officeId"));
        return "modules/act/actTaskOfficeDetail";
    }

    @RequestMapping("/officeDetailAll")
    @ResponseBody
    public Msg getDatasAll(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn, @RequestParam(value = "address_search_select", defaultValue = "") String address_search_select, @RequestParam(value = "ip_search_input", defaultValue = "") String ip_search_input, @RequestParam(value = "equipment_search_input", defaultValue = "") String equipment_search_input, @RequestParam(value = "officeId", defaultValue = "") String officeId, HttpServletRequest request) {

        System.out.println("传值" + address_search_select + "   " + ip_search_input + "  " + equipment_search_input); // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 10);
        // startPage后面紧跟的这个查询就是一个分页查询
//        List<LsEquipment> data = equipmentService.getAll();
        List<LsEquipmentVo> data = equipmentService.getVoAllEquipment(officeId, address_search_select, ip_search_input, equipment_search_input);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getIpTag().equals("0")) {
                data.get(i).setIpTag("是");
            }
            if (data.get(i).getIpTag().equals("1")) {
                data.get(i).setIpTag("否");
            }
        }
//        List<LsEquipmentVo> data = equipmentService.getResult(officeId, address_search_select, ip_search_input, equipment_search_input);
        System.out.println(" data 数据  " + data.size() + "    " + data.toString());
        System.out.println(" other数据  " + equipmentService.getVoAll(officeId, address_search_select, ip_search_input, equipment_search_input).size() + "    " + equipmentService.getAll().toString());
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(data, 5);
        System.out.println("----------->>" + page.toString());
        return Msg.success().add("pageInfo", page);
    }

    /*public List<LsEquipmentVo> getResult( String officeId, String address_search_select, String ip_search_input, String equipment_search_input) {
       List<LsEquipment> data = equipmentService.getAll();
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
    }*/

    /**
     * 返回所有的部门信息，搜索页面使用
     */
    @RequestMapping("/getNetworksDetailsWithOid")
    @ResponseBody
    public Msg getDeptsOid(@RequestParam("oId") String oId) {
        //查出的所有部门信息
        List<LsAddress> list = networkService.getAll();
//        LsAddress lsAddress = networkService.getData(Integer.parseInt(oId));
        List<LsAddress> result = new ArrayList<LsAddress>();
        result.add(new LsAddress(-1, "-1", "0.0.0.0", "0.0.0.0", "0.0.0.0"));
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).getoId().equals(oId)) {
                result.add(new LsAddress(list.get(j).getnId(), list.get(j).getoId(), list.get(j).getnMinAddress(), list.get(j).getnMaxAddress(), list.get(j).getMask()));
            }

        }
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = result.size() - 1; j > i; j--) {
                if (StringUtils.substringBeforeLast(String.valueOf(result.get(i).getnMinAddress()), ".").equals(StringUtils.substringBeforeLast(String.valueOf(result.get(j).getnMaxAddress()), "."))) {
                    result.remove(j);
                }
            }
        }
        System.out.println("-----   >deps  " + result.size());
        return Msg.success().add("deps", result);
    }


    /**
     * 返回所有的校区信息，给下拉使用
     *//*
    @RequestMapping("/getOffices")
    @ResponseBody
    public Msg getDepts() {
        //查出的所有部门信息
        List<LsOffice> list = officeSchoolService.getAll();
        return Msg.success().add("offices", list);
    }


    @RequestMapping(value = "/office", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestParam(value = "dd", defaultValue = "ddd") String dd, LsOffice lsOffice, BindingResult result) {
        System.out.println("数据" + lsOffice);
        if (result.hasErrors()) {
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            officeSchoolService.saveData(lsOffice);
            return Msg.success();
        }
    }

    *//**
     插入部门
     * @return
     *//*
    //update
    @ResponseBody
    @RequestMapping(value = "/depOfOffice/{oId}", method = RequestMethod.PUT)
    public Msg saveDep(LsOffice lsOffice ,String dId, HttpServletRequest request) {
        System.out.println("lsOffice    --- >   " + lsOffice +"   id  " + dId);
        LsOffice result = officeSchoolService.getData(lsOffice.getoId());
        System.out.println("lsOffice    --- >   " + request);
        if("".equals(result.getdIds())||result.getdIds() == null){
            result.setdIds(dId);
        }else {
            if(dId!= null) {
                result.setdIds(result.getdIds() + "," + dId);
            }
        }
        officeSchoolService.updateData(result);
        return Msg.success();
    }

      *//**
     插入部门
     * @return
     *//*
    //update
    @ResponseBody
    @RequestMapping(value = "/delDepOfOffice/{oId}", method = RequestMethod.PUT)
    public Msg delDep(LsOffice lsOffice ,String dId, HttpServletRequest request) {
        System.out.println("lsOffice    --- >   " + lsOffice +"   id  " + dId);
        LsOffice result = officeSchoolService.getData(lsOffice.getoId());
        System.out.println("lsOffice    --- >   " + request);

        String ids = result.getdIds();
        String[] str_emps = ids.split(",");
        String depResult = "";
        for (int i = 0; i < str_emps.length; i++) {
            if(!str_emps[i].equals(dId)){
                depResult += str_emps[i] + ",";
            }
        }
        if(depResult.length() >1) {
            depResult = depResult.substring(0, depResult.length() - 1);
            result.setdIds(depResult);
        }else {
            result.setdIds("");
        }

        officeSchoolService.updateData(result);
        return Msg.success();
    }


    *//**
     * 根据id查询
     *
     * @param id
     * @return
     *//*
    @RequestMapping(value = "/office/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@PathVariable("id") Integer id) {
//		System.out.println("------------------------>" + baseDataService.getData(id).getCampusName());
        LsOffice lsOffice = officeSchoolService.getData(id);
        return Msg.success().add("das", lsOffice);
    }

    //update
    @ResponseBody
    @RequestMapping(value = "/office/{oId}", method = RequestMethod.PUT)
    public Msg saveData(LsOffice lsOffice, HttpServletRequest request) {
        System.out.println("lsOffice    --- >   " + lsOffice);
        officeSchoolService.updateData(lsOffice);
        return Msg.success();
    }

    *//**
     * 单个批量二合一
     * 批量删除：1-2-3
     * 单个删除：1
     *
     * @param
     * @return
     *//*
    @ResponseBody
    @RequestMapping(value = "/office/{ids}", method = RequestMethod.DELETE)
    public Msg deleteData(@PathVariable("ids") String ids) {
        System.out.println("删除id   -------> " + ids);
        //批量删除
        if (ids.contains("-")) {
            List<Integer> del_ids = new ArrayList();
            String[] str_ids = ids.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            officeSchoolService.deleteBatch(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            officeSchoolService.deleteData(id);
        }
        return Msg.success();
    }

    *//**
     * 检查用户名是否可用
     *
     * @param empName
     * @return
     *//*
    @ResponseBody
    @RequestMapping("/checkOffices")
    public Msg checkuser(@RequestParam("empName") String empName) {

        //数据库用户名重复校验
        boolean b = officeSchoolService.checkName(empName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "该校区名已存在");
        }
    }*/
}
