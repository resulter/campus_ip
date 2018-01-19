<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>校区ip查询工具</title>
	<%
		pageContext.setAttribute("APP_PATH", request.getContextPath());
	%>
	<!-- web路径：
    不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
    以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
            http://localhost:3306/crud
     -->
	<script type="text/javascript"
			src="${APP_PATH }/static/jquery/jquery-1.12.4.min.js"></script>
	<link
			href="${APP_PATH }/static/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css"
			rel="stylesheet">
	<script
			src="${APP_PATH }/static/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<%--<script
            src="${APP_PATH }/static/js/common.js"></script>--%>
	<style type="text/css">
		#page_info_area{width: 75%;float: left;padding-top: 5px;margin-left: 5px}
		#page_nav_area{width:700px;margin-left:10px;float: right}
		/*#page_info_area,#page_nav_area{float:left;}*/
	</style>
</head>
<body>
<!-- 员工修改的模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">修改信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<%--<div class="form-group">
                        <label class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="empName_update_static"></p>
                        </div>
                    </div>--%>
					<div class="form-group">
						<label class="col-sm-3 control-label">校区名称</label>
						<div class="col-sm-8">
							<input type="text" name="campusName" class="form-control" id="campus_name_update_input"
								   placeholder="请输入校区名称">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">网络地址段</label>
						<div class="col-sm-8">
							<input type="text" name="networkAddress" class="form-control" id="network_address_update_input"
								   placeholder="请输入网络地址段">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">掩码</label>
						<div class="col-sm-8">
							<input type="text" name="mask" class="form-control" id="mask_update_input"
								   placeholder="请输入掩码">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">IP地址</label>
						<div class="col-sm-8">
							<input type="text" name="ip" class="form-control" id="ip_update_input"
								   placeholder="请输入IP地址">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">使用设备</label>
						<div class="col-sm-8">
							<input type="text" name="usingEquipment" class="form-control" id="using_equipment_update_input"
								   placeholder="请输入使用设备">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">使用部门</label>
						<div class="col-sm-8">
							<input type="text" name="usingDepartment" class="form-control" id="using_department_update_input"
								   placeholder="请输入使用部门">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">存放位置</label>
						<div class="col-sm-8">
							<input type="text" name="storagePosition" class="form-control" id="storage_position_update_input"
								   placeholder="请输入存放位置">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">用户名</label>
						<div class="col-sm-8">
							<input type="text" name="username" class="form-control" id="username_update_input"
								   placeholder="请输入用户名">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">备注</label>
						<div class="col-sm-8">
							<textarea  type="text" name="remarks" class="form-control" id="remarks_update_input"
									   placeholder="请输入备注" rows="3"></textarea>
							<span class="help-block"></span>
						</div>
					</div>

				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
			</div>
		</div>
	</div>
</div>


<!-- 员工添加的模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">信息添加</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label">校区名称</label>
						<div class="col-sm-8">
							<input type="text" name="campusName" class="form-control" id="campus_name_add_input"
								   placeholder="请输入校区名称">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">网络地址段</label>
						<div class="col-sm-8">
							<input type="text" name="networkAddress" class="form-control" id="network_address_add_input"
								   placeholder="请输入网络地址段">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">掩码</label>
						<div class="col-sm-8">
							<input type="text" name="mask" class="form-control" id="mask_add_input"
								   placeholder="请输入掩码">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">IP地址</label>
						<div class="col-sm-8">
							<input type="text" name="ip" class="form-control" id="ip_add_input"
								   placeholder="请输入IP地址">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">使用设备</label>
						<div class="col-sm-8">
							<input type="text" name="usingEquipment" class="form-control" id="using_equipment_add_input"
								   placeholder="请输入使用设备">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">使用部门</label>
						<div class="col-sm-8">
							<input type="text" name="usingDepartment" class="form-control" id="using_department_add_input"
								   placeholder="请输入使用部门">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">存放位置</label>
						<div class="col-sm-8">
							<input type="text" name="storagePosition" class="form-control" id="storage_position_add_input"
								   placeholder="请输入存放位置">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">用户名</label>
						<div class="col-sm-8">
							<input type="text" name="username" class="form-control" id="username_add_input"
								   placeholder="请输入用户名">
							<span class="help-block"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">备注</label>
						<div class="col-sm-8">
							<textarea  type="text" name="remarks" class="form-control" id="remarks_add_input"
									   placeholder="请输入备注" rows="3"></textarea>
							<span class="help-block"></span>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
			</div>
		</div>
	</div>
</div>


<!-- 搭建显示页面 -->
<div style="margin-left: 30px">
	<!-- 按钮 -->
	<div class="row" >
		<div class="col-md-4 col-md-offset-8">
			<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
			<button class="btn btn-danger" id="emp_delete_all_btn">批量删除</button>
			<button class="btn btn-info" id="import_btn">批量导入</button>
			<button class="btn btn-info" id="export_btn">导出</button>
		</div>
	</div>
	<form class="form-inline" >
		<div class="form-group" style="display: inline-block;">
			<label for="search_campus">校区名称</label>
			<input type="text" class="form-control" id="search_campus" name="search_campus" placeholder="请输入要查找的校区">
		</div>
		<div class="form-group" style="display: inline-block;">
			<label for="search_address" style="margin-right: 2px">网络地址段</label>
			<input type="text" class="form-control" id="search_address" name="search_address" placeholder="请输入要查找的网络地址段">
		</div>
		<div class="form-group" style="display: inline-block;">
			<label for="search_ip" style="margin-right: 2px">IP地址</label>
			<input type="text" class="form-control" id="search_ip" name="search_ip" placeholder="请输入要查询的IP地址">
		</div>
		<button type="button" class="btn btn-default" id="search_btn">搜索</button>
	</form>
	<!-- 显示表格数据 -->
	<div class="row">
		<div class="col-md-12">
			<table class="table table-hover" id="emps_table" style="table-layout: fixed">
				<thead>
				<tr>
					<th width="30px">
						<input type="checkbox" id="check_all" style="width: 20px"/>
					</th>
					<%--<th>#</th>--%>
					<%--<th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>--%>
					<th style="width: 140px">校区名称</th><th style="width: 150px">网络地址段</th><th style="width: 150px">掩码</th><th style="width: 150px">ip地址</th><th style="width: 120px">使用设备</th><th style="width: 120px">使用部门</th><th style="width: 120px">存放位置</th><th style="width: 120px">用户名</th><th style="width: 200px">备注</th>
					<th style="width: 140px">操作</th><th style="width: 0px"></th>
				</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>

	<!-- 显示分页信息 -->
	<div class="row">
		<!--分页文字信息  -->
		<div class="col-md-6" id="page_info_area" ></div>
		<!-- 分页条信息 -->
		<div class="col-md-6" id="page_nav_area" >

		</div>
	</div>

</div>
<%--<input type="hidden" id="corp_id" name="corp_id" value="${}" />--%>
<script type="text/javascript">
    document.onkeydown=function() {

        if(event.keyCode==13) {

            to_page_condition(1);

//          alert("enter");
        }
    }
    var totalRecord, currentPage;
    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function () {
        //去首页
        to_page(1);
    });

    $("#search_btn").click(function(){
        to_page_condition(1);
    });
    function to_page(pn) {
        $.ajax({
            url: "${APP_PATH}/a/datasAll",
            data: "pn=" + pn,
            type: "GET",
            success: function (result) {
                //console.log(result);
                //1、解析并显示员工数据
                build_emps_table(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、解析显示分页条数据
                build_page_nav(result);
            }
        });
    }
    //条件查询
    function to_page_condition(pn) {
//        alert(pn);
        $.ajax({
            url: "${APP_PATH}/a/dataCondition",
            data: {"pn": pn,"search_campus":$("#search_campus").val(),"search_address":$("#search_address").val(),"search_ip":$("#search_ip").val()},
            type: "GET",
            success: function (result) {
//                alert(result.extend.searchMap.search_campus);
                $("#search_campus").val(result.extend.searchMap.search_campus);
                $("#search_address").val(result.extend.searchMap.search_address);
                $("#search_ip").val(result.extend.searchMap.search_ip);
                //console.log(result);
                //1、解析并显示员工数据
                build_emps_table(result);
                //2、解析并显示分页信息
                build_page_info(result);
                //3、解析显示分页条数据
                build_page_nav(result);

            }
        });
    }


    function build_emps_table(result) {
        //清空table表格
        $("#emps_table tbody").empty();
        var emps = result.extend.pageInfo.list;
        $.each(emps, function (index, item) {
            var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>").addClass();
            var id= $("<td style='width: 0px'></td>").append(item.id);
            var campus_name= $("<td></td>").append(item.campusName).attr("title",item.campusName);
            var network_address= $("<td></td>").append(item.networkAddress).attr("title",item.networkAddress);
            var mask= $("<td></td>").append(item.mask).attr("title",item.mask);
            var ip= $("<td></td>").append(item.ip).attr("title",item.ip);
            var using_equipment= $("<td></td>").append(item.usingEquipment).attr("title",item.usingEquipment);
            var using_department= $("<td></td>").append(item.usingDepartment).attr("title",item.usingDepartment);
            var storage_position= $("<td></td>").append(item.storagePosition).attr("title",item.storagePosition);
            var username= $("<td></td>").append(item.username).attr("title",item.username);
//            var password= $("<td></td>").append(item.password);
            var remarks= $("<td style='width: 200px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;'></td>").append(item.remarks).attr("title",item.remarks);
            /**
             <button class="">
             <span class="" aria-hidden="true"></span>
             编辑
             </button>
             */
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前员工id
            editBtn.attr("edit-id", item.id);
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的员工id
            delBtn.attr("del-id", item.id);
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            //var delBtn =
            //append方法执行完成以后还是返回原来的元素
            $("<tr></tr>").append(checkBoxTd)
//                .append(id)
                .append(campus_name)
                .append(network_address)
                .append(mask)
                .append(ip)
                .append(using_equipment)
                .append(using_department)
                .append(storage_position)
                .append(username)
                //                .append(password)
                .append(remarks)
                .append(btnTd)
                .appendTo("#emps_table tbody").append(id);
        });
    }

    //解析显示分页信息
    function build_page_info(result) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前" + result.extend.pageInfo.pageNum + "页,总" +
            result.extend.pageInfo.pages + "页,总" +
            result.extend.pageInfo.total + "条记录");
        totalRecord = result.extend.pageInfo.total;
        currentPage = result.extend.pageInfo.pageNum;
    }

    //解析显示分页条，点击分页要能去下一页....
    function build_page_nav(result) {
        //page_nav_area
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");

        //构建元素
        var firstPageLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 20px'></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePageLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 20px'></li>").append($("<a></a>").append("&laquo;"));
        if (result.extend.pageInfo.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            //为元素添加点击翻页的事件
            firstPageLi.click(function () {
                to_page_condition(1);
            });
            prePageLi.click(function () {
                to_page_condition(result.extend.pageInfo.pageNum - 1);
            });
        }


        var nextPageLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 20px'></li>").append($("<a></a>").append("&raquo;"));
        var lastPageLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 20px'></li>").append($("<a></a>").append("末页").attr("href", "#"));
        if (result.extend.pageInfo.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            nextPageLi.click(function () {
                to_page_condition(result.extend.pageInfo.pageNum + 1);
            });
            lastPageLi.click(function () {
                to_page_condition(result.extend.pageInfo.pages);
            });
        }


        //添加首页和前一页 的提示
        ul.append(firstPageLi).append(prePageLi);
        //1,2，3遍历给ul中添加页码提示
        $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {

            var numLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 20px'></li>").append($("<a></a>").append(item));
            if (result.extend.pageInfo.pageNum == item) {
                numLi.addClass("active");
            }
            numLi.click(function () {
                to_page_condition(item);
            });
            ul.append(numLi);
        });
        //添加下一页和末页 的提示
        ul.append(nextPageLi).append(lastPageLi);

        //把ul加入到nav
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }

    //清空表单样式及内容
    function reset_form(ele) {
        $(ele)[0].reset();
        //清空表单样式
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }

    //点击新增按钮弹出模态框。
    $("#emp_add_modal_btn").click(function () {
        //清除表单数据（表单完整重置（表单的数据，表单的样式））
//        alert(APP_PATH);
        reset_form("#empAddModal form");
        //s$("")[0].reset();
        //发送ajax请求，查出部门信息，显示在下拉列表中
        getDepts("#empAddModal select");
        //弹出模态框
        $("#empAddModal").modal({
            backdrop: "static"
        });
    });
    //批量上传，跳转到import.jsp
    $("#import_btn").click(function () {

        window.location.href = "/a/importExcel" ;
//        window.location.href='data/gotoImport';
    });
    $("#export_btn").click(function () {

        window.location.href = "/a/exportExcel" ;
//        window.location.href='data/gotoImport';
    });

    //查出所有的部门信息并显示在下拉列表中
    function getDepts(ele) {
        //清空之前下拉列表的值
        $(ele).empty();
        $.ajax({
            url: "${APP_PATH}/depts",
            type: "GET",
            success: function (result) {
                //{"code":100,"msg":"处理成功！",
                //"extend":{"depts":[{"deptId":1,"deptName":"开发部"},{"deptId":2,"deptName":"测试部"}]}}
                //console.log(result);
                //显示部门信息在下拉列表中
                //$("#empAddModal select").append("")
                $.each(result.extend.depts, function () {
                    var optionEle = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                    optionEle.appendTo(ele);
                });
            }
        });

    }

    //校验表单数据
    function validate_add_form() {
        var campus_name_add_input = $("#campus_name_add_input").val();
        if(campus_name_add_input == null||campus_name_add_input == "" ){
            show_validate_msg("#campus_name_add_input", "error", "校区名称不能为空");
            return false;
		}else {
            show_validate_msg("#campus_name_add_input", "success", "");
        }
        var network_address_add_input = $("#network_address_add_input").val();
        if(network_address_add_input == null||network_address_add_input == "" ){
            show_validate_msg("#network_address_add_input", "error", "网络地址段不能为空");
            return false;
        }else if(!ValidateIPaddress(network_address_add_input)){
            show_validate_msg("#network_address_add_input", "error", "网络地址段格式不符合");
            return false;
		}else {
            show_validate_msg("#network_address_add_input", "success", "");
        }
        var mask_add_input = $("#mask_add_input").val();
        if(mask_add_input == null||mask_add_input == "" ){
            show_validate_msg("#mask_add_input", "error", "掩码不能为空");
            return false;
        }else if(!ValidateIPaddress(mask_add_input)){
            show_validate_msg("#mask_add_input", "error", "掩码格式不符合");
            return false;
        }else {
            show_validate_msg("#mask_add_input", "success", "");
        }
        var ip_add_input = $("#ip_add_input").val();
        if(ip_add_input == null||ip_add_input == "" ){
            show_validate_msg("#ip_add_input", "error", "IP地址不能为空");
            return false;
        }else if(!ValidateIPaddress(ip_add_input)){
            show_validate_msg("#ip_add_input", "error", "IP格式不符合");
            return false;
        }else {
            show_validate_msg("#ip_add_input", "success", "");
        }
        var using_equipment_add_input = $("#using_equipment_add_input").val();
        if(using_equipment_add_input == null||using_equipment_add_input == "" ){
            show_validate_msg("#using_equipment_add_input", "error", "使用设备不能为空");
            return false;
        }else {
            show_validate_msg("#using_equipment_add_input", "success", "");
        }
        var using_department_add_input = $("#using_department_add_input").val();
        if(using_department_add_input == null||using_department_add_input == "" ){
            show_validate_msg("#using_department_add_input", "error", "使用部门不能为空");
            return false;
        }else {
            show_validate_msg("#using_department_add_input", "success", "");
        }

        return true;

        //1、拿到要校验的数据，使用正则表达式
        var empName = $("#empName_add_input").val();
        var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
        if (!regName.test(empName)) {
            //alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
            show_validate_msg("#empName_add_input", "error", "用户名可以是2-5位中文或者6-16位英文和数字的组合");
            return false;
        } else {
            show_validate_msg("#empName_add_input", "success", "");
        }
        ;

        //2、校验邮箱信息
        var email = $("#email_add_input").val();
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if (!regEmail.test(email)) {
            //alert("邮箱格式不正确");
            //应该清空这个元素之前的样式
            show_validate_msg("#email_add_input", "error", "邮箱格式不正确");
            /* $("#email_add_input").parent().addClass("has-error");
            $("#email_add_input").next("span").text("邮箱格式不正确"); */
            return false;
        } else {
            show_validate_msg("#email_add_input", "success", "");
        }

    }
    function ValidateIPaddress( ip ) {
        if( /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test( ip ) ) {
            return true;
        }
//        alert("You have entered an invalid IP address!");
        return false;
    }
    //显示校验结果的提示信息
    function show_validate_msg(ele, status, msg) {
        //清除当前元素的校验状态
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if ("success" == status) {
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        } else if ("error" == status) {
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }

    //校验用户名是否可用
    $("#empName_add_input").change(function () {
        //发送ajax请求校验用户名是否可用
        var empName = this.value;
        $.ajax({
            url: "${APP_PATH}/checkuser",
            data: "empName=" + empName,
            type: "POST",
            success: function (result) {
                if (result.code == 100) {
                    show_validate_msg("#empName_add_input", "success", "用户名可用");
                    $("#emp_save_btn").attr("ajax-va", "success");
                } else {
                    show_validate_msg("#empName_add_input", "error", result.extend.va_msg);
                    $("#emp_save_btn").attr("ajax-va", "error");
                }
            }
        });
    });

    //点击保存，保存员工。
    $("#emp_save_btn").click(function () {
        //1、模态框中填写的表单数据提交给服务器进行保存
        if (!validate_add_form()) {
//            alert("cuo");
            return false;
        }
        //1、先对要提交给服务器的数据进行校验
        /* if (!validate_add_form()) {
             return false;
         }
         ;
         //1、判断之前的ajax用户名校验是否成功。如果成功。
         if ($(this).attr("ajax-va") == "error") {
             return false;
         }*/
//alert( $("#empAddModal form").serialize())
        //2、发送ajax请求保存员工
        $.ajax({
            /*  beforeSend : function (XMLHttpRequest) {
                  XMLHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
              },*/
            url: "${APP_PATH}/a/dataq",
            type: "POST",
            data: $("#empAddModal form").serialize(),
            success: function (result) {
                //alert(result.msg);
                if (result.code == 100) {
                    //员工保存成功；
                    //1、关闭模态框
                    $("#empAddModal").modal('hide');

                    //2、来到最后一页，显示刚才保存的数据
                    //发送ajax请求显示最后一页数据即可
                    to_page_condition(totalRecord);
                } else {
                    //显示失败信息
                    //console.log(result);
                    //有哪个字段的错误信息就显示哪个字段的；
                    if (undefined != result.extend.errorFields.email) {
                        //显示邮箱错误信息
                        show_validate_msg("#email_add_input", "error", result.extend.errorFields.email);
                    }
                    if (undefined != result.extend.errorFields.empName) {
                        //显示员工名字的错误信息
                        show_validate_msg("#empName_add_input", "error", result.extend.errorFields.empName);
                    }
                }
            }
        });
    });

    //1、我们是按钮创建之前就绑定了click，所以绑定不上。
    //1）、可以在创建按钮的时候绑定。    2）、绑定点击.live()
    //jquery新版没有live，使用on进行替代
    $(document).on("click", ".edit_btn", function () {
//        alert("edit");


        //1、查出部门信息，并显示部门列表
//        getDepts("#empUpdateModal select");
        //2、查出员工信息，显示员工信息
        getEmp($(this).attr("edit-id"));

        //3、把员工的id传递给模态框的更新按钮
        $("#emp_update_btn").attr("edit-id", $(this).attr("edit-id"));
        $("#empUpdateModal").modal({
            backdrop: "static"
        });
    });

    function getEmp(id) {
        $.ajax({
            url: "${APP_PATH}/a/data/" + id,
            type: "GET",
            success: function (result) {

                console.log("---->"+result);
                var myData = result.extend.das;


//                alert(myData.campusName);
                $("#campus_name_update_input").val(myData.campusName);
                $("#network_address_update_input").val(myData.networkAddress);
                $("#mask_update_input").val(myData.mask);
                $("#ip_update_input").val(myData.ip);
                $("#using_equipment_update_input").val(myData.usingEquipment);
                $("#using_department_update_input").val(myData.usingDepartment);
                $("#storage_position_update_input").val(myData.storagePosition);
                $("#username_update_input").val(myData.username);
                $("#remarks_update_input").val(myData.remarks);
                /*    $("#empUpdateModal input[name=gender]").val([myData.gender]);
                    $("#empUpdateModal select").val([myData.dId]);*/
            }

        });
    }

    //点击更新，更新员工信息
    $("#emp_update_btn").click(function () {
        var campus_name_update_input = $("#campus_name_update_input").val();
        if(campus_name_update_input == null||campus_name_update_input == "" ){
            show_validate_msg("#campus_name_update_input", "error", "校区名称不能为空");
            return false;
        }else {
            show_validate_msg("#campus_name_update_input", "success", "");
        }
        var network_address_update_input = $("#network_address_update_input").val();
        if(network_address_update_input == null||network_address_update_input == "" ){
            show_validate_msg("#network_address_update_input", "error", "网络地址段不能为空");
            return false;
        }else if(!ValidateIPaddress(network_address_update_input)){
            show_validate_msg("#network_address_update_input", "error", "网络地址段格式不符合");
            return false;
        }else {
            show_validate_msg("#network_address_update_input", "success", "");
        }
        var mask_update_input = $("#mask_update_input").val();
        if(mask_update_input == null||mask_update_input == "" ){
            show_validate_msg("#mask_update_input", "error", "掩码不能为空");
            return false;
        }else if(!ValidateIPaddress(mask_update_input)){
            show_validate_msg("#mask_update_input", "error", "掩码格式不符合");
            return false;
        }else {
            show_validate_msg("#mask_update_input", "success", "");
        }
        var ip_update_input = $("#ip_update_input").val();
        if(ip_update_input == null||ip_update_input == "" ){
            show_validate_msg("#ip_update_input", "error", "IP地址不能为空");
            return false;
        }else if(!ValidateIPaddress(ip_update_input)){
            show_validate_msg("#ip_update_input", "error", "IP格式不符合");
            return false;
        }else {
            show_validate_msg("#ip_update_input", "success", "");
        }
        var using_equipment_update_input = $("#using_equipment_update_input").val();
        if(using_equipment_update_input == null||using_equipment_update_input == "" ){
            show_validate_msg("#using_equipment_update_input", "error", "使用设备不能为空");
            return false;
        }else {
            show_validate_msg("#using_equipment_update_input", "success", "");
        }
        var using_department_update_input = $("#using_department_update_input").val();
        if(using_department_update_input == null||using_department_update_input == "" ){
            show_validate_msg("#using_department_update_input", "error", "使用部门不能为空");
            return false;
        }else {
            show_validate_msg("#using_department_update_input", "success", "");
        }
        //验证邮箱是否合法
        //1、校验邮箱信息
        var email = $("#email_update_input").val();
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        var regIp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
        var ip =  $("#ip_update_input").val();
        var address =  $("#network_address_update_input").val();
        /*if (!regIp.test(ip)) {
            show_validate_msg("#ip_update_input", "error", "IP格式不正确");
            return false;
        } else {
            show_validate_msg("#email_update_input", "success", "");
        }

        if (!regIp.test(address)) {
            show_validate_msg("#network_address_update_input", "error", "网络地址段格式不正确");
            return false;
        } else {
            show_validate_msg("#network_address_update_input", "success", "");
        }*/
//alert($("#empUpdateModal form").serialize());
        //2、发送ajax请求保存更新的员工数据
        $.ajax({
            url: "${APP_PATH}/a/dataq/" + $(this).attr("edit-id"),
            type: "PUT",
            data: $("#empUpdateModal form").serialize(),
            success: function (result) {

//                alert(result.msg);
                //1、关闭对话框
                $("#empUpdateModal").modal("hide");
                //2、回到本页面
                to_page_condition(currentPage);
            },
            error: function(result) {
                alert("put请求失败");
            }
        });
    });

    //单个删除
    $(document).on("click", ".delete_btn", function () {
        //1、弹出是否确认删除对话框
        var ipName = $(this).parents("tr").find("td:eq(4)").text();
        var id = $(this).attr("del-id");
        //alert($(this).parents("tr").find("td:eq(1)").text());
        if (confirm("确认删除IP为【" + ipName + "】的数据吗？")) {
            //确认，发送ajax请求删除即可
            $.ajax({
                url: "${APP_PATH}/a/data/" + id,
                type: "DELETE",
                success: function (result) {
                    alert(result.msg);
                    //回到本页
                    to_page_condition(currentPage);
                }
            });
        }
    });

    //完成全选/全不选功能
    $("#check_all").click(function () {
        //attr获取checked是undefined;
        //我们这些dom原生的属性；attr获取自定义属性的值；
        //prop修改和读取dom原生属性的值
        $(".check_item").prop("checked", $(this).prop("checked"));
    });

    //check_item
    $(document).on("click", ".check_item", function () {
        //判断当前选择中的元素是否5个
        var flag = $(".check_item:checked").length == $(".check_item").length;
        $("#check_all").prop("checked", flag);
    });

    //点击全部删除，就批量删除
    $("#emp_delete_all_btn").click(function () {
        //
        var ipNames = "";
        var del_idstr = "";
        $.each($(".check_item:checked"), function () {
            //this
            ipNames += $(this).parents("tr").find("td:eq(4)").text() + ",";
            //组装员工id字符串
            del_idstr += $(this).parents("tr").find("td:eq(11)").text() + "-";
        });
        //ipNames,
        ipNames = ipNames.substring(0, ipNames.length - 1);
        //去除删除的id多余的-
        del_idstr = del_idstr.substring(0, del_idstr.length - 1);
        if (confirm("确认删除IP为【" + ipNames + "】的数据吗？")) {
            //发送ajax请求删除
            $.ajax({
                url: "${APP_PATH}/a/data/" + del_idstr,
                type: "DELETE",
                success: function (result) {
                    alert(result.msg);
                    //回到当前页面
                    to_page_condition(currentPage);
                }
            });
        }
    });
</script>
</body>
</html>