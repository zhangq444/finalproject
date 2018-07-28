<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/28
  Time: 21:16
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

管理员登录页面<br>
<form action="managerLogin">
    管理员用户：<input type="text" name="name"><br>
    管理员密码：<input type="password" name="password"><br>
    <input type="submit" value="管理员登录">
</form>
<br>
${sessionScope.managerLoginError}


</body>
</html>

