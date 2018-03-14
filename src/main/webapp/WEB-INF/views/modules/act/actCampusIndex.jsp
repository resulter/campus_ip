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

        .hover-light:hover {
            background: #45aeea;
        }

        .view-own {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            font-size: 22px;
        }

        .search_input {
            padding-left: 50px;
            padding-top: 8px;
        }

        .search_btn {
            padding-top: 8px;
        }

        /*#page_info_area,#page_nav_area{float:left;}*/
    </style>

</head>
<body>
<div class="form-group search_input" id="searchInfo">
    <div class="col-sm-2 ">
        <input type="text" name="search_campus" class="form-control" id="campus_search_input"
               placeholder="校区名称/首字母缩写">
        <span class="help-block"></span>
    </div>
    <button class="btn btn-default" type="button" id="campus_search_btn">搜索</button>
    <span style="font-size: small;text-align: match-parent"><span style="color:red;">*</span>在查看了一个校区详情后，如果要看其他校区详情，请关闭此标签页</span>
</div>


<div class="row" id="campus_view" style="padding: 25px"></div>

<script type="text/javascript">
    $(function () {
        //获取数据
        getData();
    });

    function getData() {
        $("#campus_view").html('');
        $.ajax({
            url: "${APP_PATH}/a/getAllCampus",
            data: {
                "campus_search_input": $("#campus_search_input").val(),
            },
            type: "GET",
            success: function (result) {
                console.log(result);
                var $container = $('#campus_view');
                var offices = result.extend.offices;
                $.each(offices, function (idx) {
                    $container.append('<div class="col-sm-2 hover-light view-own" data-id="' + offices[idx].oId + '">' + offices[idx].oName + '</div>');
                });


                $(".view-own").click(function () {
                    window.location.href = "${APP_PATH}/a/officeDetail?officeId=" + $(this).attr("data-id");
                });
            }
        });
    }


    $("#campus_search_input").bind('input propertychange', function () {
        getData();
    });
    $("#campus_search_btn").click(function () {
        getData();
    });


</script>
</body>
</html>