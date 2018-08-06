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
</head>
<body>

    欢迎来到员工登录页面<br>
    <br>
    <form action="employeeLogin">
        用户名：<input type="text" name="uname"><br>
        密码：<input type="password" name="upassword"><br>
        <input type="submit" value="员工登录">
    </form>
    <br>
    ${sessionScope.employeeLoginError}


</body>
</html>

