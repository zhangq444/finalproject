<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/1
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title></title>
</head>
<body>

    显示用户详细信息：<br>
    员工id:${sessionScope.showEmployeeInfo.id}<br>
    员工姓名:${sessionScope.showEmployeeInfo.name}<br>
    员工性别:${sessionScope.showEmployeeInfo.gender}<br>
    员工电话:${sessionScope.showEmployeeInfo.phone}<br>
    员工email:${sessionScope.showEmployeeInfo.email}<br>
    员工地址:${sessionScope.showEmployeeInfo.address}<br>
    员工身份证:${sessionScope.showEmployeeInfo.idcard}<br>
    员工生日:${fnn:DateToString(sessionScope.showEmployeeInfo.birthday)}<br>
    员工学校:${sessionScope.showEmployeeInfo.school}<br>
    员工专业:${sessionScope.showEmployeeInfo.major}<br>
    员工教育经历:${sessionScope.showEmployeeInfo.education}<br>
    员工所属部门:${sessionScope.showEmployeeInfo.department.name}<br>
    员工职位:${sessionScope.showEmployeeInfo.position.name}<br>
    员工入职时间:${fnn:DateToString(sessionScope.showEmployeeInfo.hiredate)}<br>
    员工状态:${sessionScope.showEmployeeInfo.state}<br>
    员工用户名:${sessionScope.showEmployeeInfo.uname}<br>
    员工密码:${sessionScope.showEmployeeInfo.upassword}<br>
    <br>
    <a href="returnEmployeeManage"><input type="button" value="返回查询页面"></a>



</body>
</html>

