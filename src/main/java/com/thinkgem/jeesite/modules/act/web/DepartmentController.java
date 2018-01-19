package com.thinkgem.jeesite.modules.act.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thinkgem.jeesite.modules.act.dao.Msg;
import com.thinkgem.jeesite.modules.act.entity.LsDepartment;
import com.thinkgem.jeesite.modules.act.entity.LsOffice;
import com.thinkgem.jeesite.modules.act.service.DepartmentService;
import com.thinkgem.jeesite.modules.act.service.IpService;
import com.thinkgem.jeesite.modules.act.service.OfficeSchoolService;
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
public class DepartmentController {

    @Autowired
    OfficeSchoolService officeSchoolService;

    @Autowired
    DepartmentService departmentService;
    @Autowired
    IpService service;

    /**
     * 页面跳转专用
     *
     * @param pn
     * @return
     */
    @RequestMapping("/departments")
//	@ResponseBody
    public String getOffice(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        return "modules/act/actTaskDepartment";
    }

    @RequestMapping("/departmentsAll")
    @ResponseBody
    public Msg getDatasWithJson(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 10);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<LsDepartment> data = departmentService.getAll();
        System.out.println("----------------------> 库长度size" + data.size());
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(data, 5);
        System.out.println("----------->>" + page.toString());
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 返回所有的部门信息，给下拉使用,返回的是没有添加的部门名称，用于添加部门时使用
     */
    @RequestMapping("/getdepartments")
    @ResponseBody
    public Msg getDepts(@RequestParam("deps") String deps) {
        //查出的所有部门信息
        List<LsDepartment> list = departmentService.getAll();
        List<LsDepartment> result = new ArrayList<LsDepartment>();
        for (int i = 0; i < list.size(); i++) {
            if (!deps.contains(list.get(i).getdName())) {
                result.add(new LsDepartment(list.get(i).getdId(), list.get(i).getdName()));
            }
        }
        System.out.println("-----   >deps  " + result.size());
        return Msg.success().add("offices", result);
    }

    /**
     * 返回所有的部门信息，给下拉使用,返回的是已经添加的部门名称，用于删除部门时使用
     */
    @RequestMapping("/getdepartmentsUsed")
    @ResponseBody
    public Msg getDeptsUse(@RequestParam("deps") String deps) {
        //查出的所有部门信息
        List<LsDepartment> list = departmentService.getAll();
        List<LsDepartment> result = new ArrayList<LsDepartment>();
        for (int i = 0; i < list.size(); i++) {
            if (deps.contains(list.get(i).getdName())) {
                result.add(new LsDepartment(list.get(i).getdId(), list.get(i).getdName()));
            }
        }
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = result.size() - 1; j > i; j--) {
                if (result.get(j).getdName().equals(result.get(i).getdName())) {
                    result.remove(j);
                }
            }
        }
        System.out.println("-----   >deps  " + result.size());
        return Msg.success().add("offices", result);
    }

    /**
     * 返回所有的部门信息，给下拉使用,大表添加时二级联动用到
     */
    @RequestMapping("/getDepartmentsWithOid")
    @ResponseBody
    public Msg getDeptsOid(@RequestParam("oId") String oId) {
        //查出的所有部门信息
        List<LsDepartment> list = departmentService.getAll();
        LsOffice lsOffice = officeSchoolService.getData(Integer.parseInt(oId));
        String deps = lsOffice.getdIds();
        String[] dep = deps.split(",");
        System.out.println(lsOffice.getoName() + "部门有  " + deps);
        List<LsDepartment> result = new ArrayList<LsDepartment>();
        for (int i = 0; i < dep.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getdId().equals(Integer.parseInt(dep[i]))) {
                    result.add(new LsDepartment(list.get(i).getdId(), list.get(i).getdName()));
                }

            }
        }
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = result.size() - 1; j > i; j--) {
                if (result.get(j).getdName().equals(result.get(i).getdName())) {
                    result.remove(j);
                }
            }
        }
        System.out.println("-----   >deps  " + result.size());
        return Msg.success().add("deps", result);
    }


    @RequestMapping(value = "/department", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestParam(value = "dd", defaultValue = "ddd") String dd, LsDepartment lsDepartment, BindingResult result) {
        System.out.println("数据" + lsDepartment);
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
            departmentService.saveData(lsDepartment);
            return Msg.success();
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@PathVariable("id") Integer id) {
//		System.out.println("------------------------>" + baseDataService.getData(id).getCampusName());
        LsDepartment lsDepartment = departmentService.getData(id);
        return Msg.success().add("das", lsDepartment);
    }

    //update
    @ResponseBody
    @RequestMapping(value = "/department/{dId}", method = RequestMethod.PUT)
    public Msg saveData(LsDepartment lsDepartment, HttpServletRequest request) {
        System.out.println("lsOffice    --- >   " + lsDepartment);
        departmentService.updateData(lsDepartment);
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

    /**
     * 检查用户名是否可用
     *
     * @param empName
     * @return
     */
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
    }
}
