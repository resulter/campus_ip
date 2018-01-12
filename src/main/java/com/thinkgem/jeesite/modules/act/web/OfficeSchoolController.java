package com.thinkgem.jeesite.modules.act.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.Msg;
import com.thinkgem.jeesite.modules.act.entity.BaseData;
import com.thinkgem.jeesite.modules.act.entity.LsOffice;
import com.thinkgem.jeesite.modules.act.service.BaseDataService;
import com.thinkgem.jeesite.modules.act.service.DepartmentService;
import com.thinkgem.jeesite.modules.act.service.ExportService;
import com.thinkgem.jeesite.modules.act.service.OfficeSchoolService;
import com.thinkgem.jeesite.modules.act.utils.ImportExcelUtil;
import com.thinkgem.jeesite.modules.act.utils.POIUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 处理基础信息CRUD请求
 *
 * @author lfy
 */
@Controller
@RequestMapping(value = "/a/")
public class OfficeSchoolController {

    @Autowired
    OfficeSchoolService officeSchoolService;
    @Autowired
    DepartmentService departmentService;

    /**
     * 页面跳转专用
     *
     * @param pn
     * @return
     */
    @RequestMapping("/offices")
//	@ResponseBody
    public String getOffice(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        return "modules/act/actTaskOffice";
    }

    @RequestMapping("/officesAll")
    @ResponseBody
    public Msg getDatasWithJson(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 10);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<LsOffice> data = officeSchoolService.getAll();
        for (int i = 0; i < data.size(); i++) {
            String depName = "";
            if (StringUtils.isNotBlank(data.get(i).getdIds())) {
                String[] dep = data.get(i).getdIds().split(",");
                int[] deps = new int[dep.length];
                for (int j = 0; j < dep.length; j++) {
                    if(dep[j]!= null&&!dep[j].equals("null")) {
                        deps[j] = Integer.parseInt(dep[j]);
                        depName += departmentService.getData(deps[j]).getdName() + ",";

                    }
                }
                depName = depName.substring(0, depName.length() - 1);
                data.get(i).setdIds(depName);
            }
        }
        System.out.println("----------------------> 库长度size" + data.size());
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(data, 5);
        System.out.println("----------->>" + page.toString());
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 返回所有的校区信息，给下拉使用
     */
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

    /**
插入部门
     * @return
     */
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

      /**
插入部门
     * @return
     */
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


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
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

    /**
     * 单个批量二合一
     * 批量删除：1-2-3
     * 单个删除：1
     *
     * @param
     * @return
     */
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

    /**
     * 检查用户名是否可用
     *
     * @param empName
     * @return
     */
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
    }
}
