package com.thinkgem.jeesite.modules.act.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thinkgem.jeesite.modules.act.dao.Msg;
import com.thinkgem.jeesite.modules.act.entity.BaseData;
import com.thinkgem.jeesite.modules.act.service.BaseDataService;
import com.thinkgem.jeesite.modules.act.service.ExportService;
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
 * 
 */
@Controller
@RequestMapping(value = "/a/")
public class BaseDataController {

	@Autowired
	BaseDataService baseDataService;
//	EmployeeService employeeService;

	@Autowired
	ExportService exportService;
	
	/**
	 * 单个批量二合一
	 * 批量删除：1-2-3
	 * 单个删除：1
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/data/{ids}",method= RequestMethod.DELETE)
	public Msg deleteData(@PathVariable("ids")String ids){
		//批量删除
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList();
			String[] str_ids = ids.split("-");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			baseDataService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			baseDataService.deleteData(id);
		}
		return Msg.success();
	}
	
	
	
	/**
	 * 如果直接发送ajax=PUT形式的请求
	 * 封装的数据
	 * Employee 
	 * [empId=1014, empName=null, gender=null, email=null, dId=null]
	 * 
	 * 问题：
	 * 请求体中有数据；
	 * 但是Employee对象封装不上；
	 * update tbl_emp  where emp_id = 1014;
	 * 
	 * 原因：
	 * Tomcat：
	 * 		1、将请求体中的数据，封装一个map。
	 * 		2、request.getParameter("empName")就会从这个map中取值。
	 * 		3、SpringMVC封装POJO对象的时候。
	 * 				会把POJO中每个属性的值，request.getParamter("email");
	 * AJAX发送PUT请求引发的血案：
	 * 		PUT请求，请求体中的数据，request.getParameter("empName")拿不到
	 * 		Tomcat一看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map
	 * org.apache.catalina.connector.Request--parseParameters() (3111);
	 * 
	 * protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
                success = true;
                return;
            }
	 * 
	 * 
	 * 解决方案；
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 1、配置上HttpPutFormContentFilter；
	 * 2、他的作用；将请求体中的数据解析包装成一个map。
	 * 3、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
	 * 员工更新方法
	 * @param baseData
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/data/{id}",method= RequestMethod.PUT)
	public Msg saveData(BaseData baseData, HttpServletRequest request){
//		System.out.println("a数据" + request.getParameter("a"));
		System.out.println("请求体中的值："+request.getParameter("ip"));
		System.out.println("将要更新的数据："+baseData.getId());
		System.out.println("将要更新的全部数据："+baseData);
		System.out.println("将要更新的数据IP："+baseData.getIp());
		baseDataService.updateData(baseData);
		return Msg.success();
	}
	@RequestMapping(value="/dataq/{id}",method= RequestMethod.PUT)
	@ResponseBody
	public Msg save(BaseData baseData, HttpServletRequest request){
		System.out.println("请求体中的值："+request.getParameter("ip"));
		System.out.println("BaseData中的值："+baseData.getIp());
		System.out.println("将要更新的数据："+baseData.getId());
		System.out.println("将要更新的全部数据："+baseData);
		System.out.println("将要更新的数据IP："+baseData.getIp());
		baseDataService.updateData(baseData);
		return Msg.success();
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/data/{id}",method= RequestMethod.GET)
	@ResponseBody
	public Msg getData(@PathVariable("id")Integer id){
//		System.out.println("------------------------>" + baseDataService.getData(id).getCampusName());
		BaseData baseData = baseDataService.getData(id);
		return Msg.success().add("das", baseData);
	}
	
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkusers")
	public Msg checkuser(@RequestParam("empName")String empName){
		//先判断用户名是否是合法的表达式;
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)){
			return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
		}
		
		//数据库用户名重复校验
		boolean b = baseDataService.checkUser(empName);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "用户名不可用");
		}
	}
	
	
	/**
	 * 保存
	 * 1、支持JSR303校验
	 * 2、导入Hibernate-Validator
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value="/data",method= RequestMethod.POST)
	@ResponseBody
	public Msg saveData(@Valid BaseData baseData,BindingResult result){
		System.out.println("数据" + baseData);
		if(result.hasErrors()){
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			baseDataService.saveData(baseData);
			return Msg.success();
		}
		
	}
	@RequestMapping(value="/dataq",method= RequestMethod.POST)
	@ResponseBody
	public Msg dd(@RequestParam(value = "dd", defaultValue = "ddd") String dd, BaseData baseData, BindingResult result){
		System.out.println("数据" + baseData);
		if(result.hasErrors()){
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			baseDataService.saveData(baseData);
			return Msg.success();
		}
	}

	/**
	 * 导入jackson包。
	 * @param pn
	 * @return
	 */
	@RequestMapping("/datas")
//	@ResponseBody
	public String getDatas(	@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

		return "modules/act/actTaskDataDisplay";

	}
	@RequestMapping("/datasAll")
	@ResponseBody
	public Msg getDatasWithJson(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 10);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<BaseData> data = baseDataService.getAll();
		System.out.println("----------------------> 库长度size" + data.size());
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(data, 5);
		System.out.println("----------->>"+page.toString());
		return Msg.success().add("pageInfo", page);
	}


/**
 * 条件查找
 */

	@RequestMapping("/dataCondition")
	@ResponseBody
	public Msg getDatasWithCondition(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @RequestParam(value = "search_campus", defaultValue = "") String search_campus, @RequestParam(value = "search_address", defaultValue = "")String search_address, @RequestParam(value = "search_ip", defaultValue = "")String search_ip, HttpServletRequest request, Model model) {
//		System.out.println("数据     campus " + request.getParameter("search_campus") + "address  "+ request.getParameter("search_address") + "ip  " + request.getParameter("search_ip"));
		Map<String,String> searchMap = getSearchMap(request);
//		String search_campus = request.getParameter("search_campus");
//		String search_address = request.getParameter("search_address");
//		String search_ip = request.getParameter("search_ip");
		// TODO: 2017/12/20 搜索 
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 10);
		// startPage后面紧跟的这个查询就是一个分页查询
		System.out.println("查询条件search_campus："+search_campus+"     search_address："+search_address+"     search_ip："+search_ip);
		List<BaseData> data = baseDataService.getConditionAll(search_campus,search_address,search_ip);
		for (int i = 0;i<data.size();i++){
			System.out.println("查询结果--" + data.get(i));
		}
		System.out.println("查询结果----------------------> 库长度size" + data.size());
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(data, 5);
		model.addAttribute("searchMap",searchMap);
		return Msg.success().add("pageInfo", page).add("searchMap",searchMap);
	}
	/**
	 * 查询员工数据（分页查询）
	 * 
	 * @return
	 */
	// @RequestMapping("/emps")
	public String getEmps(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// 这不是一个分页查询；
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<BaseData> data = baseDataService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(data, 5);
		model.addAttribute("pageInfo", page);

		return "list";
	}
	@RequestMapping(value = "/gotoImport")
	public String gotoImport() {
		return "import";
	}

	/**
	 * 返回查询条件集合
	 *
	 * @param request
	 * @return
	 */
	private Map<String, String> getSearchMap(HttpServletRequest request) {
		Map<String, String> searchMap = new HashMap<String, String>();
		//校区名称
		String search_campus = request.getParameter("search_campus");
		if (search_campus != null) {search_campus = search_campus.trim();}
		search_campus = search_campus != null ? search_campus : "";
		searchMap.put("search_campus", search_campus);
		//校区名称
		String search_address = request.getParameter("search_address");
		if (search_address != null) {search_address = search_address.trim();}
		search_address = search_address != null ? search_address : "";
		searchMap.put("search_address", search_address);
		//校区名称
		String search_ip = request.getParameter("search_ip");
		if (search_ip != null) {search_ip = search_ip.trim();}
		search_ip = search_ip != null ? search_ip : "";
		searchMap.put("search_ip", search_ip);
		
		return searchMap;
	}

	@RequestMapping(value = "/exportExcel")
	public String exportExcel(HttpServletRequest request, HttpServletResponse response) {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String timer=format.format(date);
		HSSFWorkbook wb = exportService.export();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" +timer + "ipInfo.xls");
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "modules/act/actTaskDataDisplay";
	}
	@RequestMapping(value = "/importExcel")
	public String importExcel() {
		return "modules/act/actTaskDataImport";
	}

	@RequestMapping(value = "/uploadFileAndImport")
	@ResponseBody
	public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {

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
			m.put("网络地址段（多）", "网络地址段");
			m.put("掩码", "掩码");
			m.put("ip地址（多）", "ip地址");
			m.put("使用设备", "使用设备");
			m.put("使用部门", "使用部门");
			m.put("存放位置", "存放位置");
			m.put("用户名", "用户名");
			m.put("密码（不显示）", "密码");
			m.put("备注", "备注");
			List<Map<String, Object>> result = ImportExcelUtil.parseExcel(fis, targetFile.getName(), m);
           /* for (int i = 0; i < result.size(); i++) {
                System.out.println("----result"+result.get(i).toString());
            }*/
			for (int i = 0; i < result.size(); i++) {
				if (!(result.get(i).get("校区名称").equals("") && result.get(i).get("网络地址段").equals("") && result.get(i).get("掩码")
						.equals("") && result.get(i).get("ip地址").equals("") && result.get(i).get("使用设备").equals("") && result.get(i).get("使用部门")
						.equals("") && result.get(i).get("存放位置").equals("") && result.get(i).get("用户名").equals("") && result.get(i).get("密码")
						.equals("") && result.get(i).get("备注").equals(""))){
					BaseData baseData = new BaseData((String)result.get(i).get("校区名称"),(String)result.get(i).get("网络地址段"),(String)result.get(i).get("掩码")
							,(String)result.get(i).get("ip地址"),(String)result.get(i).get("使用设备"),(String)result.get(i).get("使用部门")
							,(String)result.get(i).get("存放位置"),(String)result.get(i).get("用户名"),(String)result.get(i).get("密码")
							,(String)result.get(i).get("备注"));
					baseDataService.saveData(baseData);
					System.out.println("------controller"+result.get(i).toString());

				}
			}
			model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);
			System.out.println("path" + request.getContextPath() + "/upload/" + fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/a/backCampus");
	}
	@RequestMapping(value = "/backCampus")
	public String a() {
		return "modules/act/actTaskDataDisplay";
	}

	@RequestMapping(value = "/exportTemplate")
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String timer=format.format(date);
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
