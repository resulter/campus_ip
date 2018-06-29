<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
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
        #page_info_area {
            width: 75%;
            float: left;
            padding-top: 5px;
            margin-left: 5px
        }

        #page_nav_area {
            width: 700px;
            margin-left: 10px;
            float: right
        }

        .first {
            width: 70px;
            padding: 6px;
        }

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

                    <div class="form-group">
                        <label class="col-sm-3 control-label">校区名称</label>
                        <div class="col-sm-8">
                            <!-- 校区提交校区id即可 -->
                            <select class="form-control" name="oId" onchange="officeChangeUpdate()"
                                    id="office_name_update_select">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">网络地址段</label>
                        <div class="col-sm-8">
                            <!-- 地址段提交网络地址段id即可 -->
                            <select class="form-control" name="nId" onchange="addressChangeUpdate()"
                                    id="network_update_select">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">掩码</label>
                        <div class="col-sm-8">
                            <input type="text" name="mask" class="form-control" id="equipment_mask_update_input"
                                   placeholder="请输入掩码">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">ip地址</label>
                        <div class="col-sm-8">
                            <!-- ip提交ip id即可 -->
                            <select class="form-control" name="iId" id="ip_update_select">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">使用设备</label>
                        <div class="col-sm-8">
                            <input type="text" name="equipmentName" class="form-control"
                                   id="equipment_using_update_input"
                                   placeholder="请输入设备名称">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">使用部门</label>
                        <div class="col-sm-8">
                            <!-- 部门提交部门id即可 -->
                            <select class="form-control" name="dId" id="department_update_select">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">存放位置</label>
                        <div class="col-sm-8">
                            <input type="text" name="location" class="form-control" id="equipment_location_update_input"
                                   placeholder="请输入存放位置">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名</label>
                        <div class="col-sm-8">
                            <input type="text" name="username" class="form-control" id="equipment_username_update_input"
                                   placeholder="请输入用户名">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密码</label>
                        <div class="col-sm-8">
                            <input type="text" name="password" class="form-control" id="equipment_password_update_input"
                                   placeholder="请输入密码">
                            <span class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-8">
                            <input type="text" name="remark" class="form-control" id="equipment_remark_update_input"
                                   placeholder="请输入备注">
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

<!-- 搭建显示页面 -->
<div style="margin-left: 30px">
    <form>

        <div class="form-group" id="searchInfo">
            <div class="col-sm-3">
                <!-- 部门提交部门id即可 -->
                <select class="form-control" name="search_address" id="address_search_select"
                        onchange=" changeSelectOffice()">
                </select>
            </div>
            <div class="col-sm-2">
                <input type="text" name="search_ip" class="form-control" id="ip_search_input"
                       placeholder="ip">
                <span class="help-block"></span>
            </div>
            <div class="col-sm-2">
                <input type="text" name="search_equipment" class="form-control" id="equipment_search_input"
                       placeholder="设备名称">
                <span class="help-block"></span>
            </div>
            <button class="btn btn-default" type="button" id="search_office_details">搜索</button>
        </div>

    </form>
    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="emps_table" style="table-layout: fixed">
                <thead>
                <tr>
                    <th style="width: 1px;display: none"></th>
                    <th style="width: 140px">校区名称</th>
                    <th style="width: 150px">网络地址段</th>
                    <th style="width: 150px">掩码</th>
                    <th style="width: 150px">ip地址</th>
                    <th style="width: 120px">使用设备</th>
                    <th style="width: 120px">使用部门</th>
                    <th style="width: 120px">存放位置</th>
                    <th style="width: 120px">用户名</th>
                    <th style="width: 120px">密码</th>
                    <th style="width: 80px">是否可用</th>

                    <th style="width: 200px">备注</th>
                    <th style="width: 200px">
                        <div class="checkbox">
                            <input type="checkbox" id="checkBoxUsed">
                            <div class="btn-danger first" id="used_first">使用优先</div>
                        </div>
                    </th>

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
        <div class="col-md-6" id="page_info_area"></div>
        <!-- 分页条信息 -->
        <div class="col-md-6" id="page_nav_area">

        </div>
    </div>

</div>
<%--<input type="hidden" id="corp_id" name="corp_id" value="${}" />--%>
<script type="text/javascript">
    var totalRecord, currentPage;
    getNetworks("#searchInfo select", ${officeId});
    //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
    $(function () {
        //去首页
        to_page(1);
    });


    function to_page(pn) {
//        alert($("#address_search_select option:selected").text());
        <%--alert(${officeId});--%>
        $.ajax({
            url: "${APP_PATH}/a/officeDetailAll",
            data: {
                "pn": pn,
                "address_search_select": $("#address_search_select option:selected").text(),
                "ip_search_input": $("#ip_search_input").val(),
                "equipment_search_input": $("#equipment_search_input").val(),
                "officeId":${officeId},
                "isSort": $("#checkBoxUsed").is(":checked")
            },
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


    function build_emps_table(result) {
        //清空table表格
        $("#emps_table tbody").empty();
        var emps = result.extend.pageInfo.list;
        $.each(emps, function (index, item) {
            var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>").addClass();
            console.log(item);
//            var id = $("<td style='width: 0px'></td>").append(item.id);
            var eId = $("<td style='width: 0px;display: none'></td>").append(item.eId).attr("title", item.eId);
            var oName = $("<td></td>").append(item.officeName).attr("title", item.officeName);

            var address = $("<td></td>").append(item.address).attr("title", item.minAddress + " - " + item.maxAddress);
            var mask = $("<td></td>").append(item.mask).attr("title", item.mask);
            var ip = $("<td></td>").append(item.ip).attr("title", item.ip);
            var equipmentName = $("<td></td>").append(item.equipmentName).attr("title", item.equipmentName);
            var department = $("<td></td>").append(item.department).attr("title", item.department);
            var location = $("<td style='width: 200px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;'></td>").append(item.location).attr("title", item.location);
            var username = $("<td style='width: 200px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;'></td>").append(item.username).attr("title", item.username);
            var password = $("<td style='width: 200px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;'   ></td>").append(item.password).attr("title", item.password);
            var remark = $("<td style='width: 200px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;'></td>").append(item.remark).attr("title", item.remark);
            var ipTag = $("<td></td>").append(item.ipTag).attr("title", item.ipTag);

            /**
             <button class="">
             <span class="" aria-hidden="true"></span>
             编辑
             </button>
             */
            var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            //为编辑按钮添加一个自定义的属性，来表示当前员工id
            editBtn.attr("edit-id", item.eId);
            editBtn.attr("edit-ip", item.ip);
            editBtn.attr("off", item.officeName);

            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            //为删除按钮添加一个自定义的属性来表示当前删除的员工id
            delBtn.attr("del-id", item.id);
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            //var delBtn =
            //append方法执行完成以后还是返回原来的元素
            $("<tr></tr>")
                .append(eId)
                .append(oName)
                .append(address)
                .append(mask)
                .append(ip)
                .append(equipmentName)
                .append(department)
                .append(location)
                .append(username)
                .append(password)
                .append(ipTag)
                .append(remark)
                .append(btnTd)
                .appendTo("#emps_table tbody");
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
        var firstPageLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 14px'></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePageLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 14px'></li>").append($("<a></a>").append("&laquo;").attr("href", "#"));
        if (result.extend.pageInfo.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            //为元素添加点击翻页的事件
            firstPageLi.click(function () {
                to_page(1);
            });
            prePageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum - 1);
            });
        }


        var nextPageLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 14px'></li>").append($("<a></a>").append("&raquo;").attr("href", "#"));
        var lastPageLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 14px'></li>").append($("<a></a>").append("末页").attr("href", "#"));
        if (result.extend.pageInfo.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            nextPageLi.click(function () {
                to_page(result.extend.pageInfo.pageNum + 1);
                alert(result.extend.pageInfo.pageNum + 1);
            });
            lastPageLi.click(function () {
                to_page(result.extend.pageInfo.pages);
            });
        }


        //添加首页和前一页 的提示
        ul.append(firstPageLi).append(prePageLi);
        //1,2，3遍历给ul中添加页码提示
        $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {

            var numLi = $("<li style='display: inline;margin-left: 5px;margin-right5px;font-size: 14px'></li>").append($("<a></a>").append(item).attr("href", "#"));
            if (result.extend.pageInfo.pageNum == item) {
                numLi.addClass("active");
            }
            numLi.click(function () {
                to_page(item);
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
        //发送ajax请求，查出部门信息，

        getOffices("#empAddModal select[name=oId]");


//      getOffice("#empAddModal select[name=oId]");
        //弹出模态框
        $("#empAddModal").modal({
            backdrop: "static"
        });

        // 初始化数据
//        $('[name=oId]').trigger('change');
    });
    //批量上传，跳转到import.jsp
    $("#import_btn").click(function () {

        window.location.href = "/a/importExcel";
//        window.location.href='data/gotoImport';
    });
    $("#export_btn").click(function () {

        window.location.href = "/a/exportExcel";
//        window.location.href='data/gotoImport';
    });

    //查出所有的校区信息并显示在下拉列表中
    function getOffices(ele) {
        //清空之前下拉列表的值
        $(ele).empty();
        $.ajax({
            url: "${APP_PATH}/a/getOffices",
            type: "GET",
            success: function (result) {
                $.each(result.extend.offices, function () {
                    var optionEle = $("<option></option>").append(this.oName).attr("value", this.oId);
                    optionEle.appendTo(ele);
                });
                $(ele).trigger('change');

            }
        });

    }


    //查出所有的选择下的所有网络地址段信息并显示在下拉列表中
    function getNetworks(ele, oId) {
        //清空之前下拉列表的值
        $(ele).empty();
        $.ajax({
            url: "${APP_PATH}/a/getNetworksDetailsWithOid",
            data: "oId=" + oId,
            type: "GET",
            success: function (result) {
                $.each(result.extend.deps, function () {
                    if (this.nId < 0) {
                        var optionEle = $("<option></option>").append("全部网段").attr("value", this.nId);
                    } else {
                        var pre = new Array();
                        pre = this.nMinAddress.split(".");
                        var pres = "";
                        for (i = 0; i < 3; i++) {
                            pres += pre[i] + ".";
                        }
                        pres = pres.substr(0, pres.length - 1);
                        var optionEle = $("<option></option>").append(pres).attr("value", this.nId).attr("title", pres + ".*");
                    }
                    $("#equipment_mask_add_input").val(this.mask);
                    optionEle.appendTo(ele);
                });
                $(ele).trigger('change');
            }
        })
        ;

    }

    //查出所有的选择下的所有网络地址段信息并显示在下拉列表中
    function getIps(ele, nId) {
        //清空之前下拉列表的值
        $(ele).empty();
        $.ajax({
            url: "${APP_PATH}/a/getIpWithOid",
            data: "nId=" + nId,
            type: "GET",
            success: function (result) {
                //{"code":100,"msg":"处理成功！",
                //"extend":{"depts":[{"deptId":1,"deptName":"开发部"},{"deptId":2,"deptName":"测试部"}]}}
                //console.log(result);
                //显示部门信息在下拉列表中
                //$("#empAddModal select").append("")
                $.each(result.extend.ips, function () {
                    var optionEle = $("<option></option>").append(this.ip).attr("value", this.iId);
                    optionEle.appendTo(ele);
                });
            }
        });

    }


    function ValidateIPaddress(ip) {
        if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(ip)) {
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
    $("#office_name_add_input").change(function () {
        //发送ajax请求校验用户名是否可用

        var empName = this.value;
        $.ajax({
            url: "${APP_PATH}/a/checkDepartments",
            data: "empName=" + empName,
            type: "POST",
            success: function (result) {
                if (result.code == 100) {
                    show_validate_msg("#office_name_add_input", "success", "部门名可用");
                    $("#emp_save_btn").attr("ajax-va", "success");
                } else {
                    show_validate_msg("#office_name_add_input", "error", result.extend.va_msg);
                    $("#emp_save_btn").attr("ajax-va", "error");
                    return false;
                }
            }
        });
    });
    $("#office_name_update_input").change(function () {
        //发送ajax请求校验用户名是否可用

        var empName = this.value;
        $.ajax({
            url: "${APP_PATH}/a/checkDepartments",
            data: "empName=" + empName,
            type: "POST",
            success: function (result) {
                if (result.code == 100) {
                    show_validate_msg("#office_name_update_input", "success", "部门名可用");
                    $("#emp_update_btn").attr("ajax-va", "success");
                } else {
                    show_validate_msg("#office_name_update_input", "error", result.extend.va_msg);
                    $("#emp_update_btn").attr("ajax-va", "error");
                    return false;
                }
            }
        });
    });

    //点击保存，保存员工。
    $("#emp_save_btn").click(function () {


        //1、模态框中填写的表单数据提交给服务器进行保存

        //1、先对要提交给服务器的数据进行校验
        if (!validate_add_form()) {
            return false;
        }
        //1、判断之前的ajax用户名校验是否成功。如果成功。
        /*  if ($(this).attr("ajax-va") == "error") {
  //             alert("验证失败");
              return false;
          }*/
        //alert( $("#empAddModal form").serialize())
        //2、发送ajax请求保存员工
        $.ajax({
            /*  beforeSend : function (XMLHttpRequest) {
                  XMLHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
              },*/
            url: "${APP_PATH}/a/equipment",
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
                    to_page(totalRecord);
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
        getOffices("#empUpdateModal select[name=oId]");


        //1、查出部门信息，并显示部门列表
//        getDepts("#empUpdateModal select");
        //2、查出员工信息，显示员工信息
        initData($(this).attr("edit-id"));
        $("#empUpdateModal h4").text("修改原IP为" + $(this).attr("edit-ip") + "的设备信息");
//        $("#office_name_update_select").val($(this).attr("off"));
//        alert($(this).attr("off"));
        //3、把员工的id传递给模态框的更新按钮
        $("#emp_update_btn").attr("edit-id", $(this).attr("edit-id"));
        $("#empUpdateModal").modal({
            backdrop: "static"
        });
    });

    function initData(id) {
        $.ajax({
            url: "${APP_PATH}/a/equipment/" + id,
            type: "GET",
            success: function (result) {

                console.log("---->" + result);
                var myData = result.extend.equipmentVo;

//                alert(myData.oId);

//                alert(myData.officeName);
//                alert(myData.campusName);
                // $("#office_name_update_select option[text = "+myData.officeName+"]").attr("selected", true);
//                $("#office_name_update_select option[text = '海龙校区']").attr("selected", true);
//                $("#office_name_update_select option[text = '海龙校区']").attr("selected", true);

//                console.log($("#office_name_update_select").val(myData.officeName));
//                $("#office_name_update_select").attr('selected',true).val([myData.officeName]).trigger('change');
                //$("#office_name_update_select").val(myData.officeName);
//                getOffices("#empUpdateModal select[name=oId]", myData.oId);
                $("#network_update_select").val(myData.minAddress + myData.maxAddress);
                $("#equipment_mask_update_input").val(myData.mask);
                $("#ip_update_select").val(myData.ip);
                $("#equipment_using_update_input").val(myData.equipmentName);
                $("#department_update_select").val(myData.department);
                $("#equipment_location_update_input").val(myData.location);
                $("#equipment_username_update_input").val(myData.username);
                $("#equipment_password_update_input").val(myData.password);
                $("#equipment_remark_update_input").val(myData.remark);
                /*    $("#empUpdateModal input[name=gender]").val([myData.gender]);
                    $("#empUpdateModal select").val([myData.dId]);*/
                initEditiModal("#empUpdateModal select[name=oId]", myData.oId, "#empUpdateModal select[name=nId]", myData.nId, "#empUpdateModal select[name=iId]", myData.iId, "#empUpdateModal select[name=dId]", myData.dId)
            }

        });
    }

    function initEditiModal(ele1, oId, ele2, nId, ele3, iId, ele4, dId) {
//        alert(oId+"  " + nId +"   " +iId + "   " + dId);
        //设置校区默认值
        //清空之前下拉列表的值
        $(ele1).empty();
        $.ajax({
            url: "${APP_PATH}/a/getOffices",
            type: "GET",
            success: function (result) {
                $.each(result.extend.offices, function () {
                    var optionEle = $("<option></option>").append(this.oName).attr("value", this.oId);
                    optionEle.appendTo(ele1);
                });
                $(ele1).trigger('change');
                if (oId != null) {
                    $("#office_name_update_select").val(oId);
                }
            }
        });

        //设置网段默认值
        //清空之前下拉列表的值
        $(ele2).empty();
        $.ajax({
            url: "${APP_PATH}/a/getNetworksDetailsWithOid",
            data: "oId=" + oId,
            type: "GET",
            success: function (result) {
                $.each(result.extend.deps, function () {
                    if (this.nId < 0) {
                        var optionEle = $("<option></option>").append("全部网段").attr("value", this.nId);
                    } else {
                        var pre = new Array();
                        pre = this.nMinAddress.split(".");
                        var pres = "";
                        for (i = 0; i < 3; i++) {
                            pres += pre[i] + ".";
                        }
                        pres = pres.substr(0, pres.length - 1);
                        var optionEle = $("<option></option>").append(pres).attr("value", this.nId).attr("title", pres + ".*");
                    }
                    $("#equipment_mask_add_input").val(this.mask);
                    optionEle.appendTo(ele2);
                });
                $(ele2).trigger('change');
                if (nId != null) {
                    $("#network_update_select").val(nId);
                }
            }
        });
        //设置ip默认值
        //清空之前下拉列表的值
        $(ele3).empty();
        $.ajax({
            url: "${APP_PATH}/a/getIpWithOid",
            data: "nId=" + nId,
            type: "GET",
            success: function (result) {
                //{"code":100,"msg":"处理成功！",
                //"extend":{"depts":[{"deptId":1,"deptName":"开发部"},{"deptId":2,"deptName":"测试部"}]}}
                //console.log(result);
                //显示部门信息在下拉列表中
                //$("#empAddModal select").append("")
                $.each(result.extend.ips, function () {
                    var optionEle = $("<option></option>").append(this.ip).attr("value", this.iId);
                    optionEle.appendTo(ele3);
                });
                $(ele3).trigger('change');
                if (iId != null) {
                    $("#ip_update_select").val(iId);
                }
            }
        });
        //设置部门默认值
        //清空之前下拉列表的值
        $(ele4).empty();
        $.ajax({
            url: "${APP_PATH}/a/getDepartmentsWithOid",
            data: "oId=" + oId,
            type: "GET",
            success: function (result) {
                //{"code":100,"msg":"处理成功！",
                //"extend":{"depts":[{"deptId":1,"deptName":"开发部"},{"deptId":2,"deptName":"测试部"}]}}
                //console.log(result);
                //显示部门信息在下拉列表中
                //$("#empAddModal select").append("")
                $.each(result.extend.deps, function () {
                    var optionEle = $("<option></option>").append(this.dName).attr("value", this.dId);
                    optionEle.appendTo(ele4);
                });
                $(ele4).trigger('change');
                if (dId != null) {
                    $("#department_update_select").val(dId);
                }
            }
        });

    }

    $("#search_office_details").click(function () {
        to_page(1);
    });
    //点击更新，更新员工信息
    $("#emp_update_btn").click(function () {
        if (!validate_update_form()) {
            return false;
        }
        var idExist = $(this).attr("edit-id");
        if(typeof $(this).attr("edit-id")=="undefined"){
            idExist = -1;
        }
        //2、发送ajax请求保存更新的员工数据
        $.ajax({
            url: "${APP_PATH}/a/equipment/" + idExist,
            type: "PUT",
            data: $("#empUpdateModal form").serialize(),
            success: function (result) {

//                alert(result.msg);
                //1、关闭对话框
                $("#empUpdateModal").modal("hide");
                //2、回到本页面
                to_page(currentPage);
            },
            error: function (result) {
                alert("put请求失败");
            }
        });
    });


    //查出所有的选择下的所有部门信息并显示在下拉列表中
    function getDepartments(ele, oId) {
        //清空之前下拉列表的值
        $(ele).empty();
        $.ajax({
            url: "${APP_PATH}/a/getDepartmentsWithOid",
            data: "oId=" + oId,
            type: "GET",
            success: function (result) {
                //{"code":100,"msg":"处理成功！",
                //"extend":{"depts":[{"deptId":1,"deptName":"开发部"},{"deptId":2,"deptName":"测试部"}]}}
                //console.log(result);
                //显示部门信息在下拉列表中
                //$("#empAddModal select").append("")
                $.each(result.extend.deps, function () {
                    var optionEle = $("<option></option>").append(this.dName).attr("value", this.dId);
                    optionEle.appendTo(ele);
                });
            }
        });

    }

    //单个删除
    $(document).on("click", ".delete_btn", function () {
        //1、弹出是否确认删除对话框
        var id = $(this).parents("tr").find("td:eq(1)").text();
        var office = $(this).parents("tr").find("td:eq(5)").text();
        var oId = $(this).attr("del-id");
//        alert($(this).parents("tr").find("td:eq(1)").text());
        if (confirm("确认删除ip为【" + office + "】的数据吗？")) {
            //确认，发送ajax请求删除即可
            $.ajax({
                url: "${APP_PATH}/a/equipment/" + id,
                type: "DELETE",
                success: function (result) {
                    alert(result.msg);
                    //回到本页
                    to_page(currentPage);
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
            ipNames += $(this).parents("tr").find("td:eq(5)").text() + ",";
            //组装员工id字符串
            del_idstr += $(this).parents("tr").find("td:eq(1)").text() + "-";
        });
        //ipNames,
        ipNames = ipNames.substring(0, ipNames.length - 1);
        //去除删除的id多余的-
        del_idstr = del_idstr.substring(0, del_idstr.length - 1);
        if (confirm("确认删除ip为【" + ipNames + "】的数据吗？")) {
            //发送ajax请求删除
            $.ajax({
                url: "${APP_PATH}/a/equipment/" + del_idstr,
                type: "DELETE",
                success: function (result) {
                    alert(result.msg);
                    //回到当前页面
                    to_page(currentPage);
                }
            });
        }
    });

    function officeChange() {
//        getDepartments("#empAddModal select[name=dId]", $('select[name=oId]  option:selected').val());
        getNetworks("#empAddModal select[name=nId]", $('select[name=oId]  option:selected').val());
//        $('[name=nId]').trigger('change');

//        alert($('select[name=oId]').val());
    }

    function addressChange() {
//        getDepartments("#empAddModal select[name=dId]",$('select[name=oId]  option:selected').val());
        getIps("#empAddModal select[name=iId]", $('select[name=nId]  option:selected').val());
//        alert($('select[name=nId]  option:selected').val());
    }

    function officeChangeUpdate() {
//        getDepartments("#empUpdateModal select[name=dId]", $('select[name=oId]  option:selected').val());
        getDepartments("#empUpdateModal select[name=dId]", $('select[name=oId]  option:selected').val());
        getNetworks("#empUpdateModal select[name=nId]", $('select[name=oId]  option:selected').val());
//        $('[name=nId]').trigger('change');

//        alert($('select[name=oId]').val());
    }

    function addressChangeUpdate() {
//        getDepartments("#empAddModal select[name=dId]",$('select[name=oId]  option:selected').val());
        getIps("#empUpdateModal select[name=iId]", $('select[name=nId]  option:selected').val());
//        alert($('select[name=nId]  option:selected').val());
    }

    function validate_update_form() {
        var equipment_using_update_input = $("#equipment_using_update_input").val();
        if (equipment_using_update_input == null || equipment_using_update_input == "") {
            show_validate_msg("#equipment_using_update_input", "error", "请输入设备名称");
            return false;
        } else {
            show_validate_msg("#equipment_using_update_input", "success", "");
        }
        var equipment_location_update_input = $("#equipment_location_update_input").val();
        if (equipment_location_update_input == null || equipment_location_update_input == "") {
            show_validate_msg("#equipment_location_update_input", "error", "存储位置不能为空");
            return false;
        } else {
            show_validate_msg("#equipment_location_update_input", "success", "");
        }

        return true;
    }

    //校验表单数据
    function validate_add_form() {
        var equipment_using_add_input = $("#equipment_using_add_input").val();
        if (equipment_using_add_input == null || equipment_using_add_input == "") {
            show_validate_msg("#equipment_using_add_input", "error", "请输入设备名称");
            return false;
        } else {
            show_validate_msg("#equipment_using_add_input", "success", "");
        }
        var equipment_location_add_input = $("#equipment_location_add_input").val();
        if (equipment_location_add_input == null || equipment_location_add_input == "") {
            show_validate_msg("#equipment_location_add_input", "error", "存储位置不能为空");
            return false;
        } else {
            show_validate_msg("#equipment_location_add_input", "success", "");
        }
        return true;
    }

    //select 点击选择时onchange事件
    function changeSelectOffice() {
        to_page(1);
    }

    $("#used_first").click(function () {
//        $("#checkBoxUsed").trigger('change');
        if ($("#checkBoxUsed").is(":checked")) {
            $("#checkBoxUsed").prop("checked", false);
        } else {
            $("#checkBoxUsed").prop("checked", true);
        }
        to_page(1);
    });

</script>
</body>
</html>