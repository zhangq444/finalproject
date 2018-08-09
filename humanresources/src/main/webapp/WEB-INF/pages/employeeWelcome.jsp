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
    <style>
        div{
            width: 1000px;
            margin-left: auto;
            margin-right: auto;
        }
        ul{
            list-style-type: none;
            margin: 0px;
            padding: 0px;
        }
        li{
            list-style-type: none;
            float: left;
            margin: 20px;
            width: 100px;
            height: 30px;
        }

    </style>
</head>
<body>

<jsp:include page="head.jsp"></jsp:include>

<div>
    <h2>欢迎来到员工界面</h2>
    <br>

    <ul>
        <li><a href="checkTrain"><input type="button" value="查看培训通知"></a></li>
        <li><a href="clockin"><input type="button" value="日常打卡管理"></a></li>
        <li><a href="personInfoManage"><input type="button" value="个人信息管理"></a></li>
        <li><a href="checkEmployeeDetail"><input type="button" value="查公司通讯录"></a></li>
        <li><a href="checkRewards"><input type="button" value="奖惩信息管理"></a></li>
        <li><a href="checkSalary"><input type="button" value="薪资信息管理"></a></li>
    </ul>

</div>

</body>
</html>

