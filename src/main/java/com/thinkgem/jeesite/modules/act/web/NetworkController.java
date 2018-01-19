package com.thinkgem.jeesite.modules.act.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.Msg;
import com.thinkgem.jeesite.modules.act.entity.LsAddress;
import com.thinkgem.jeesite.modules.act.entity.LsIp;
import com.thinkgem.jeesite.modules.act.entity.LsOffice;
import com.thinkgem.jeesite.modules.act.service.IpService;
import com.thinkgem.jeesite.modules.act.service.NetworkService;
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
public class NetworkController {

    @Autowired
    OfficeSchoolService officeSchoolService;

    @Autowired
    NetworkService networkService;
    @Autowired
    IpService ipService;


    @RequestMapping("/network")
//	@ResponseBody
    public String getOffice(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        return "modules/act/actTaskNetwork";
    }

    @RequestMapping("/networksAll")
    @ResponseBody
    public Msg getDatasWithJson(
            @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 这不是一个分页查询
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 10);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<LsAddress> data = networkService.getAll();
        for (int i = 0; i < data.size(); i++) {
//            if(officeSchoolService.getData(i) != null) {
            int oId = Integer.parseInt(data.get(i)
                    .getoId());
            if (officeSchoolService.getData(oId) != null) {
                data.get(i).setoId(officeSchoolService
                        .getData(oId)
                        .getoName());
            }
            System.out.println("officeSchoolService.getData(oId)    " + officeSchoolService.getData(oId));
        }
        System.out.println("----------------------> 库长度size" + data.size());
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(data, 5);
        System.out.println("----------->>" + page.toString());
        return Msg.success().add("pageInfo", page);
    }

    /**
     * 返回所有的部门信息，给下拉使用,大表添加时二级联动用到
     */
    @RequestMapping("/getNetworksWithOid")
    @ResponseBody
    public Msg getDeptsOid(@RequestParam("oId") String oId) {
        //查出的所有部门信息
        List<LsAddress> list = networkService.getAll();
//        LsAddress lsAddress = networkService.getData(Integer.parseInt(oId));
        List<LsAddress> result = new ArrayList<LsAddress>();
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).getoId().equals(oId)) {
                result.add(new LsAddress(list.get(j).getnId(), list.get(j).getoId(), list.get(j).getnMinAddress(), list.get(j).getnMaxAddress(), list.get(j).getMask()));
            }

        }
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = result.size() - 1; j > i; j--) {
                if (result.get(j).getnId().equals(result.get(i).getnId())) {
                    result.remove(j);
                }
            }
        }
        System.out.println("-----   >deps  " + result.size());
        return Msg.success().add("deps", result);
    }

    /**
     * 返回所有的部门信息，给下拉使用,大表添加时二级联动用到
     */
    @RequestMapping("/getIpWithOid")
    @ResponseBody
    public Msg getIpsOid(@RequestParam("nId") String nId) {
        //查出的所有部门信息
        List<LsIp> list = ipService.getAll();
//        LsAddress lsAddress = networkService.getData(Integer.parseInt(oId));
        List<LsIp> result = new ArrayList<LsIp>();
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).getnId().equals(nId)) {
                result.add(new LsIp(list.get(j).getiId(), list.get(j).getIp(), list.get(j).getnId(), list.get(j).getTag()));
            }

        }
       /* for (int i = 0; i < result.size() - 1; i++) {
            for (int j = result.size() - 1; j > i; j--) {
                if (result.get(j).getnId().equals(result.get(i).getnId())) {
                    result.remove(j);
                }
            }
        }*/
        System.out.println("-----   >deps  " + result.size());
        return Msg.success().add("ips", result);
    }


    @RequestMapping(value = "/network", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestParam(value = "dd", defaultValue = "ddd") String dd, LsAddress lsAddress, BindingResult result) {
        System.out.println("数据" + lsAddress);
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
//            networkService.saveData(lsAddress);
            networkService.saveDataGetId(lsAddress);
            String networdId = lsAddress.getnId() + "";
            String maxIp = lsAddress.getnMaxAddress();
            String minIp = lsAddress.getnMinAddress();
            String pre = StringUtils.substringBeforeLast(maxIp, ".");
            int min = Integer.parseInt(StringUtils.substringAfterLast(minIp, "."));
            int max = Integer.parseInt(StringUtils.substringAfterLast(maxIp, "."));

            for (int i = min; i <= max; i++) {
                ipService.saveData(new LsIp(pre + "." + i, networdId, "0"));

            }
            System.out.println("数据  前缀 " + pre + "  " + min + "-" + max);
            return Msg.success();
        }
    }
    /*
    */

    /**
     * 根据id查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/network/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@PathVariable("id") Integer id) {
//		System.out.println("------------------------>" + baseDataService.getData(id).getCampusName());
//		data.get(i).setoId(officeSchoolService.getData(Integer.parseInt(data.get(i).getoId())).getoName());

        LsAddress lsAddress = networkService.getData(id);
        lsAddress.setoId(officeSchoolService.getData(Integer.parseInt(lsAddress.getoId())).getoName());
        return Msg.success().add("das", lsAddress);
    }

    //update
    @ResponseBody
    @RequestMapping(value = "/network/{nId}", method = RequestMethod.PUT)
    public Msg saveData(LsAddress lsAddress, HttpServletRequest request) {
        System.out.println("lsOffice    --- >   " + lsAddress);
//		officeSchoolService.updateData(lsOffice);
        networkService.updateData(lsAddress);
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
    @RequestMapping(value = "/network/{ids}", method = RequestMethod.DELETE)
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
            networkService.deleteBatch(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            networkService.deleteData(id);
        }
        return Msg.success();
    }
    /**
     * 检查用户名是否可用
     * @param empName
     * @return
     *//*
    @ResponseBody
	@RequestMapping("/checkOffices")
	public Msg checkuser(@RequestParam("empName")String empName){

		//数据库用户名重复校验
		boolean b = officeSchoolService.checkName(empName);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "该校区名已存在");
		}
	}*/
}
