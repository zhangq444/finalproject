<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/25
  Time: 19:52
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

<form action="loginAndRegister">
    用户:<input type="text" name="name"><br>
    密码:<input type="text" name="password"><br>
    <input type="submit" value="登录" name="login">
    <input type="submit" value="注册" name="register">
    <input type="submit" value="直接登录" name="loginDirect">
</form>

${sessionScope.loginUser.name}<br>
${sessionScope.loginUser.password}<br>

${sessionScope.loginError}<br>

<a href="resume"><input type="button" value="简历管理"></a><br>
${sessionScope.resumeError}<br>



招聘信息


</body>
</html>

