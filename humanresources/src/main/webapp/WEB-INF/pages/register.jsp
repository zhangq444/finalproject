<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/25
  Time: 20:01
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

register<br>

<form action="register">
    用户:<input type="text" name="name"><br>
    密码:<input type="password" name="password"><br>
    <input type="submit" value="提交">
    <a href="returnWelcome"><input type="button" value="返回主页"></a>
</form>

${sessionScope.registerError}

</body>
</html>

