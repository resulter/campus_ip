package com.thinkgem.jeesite.modules.act.service;


import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.entity.*;
import com.thinkgem.jeesite.modules.act.vo.LsEquipmentVo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExportService {

    @Autowired
    BaseDataService baseDataService;
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

    String[] excelHeader = {"校区名称", "网络地址段", "掩码", "ip地址", "使用设备", "使用部门", "存放位置", "用户名", "密码", "备注"};

    // 单元格列宽
    int[] excelHeaderWidth = {140, 140, 140, 140, 140, 140, 120, 120, 140, 140};

    public HSSFWorkbook export() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("ip数据统计表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        // 设置列宽度（像素）
        for (int i = 0; i < excelHeaderWidth.length; i++) {
            sheet.setColumnWidth(i, 32 * excelHeaderWidth[i]);
        }

//        List<BaseData> list = baseDataService.getAll();

        List<LsEquipment> data = equipmentService.getAll();
        for (int i = 0; i < data.size(); i++) {
            LsAddress lsAddress = null;
            LsOffice lsOffice = null;
            LsIp lsIp = null;
            LsDepartment lsDepartment = null;
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
            if (!"".equals(dId)) {
                lsDepartment = departmentService.getData(Integer.parseInt(dId));
            }
            String dName = lsDepartment.getdName();

            if (!"".equals(iId)) {
                lsIp = ipService.getData(Integer.parseInt(iId));
            }
            String ip = lsIp.getIp();
            String nId = lsIp.getnId();
            String tag = lsIp.getTag();

            if (!"".equals(nId)) {
                lsAddress = networkService.getData(Integer.parseInt(nId));
            }
            String mask = lsAddress.getMask();

            String maxAddress = lsAddress.getnMaxAddress();

            String minAddress = lsAddress.getnMinAddress();
            String address = StringUtils.substringBeforeLast(minAddress, ".") + "." + "0";
            String oId = lsAddress.getoId();

            if (!"".equals(oId)) {
                lsOffice = officeSchoolService.getData(Integer.parseInt(oId));
            }
            String dIds = lsOffice.getdIds();
            String oName = lsOffice.getoName();

            row = sheet.createRow(i + 1);
//            BaseData baseData = list.get(i);
            row.createCell(0).setCellValue(oName);
            row.createCell(1).setCellValue(minAddress + "-" + maxAddress);
            row.createCell(2).setCellValue(mask);
            row.createCell(3).setCellValue(ip);
            row.createCell(4).setCellValue(equipmentName);
            row.createCell(5).setCellValue(dName);
            row.createCell(6).setCellValue(location);
            row.createCell(7).setCellValue(username);
            row.createCell(8).setCellValue(password);
            row.createCell(9).setCellValue(remark);
        }
        return wb;
    }

    public HSSFWorkbook exportTemplate() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("ip数据统计表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        // 设置列宽度（像素）
        for (int i = 0; i < excelHeaderWidth.length; i++) {
            sheet.setColumnWidth(i, 32 * excelHeaderWidth[i]);
        }

        List<BaseData> list = new ArrayList<BaseData>();
        list.add(new BaseData("总部校区", "10.152.25.0", "255.255.255.0", "10.152.25.1", "路由器", "网络运营部", "水清机房", "admin", "XXXXX", "上一行标题不变，删除本示例行开始编辑"));
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            BaseData baseData = list.get(i);
            row.createCell(0).setCellValue(baseData.getCampusName());
            row.createCell(1).setCellValue(baseData.getNetworkAddress());
            row.createCell(2).setCellValue(baseData.getMask());
            row.createCell(3).setCellValue(baseData.getIp());
            row.createCell(4).setCellValue(baseData.getUsingEquipment());
            row.createCell(5).setCellValue(baseData.getUsingDepartment());
            row.createCell(6).setCellValue(baseData.getStoragePosition());
            row.createCell(7).setCellValue(baseData.getUsername());
            row.createCell(8).setCellValue(baseData.getPassword());
            row.createCell(9).setCellValue(baseData.getRemarks());
        }
        return wb;
    }
}

