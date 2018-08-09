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
    管理员登录页面<br>
    <br>
    <form action="managerLogin">
        管理员用户：<input type="text" name="name"><br>
        管理员密码：<input type="password" name="password"><br>
        <br>
        <input type="submit" value="管理员登录">
    </form>
    <br>
    ${sessionScope.managerLoginError}



</div>


<%--



管理员登录页面<br>
<form action="managerLogin">
    管理员用户：<input type="text" name="name"><br>
    管理员密码：<input type="password" name="password"><br>
    <input type="submit" value="管理员登录">
</form>
<br>
${sessionScope.managerLoginError}
--%>


</body>
</html>

