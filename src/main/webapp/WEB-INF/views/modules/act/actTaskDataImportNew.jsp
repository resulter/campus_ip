<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工列表</title>
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
    <script type="text/javascript">
        //用于验证文件扩展名的正则表达式
        function checkSuffix(){
            var name = document.getElementById("txt").value;
            var strRegex = "(.xls|.xlsx)$";
            var re=new RegExp(strRegex);
            if (re.test(name.toLowerCase())){
                alert("正在上传，稍后将跳转到主页");
                document.fileupload.submit();
            } else{
                alert("文件名不合法");
            }
        }
    </script>
</head>
<body>
<div class="center-block" style="margin-left: auto; margin-right: auto;width: 450px">
<form name="fileupload" enctype="multipart/form-data" action="${APP_PATH}/a/uploadFileAndImportNew" method="post">
    <div >
    <h2 style="font-size:20px;width: 450px" class="lead">请选择正确的excel文件 <span style="color:red;width: 265px;font-size: 17px">(支持的excel格式：xls、xlsx！)</span></h2>
    </div>
    <div onclick="file.click()">
        <%--<label class="col-sm-3 control-label">点击选择文件</label>--%>
    <input id="txt" class="input" type="text" disabled="disabled" value="点击选择文件" name="txt" style="width: 450px">
            </div>
    <input type="button" onclick="checkSuffix();" value="提交上传" style="margin: 10px;width: 430px" class="btn btn-default">
    <%--<input class="liulan" type="button" onclick="file.click()" size="30" value="上传文件" style="opacity: 0;" onmousemove="file.style.pixelLeft=event.x-60;file.style.pixelTop=this.offsetTop;">--%>
    <input id="file" class="files " type="file" hidefocus="" size="1" style="height:26px;opacity: 0" name="file" onchange="txt.value=this.value">
    <%--<input type="button" onclick="checkSuffix();" value="提交上传" style="padding-top: -27px" class="btn btn-default">--%>

</form>
</div>
<div style="margin:0 auto;width: 720px">
    <h3 style="color: red">请严格按照模板样式上传文件，否则可能会造成不可估计的错误！</h3>
<table border="1">
    <tr>
        <th>校区名称</th>
        <th>网络地址段</th>
        <th style="text-align:center">掩码</th>
        <th>ip地址</th>
        <th>使用设备</th>
        <th>使用部门</th>
        <th>存放位置</th>
        <th>用户名</th>
        <th>密码</th>
        <th>备注</th>
    </tr>
    <tr>
        <td>总部校区</td>
        <td>10.152.25.0</td>
        <td>255.255.255.0</td>
        <td>10.152.25.1</td>
        <td>路由器</td>
        <td>网络运营部</td>
        <td>水清机房</td>
        <td>admin</td>
        <td>XXXXX</td>
        <td>示例</td>
    </tr>
</table>
    <div style="margin: 5px">
    <a href="${APP_PATH}/a/exportTemplate" class="btn btn-link">点击下载模板文件</a>
    <a href="${APP_PATH}/a/backCampusNew" class="btn btn-link">返回上一页</a>
    </div>
    </div>
</body>
<%--<form name="uploadform" method="POST" action="./servlet/UploadServlet" ENCTYPE="multipart/form-data">--%>

    <%--<table border="1" width="450" cellpadding="4" cellspacing="2" bordercolor="#9BD7FF">--%>
        <%--<tr>--%>
            <%--<td width="100%" colspan="2">--%>

                <%--文件：<input name="x" size="40" type="file">--%>

            <%--</td>--%>
        <%--</tr>--%>
    <%--</table>--%>
    <%--<br/><br/>--%>

    <%--<table>--%>
        <%--<tr><td align="center"><input name="upload" type="submit" value="开始上传"/></td></tr>--%>
    <%--</table>--%>

<%--</form>--%>
</body>
</html>