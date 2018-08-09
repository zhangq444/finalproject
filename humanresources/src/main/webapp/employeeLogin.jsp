<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/3
  Time: 9:44
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
        #div1{
            margin-left: auto;
            margin-right: auto;
            margin-top: 100px;
            padding-top: 100px;
            background-image: url("/pic/register.jpg");
            width: 800px;
            height: 400px;
            text-align: center;
        }

    </style>
</head>
<body>

<div id="div1">
    欢迎来到员工登录页面<br>
    <br>
    <form action="employeeLogin">
        用户：<input type="text" name="uname"><br>
        密码：<input type="password" name="upassword"><br>
        <br>
        <input type="submit" value="员工登录">
    </form>
    <br>
    ${sessionScope.employeeLoginError}
</div>

</body>
</html>

