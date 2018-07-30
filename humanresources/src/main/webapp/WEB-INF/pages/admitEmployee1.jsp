<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/29
  Time: 16:06
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

录取新员工<br>
<form action="admitEmployee2">
    员工登录用户名:<input type="text" name="uname">
    员工登录密码：<input type="password" name="upassword">
    <input type="submit" value="提交">
    <a href="returnManagerWelcome"><input type="button" value="返回主页面"></a>
</form>

${sessionScope.admitEmployeeError}

</body>
</html>

