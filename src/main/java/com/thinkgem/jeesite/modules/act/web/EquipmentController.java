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
public class EquipmentController {

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
    @RequestMapping("/equipments")
//	@ResponseBody
    public String getOffice(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        return "modules/act/actTaskEquipment";
    }


    @RequestMapping("/equipmentsAll")
    @ResponseBody
    public Msg getDatasWithJson(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 10);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<LsEquipmentVo> data = equipmentService.getVoAll("","","","");
       /*
        mybatis  pagehelper分页插件只对PageHelper.startPage后的第一条查询起作用，后更改数据则不能正常切换页码，只能修改sql语句
        List<LsEquipment> data = equipmentService.getAll();
        List<LsEquipmentVo> result = new ArrayList<LsEquipmentVo>();
        System.out.println("data库长度" + data.size());

        for (int i = 0; i < data.size(); i++) {
            LsEquipment lsEquipment = data.get(i);
            LsEquipmentVo lsEquipmentVo = new LsEquipmentVo();
            String dId = lsEquipment.getdId();
            Integer eId = lsEquipment.geteId();
            String equipmentName = lsEquipment.getEquipmentName();
            String iId = lsEquipment.getiId();
            String location = lsEquipment.getLocation();
            String password = lsEquipment.getPassword();
            String remark = lsEquipment.getRemark();
            String username = lsEquipment.getUsername();

            LsDepartment lsDepartment = departmentService.getData(Integer.parseInt(dId));
            String dName = lsDepartment.getdName();

            LsIp lsIp = ipService.getData(Integer.parseInt(iId));
            String ip = lsIp.getIp();
            String nId = lsIp.getnId();
            String tag = lsIp.getTag();

            LsAddress lsAddress = networkService.getData(Integer.parseInt(nId));
            String mask = lsAddress.getMask();
            String maxAddress = lsAddress.getnMaxAddress();
            String minAddress = lsAddress.getnMinAddress();
            String address = StringUtils.substringBeforeLast(minAddress, ".") + "." + "0";
            String oId = lsAddress.getoId();

            LsOffice lsOffice = officeSchoolService.getData(Integer.parseInt(oId));
            String dIds = lsOffice.getdIds();
            String oName = lsOffice.getoName();


            result.add(new LsEquipmentVo(eId, iId, oName, oId, address, minAddress, maxAddress, mask, nId, ip, tag, dId, dName, equipmentName, location, username, password, remark));
            System.out.println("data数据  ：" + result.get(i));
        }*/
        System.out.println("----------------------> 库长度size" + data.size());
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(data, 5);
        System.out.println("----------->>" + page.toString());
        return Msg.success().add("pageInfo", page);
    }

    @RequestMapping(value = "/equipment", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestParam(value = "dd", defaultValue = "ddd") String dd, LsEquipmentVo lsEquipmentVo, BindingResult result) {
        System.out.println("lsEquipmentVo数据" + lsEquipmentVo);
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
            LsIp lsIp = ipService.getData(Integer.parseInt(lsEquipmentVo.getiId()));
            lsIp.setTag("1");
//            ipService.saveData(lsIp);
            ipService.updateData(lsIp);
            equipmentService.saveData(new LsEquipment(lsEquipmentVo.getiId(), lsEquipmentVo.getdId(), lsEquipmentVo.getEquipmentName(), lsEquipmentVo.getLocation(), lsEquipmentVo.getUsername(), lsEquipmentVo.getPassword(), lsEquipmentVo.getRemark()));
//            departmentService.saveData(lsDepartment);
            return Msg.success();
        }
    }

    /**
     * 根据ID查询信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/equipment/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@PathVariable("id") Integer id) {
//		System.out.println("------------------------>" + baseDataService.getData(id).getCampusName());

        LsEquipment lsEquipment = equipmentService.getData(id);
        LsEquipmentVo lsEquipmentVo = new LsEquipmentVo();
        if (lsEquipment != null) {
            String dId = lsEquipment.getdId();
            Integer eId = lsEquipment.geteId();
            String equipmentName = lsEquipment.getEquipmentName();
            String iId = lsEquipment.getiId();
            String location = lsEquipment.getLocation();
            String password = lsEquipment.getPassword();
            String remark = lsEquipment.getRemark();
            String username = lsEquipment.getUsername();

            LsDepartment lsDepartment = departmentService.getData(Integer.parseInt(dId));
            String dName = "";
            if(lsDepartment!=null) {
                 dName = lsDepartment.getdName();
            }

            LsIp lsIp = ipService.getData(Integer.parseInt(iId));
            String ip = lsIp.getIp();
            String nId = lsIp.getnId();
            String tag = lsIp.getTag();

            LsAddress lsAddress = networkService.getData(Integer.parseInt(nId));
            String mask = lsAddress.getMask();
            String maxAddress = lsAddress.getnMaxAddress();
            String minAddress = lsAddress.getnMinAddress();
            String address = StringUtils.substringBeforeLast(minAddress, ".") + "." + "0";
            String oId = lsAddress.getoId();

            LsOffice lsOffice = officeSchoolService.getData(Integer.parseInt(oId));
            String dIds = lsOffice.getdIds();
            String oName = lsOffice.getoName();


            LsEquipmentVo result = new LsEquipmentVo(eId, iId, oName, oId, address, minAddress, maxAddress, mask, nId, ip, tag, dId, dName, equipmentName, location, username, password, remark);
            System.out.println("vo===>>" + result);
            String network = StringUtils.substringBeforeLast(minAddress, ".");
            return Msg.success().add("equipmentVo", result).add("network",network);
        } else {
            return Msg.fail().add("equipmentVo", null);
        }
    }

    //update
    @ResponseBody
    @RequestMapping(value = "/equipment/{eId}", method = RequestMethod.PUT)
    public Msg saveData(LsEquipmentVo lsEquipmentVo, HttpServletRequest request) {
        System.out.println("lsOffice    --- >   " + lsEquipmentVo);

        LsEquipment lsEquipment = new LsEquipment(lsEquipmentVo.geteId(),lsEquipmentVo.getiId(), lsEquipmentVo.getdId(), lsEquipmentVo.getEquipmentName(), lsEquipmentVo.getLocation(), lsEquipmentVo.getUsername(), lsEquipmentVo.getPassword(), lsEquipmentVo.getRemark());
        equipmentService.updateData(lsEquipment);
        return Msg.success();
    }

    /**
     * 单个批量二合一
     * 批量删除：1-2-3
     * 单个删除：1
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/equipment/{ids}", method = RequestMethod.DELETE)
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
            equipmentService.deleteBatch(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            equipmentService.deleteData(id);
        }
        return Msg.success();
    }
    /*
    *//**
     * 返回所有的校区信息，给下拉使用,返回的是没有添加的部门名称，用于添加部门时使用
     *//*
    @RequestMapping("/getdepartments")
    @ResponseBody
    public Msg getDepts(@RequestParam("deps") String deps){
        //查出的所有部门信息
        List<LsDepartment> list = departmentService.getAll();
        List<LsDepartment> result = new ArrayList<LsDepartment>();
        for (int i = 0; i < list.size(); i++) {
            if(!deps.contains(list.get(i).getdName())){
                result.add(new LsDepartment(list.get(i).getdId(),list.get(i).getdName()));
            }
        }
        System.out.println("-----   >deps  " + result.size());
        return Msg.success().add("offices", result);
    }
    *//**
     * 返回所有的校区信息，给下拉使用,返回的是已经添加的部门名称，用于删除部门时使用
     *//*
    @RequestMapping("/getdepartmentsUsed")
    @ResponseBody
    public Msg getDeptsUse(@RequestParam("deps") String deps){
        //查出的所有部门信息
        List<LsDepartment> list = departmentService.getAll();
        List<LsDepartment> result = new ArrayList<LsDepartment>();
        for (int i = 0; i < list.size(); i++) {
            if(deps.contains(list.get(i).getdName())){
                result.add(new LsDepartment(list.get(i).getdId(),list.get(i).getdName()));
            }
        }
        for ( int i = 0 ; i < result.size() - 1 ; i ++ ) {
            for ( int j = result.size() - 1 ; j > i; j -- ) {
                if (result.get(j).getdName().equals(result.get(i).getdName())) {
                    result.remove(j);
                }
            }
        }
        System.out.println("-----   >deps  " + result.size());
        return Msg.success().add("offices", result);
    }




    *//**
     * 根据id查询
     *
     * @param id
     * @return
     *//*




    *//**
     * 单个批量二合一
     * 批量删除：1-2-3
     * 单个删除：1
     *
     * @param
     * @return
     *//*
    @ResponseBody
    @RequestMapping(value = "/department/{ids}", method = RequestMethod.DELETE)
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
            departmentService.deleteBatch(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            departmentService.deleteData(id);
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
    @RequestMapping("/checkDepartments")
    public Msg checkuser(@RequestParam("empName") String empName) {

        //数据库用户名重复校验
        boolean b = departmentService.checkName(empName);
        System.out.println("数据库用户名重复校验  " + b);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "该部门名已存在");
        }
    }*/

    /**
     * 返回查询条件集合
     *
     * @param request
     * @return
     */
    private Map<String, String> getSearchMap(HttpServletRequest request) {
        Map<String, String> searchMap = new HashMap<String, String>();
        //校区名称
        String ip_search_input = request.getParameter("ip_search_input");
        if (ip_search_input != null) {ip_search_input = ip_search_input.trim();}
        ip_search_input = ip_search_input != null ? ip_search_input : "";
        searchMap.put("ip_search_input", ip_search_input);
        //校区名称
        String equipment_search_input = request.getParameter("equipment_search_input");
        if (equipment_search_input != null) {equipment_search_input = equipment_search_input.trim();}
        equipment_search_input = equipment_search_input != null ? equipment_search_input : "";
        searchMap.put("equipment_search_input", equipment_search_input);
        //校区名称
        String address_search_select = request.getParameter("address_search_select");
        if (address_search_select != null) {address_search_select = address_search_select.trim();}
        address_search_select = address_search_select != null ? address_search_select : "";
        searchMap.put("address_search_select", address_search_select);

        return searchMap;
    }

}
