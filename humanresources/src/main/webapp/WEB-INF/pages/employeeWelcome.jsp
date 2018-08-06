<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/3
  Time: 10:00
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

    欢迎来到员工界面<br>

    <ul>
        <li><a href="checkTrain"><input type="button" value="查看培训通知"></a></li>
        <li><a href="clockin"><input type="button" value="打卡"></a></li>
        <li><a href="personInfoManage"><input type="button" value="个人信息管理"></a></li>
        <li><a href="checkEmployeeDetail"><input type="button" value="查看公司通讯录"></a></li>
        <li><a href="checkRewards"><input type="button" value="奖惩信息"></a></li>
        <li><a href="checkSalary"><input type="button" value="薪资管理"></a></li>


    </ul>


</body>
</html>

