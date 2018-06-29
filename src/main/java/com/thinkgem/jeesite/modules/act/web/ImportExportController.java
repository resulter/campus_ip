package com.thinkgem.jeesite.modules.act.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.Msg;
import com.thinkgem.jeesite.modules.act.entity.*;
import com.thinkgem.jeesite.modules.act.service.*;
import com.thinkgem.jeesite.modules.act.utils.ImportExcelUtil;
import com.thinkgem.jeesite.modules.act.utils.POIUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.ietf.jgss.Oid;
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
 * 导入导出专用
 *
 */
@Controller
@RequestMapping(value = "/a/")
public class ImportExportController {

    @Autowired
    BaseDataService baseDataService;
//	EmployeeService employeeService;

    @Autowired
    ExportService exportService;

    @Autowired
    EquipmentService equipmentService;
    @Autowired
    IpService ipService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    OfficeSchoolService officeSchoolService;
    @Autowired
    NetworkService networkService;


    @RequestMapping(value = "/exportExcelNew")
    public String exportExcel(HttpServletRequest request, HttpServletResponse response) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String timer = format.format(date);
        HSSFWorkbook wb = exportService.export();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + timer + "ipInfo.xls");
        OutputStream ouputStream = null;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "modules/act/actTaskofficeDetail";
    }

    @RequestMapping(value = "/importExcelNew")
    public String importExcel() {
        return "modules/act/actTaskDataImportNew";
    }

    @RequestMapping(value = "/uploadFileAndImportNew")
    @ResponseBody
    public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) throws IOException {

        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
//        String fileName = new Date().getTime()+".jpg";
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileInputStream input = null;
        try {
            input = new FileInputStream(targetFile);
            MultipartFile multipartFile = new MockMultipartFile(targetFile.getName(), targetFile.getName(), "text/plain", IOUtils.toByteArray(input));
            List<String[]> list = POIUtil.readExcel(multipartFile);
            for (int i = 0; i < list.size(); i++) {
                System.out.println("list数组" + list.get(i));
            }

            FileInputStream fis = new FileInputStream(targetFile);
            Map<String, String> m = new HashMap<String, String>();
            m.put("校区名称", "校区名称");
            m.put("网络地址段", "网络地址段");
            m.put("掩码", "掩码");
            m.put("ip地址", "ip地址");
            m.put("使用设备", "使用设备");
            m.put("使用部门", "使用部门");
            m.put("存放位置", "存放位置");
            m.put("用户名", "用户名");
            m.put("密码", "密码");
            m.put("备注", "备注");
            List<Map<String, Object>> result = ImportExcelUtil.parseExcel(fis, targetFile.getName(), m);
           /* for (int i = 0; i < result.size(); i++) {
                System.out.println("----result"+result.get(i).toString());
            }*/
            List<LsEquipment> lsEquipments = new ArrayList<LsEquipment>();
            for (int i = 0; i < result.size(); i++) {
                if (!("".equals(result.get(i).get("校区名称")) && "".equals(result.get(i).get("网络地址段")) && "".equals(result.get(i).get("掩码"))
                        && "".equals(result.get(i).get("ip地址")) && "".equals(result.get(i).get("使用设备")) && "".equals(result.get(i).get("使用部门"))
                        && "".equals(result.get(i).get("存放位置")) && "".equals(result.get(i).get("用户名")) && "".equals(result.get(i).get("密码"))
                        && "".equals(result.get(i).get("备注")))) {
// TODO: 2018/2/1 部门为空时，无法返回部门id，待解决 
                    String officename = (result.get(i).get("校区名称") == null || (result.get(i).get("校区名称")).equals("")) ? (String) result.get(0).get("校区名称") : (String) result.get(i).get("校区名称");
                    String address = (result.get(i).get("网络地址段") == null || (result.get(i).get("网络地址段")).equals("")) ? (String) result.get(0).get("网络地址段") : (String) result.get(i).get("网络地址段");
                    String mask = (result.get(i).get("掩码") == null || (result.get(i).get("掩码")).equals("")) ? (String) result.get(0).get("掩码") : (String) result.get(i).get("掩码");
                    String ip = (result.get(i).get("ip地址") == null || (result.get(i).get("ip地址")).equals("")) ? "0.0.0.0" : (String) result.get(i).get("ip地址");
                    String equipment = (result.get(i).get("使用设备") == null || (result.get(i).get("使用设备")).equals("")) ? "" : (String) result.get(i).get("使用设备");
                    String department = (result.get(i).get("使用部门") == null || (result.get(i).get("使用部门")).equals("")) ? "" : (String) result.get(i).get("使用部门");
                    String location = (result.get(i).get("存放位置") == null || (result.get(i).get("存放位置")).equals("")) ? "" : (String) result.get(i).get("存放位置");
                    String username = (result.get(i).get("用户名") == null || (result.get(i).get("用户名")).equals("")) ? "" : (String) result.get(i).get("用户名");
                    String password = (result.get(i).get("密码") == null || (result.get(i).get("密码")).equals("")) ? "" : (String) result.get(i).get("密码");
                    String remark = (result.get(i).get("备注") == null || (result.get(i).get("备注")).equals("")) ? "" : (String) result.get(i).get("备注");
//                    baseDataService.saveData(baseData);

                    //ls_department 表操作
                    int depId = 0;//部门Id
                    if (departmentService.checkName(department)) {
                        // 直接这样并取不到返回来的主键id    officeId = officeSchoolService.saveDataGetId(new LsOffice(officename));
                        LsDepartment lsDepartment = new LsDepartment(department);
                        if (StringUtils.isNotBlank(department)) {
                            departmentService.saveDataGetId(lsDepartment);
                            depId = lsDepartment.getdId();

                        }
                    } else {
                        depId = departmentService.getId(department);
                    }
                    System.out.println("get到的ddddid" + depId);

                    //ls_office表操作
                    int officeId = 0;
                    if (officeSchoolService.checkName(officename)) {
                        LsOffice lsOffice = new LsOffice(officename);
                        officeSchoolService.saveDataGetId(lsOffice);
                        officeId = lsOffice.getoId();
                        System.out.println("get到的id" + officeId);
                    } else {
                        officeId = officeSchoolService.getId(officename);
                    }
                    LsOffice lsOffice = officeSchoolService.getData(officeId);
                    System.out.println("get到的数据  office " + lsOffice);
                    if ("".equals(lsOffice.getdIds()) || lsOffice.getdIds() == null) {
                        lsOffice.setdIds(depId + "");
                    } else {
                        String dep = lsOffice.getdIds() + "," + depId;
                        String[] deps = dep.split(",");
                        Set<String> set = new HashSet<String>();
                        for (int k = 0; k < deps.length; k++) {
                            set.add(deps[k]);
                        }
                        String[] arrayResult = (String[]) set.toArray(new String[set.size()]);
                        String s = "";
                        for (int j = 0; j < arrayResult.length; j++) {
                            s += arrayResult[j] + ",";
                        }
                        lsOffice.setdIds(s.substring(0, s.length() - 1));
                    }
                    officeSchoolService.updateData(lsOffice);
                    //ls_address表操作
                    int networkId = 0;
                    if (networkService.checkNameByStr(StringUtils.substringBeforeLast(address, "."))) {
                        if(StringUtils.isNotBlank(address)) {
                            LsAddress lsAddress = new LsAddress(officeId + "", StringUtils.substringBeforeLast(address, ".") + ".0", StringUtils.substringBeforeLast(address, ".") + ".255", mask);
                            networkService.saveDataGetId(lsAddress);
                            networkId = lsAddress.getnId();
                        }
                    } else {
                        networkId = networkService.getId(StringUtils.substringBeforeLast(address, "."));
                    }
                    //ls_ip表操作

                    int ipId = 0;
                    if (ipService.checkName(ip)) {
                        LsIp lsIp = new LsIp(ip, networkId + "", "1");
                        ipService.saveDataGetId(lsIp);
                        ipId = lsIp.getiId();
                    } else {
                        ipId = ipService.getId(ip);
                    }
                    //ls_equipment表操作
                    lsEquipments.add(new LsEquipment(ipId + "", depId + "", equipment, location, username, password, remark));
//                    equipmentService.saveData(new LsEquipment(ipId + "", depId + "", equipment, location, username, password, remark));
                    System.out.println("------controller" + result.get(i).toString());
                    System.out.println("id集合" + ipId + "  officeid " + officeId + "   networkId  " + networkId + "   depId  " + depId);
                }
            }

            equipmentService.savaDataByBatch(lsEquipments);
            model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);
            System.out.println("path" + request.getContextPath() + "/upload/" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return new ModelAndView("redirect:/a/backCampusNew");
    }

    @RequestMapping(value = "/backCampusNew")
    public String a() {
        return "modules/act/actTaskEquipment";
    }

    @RequestMapping(value = "/exportTemplateNew")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String timer = format.format(date);
        HSSFWorkbook wb = exportService.exportTemplate();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=ipTemplate.xls");
        OutputStream ouputStream = null;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
