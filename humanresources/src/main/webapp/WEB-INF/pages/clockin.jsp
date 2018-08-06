<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/3
  Time: 15:25
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

    欢迎进行上下班打卡<br>
    <br>
    <form action="punchin">
        <input type="submit" value="上班打卡">
    </form>
    &nbsp;
    <form action="punchout">
        <input type="submit" value="下班打卡">
    </form>
    &nbsp;
    <form action="overtime">
        <input type="submit" value="加班打卡">
    </form>
    &nbsp;
    <a href="returnEmployeeWelcome"><input type="button" value="返回主页"></a>
    <br>
    ${sessionScope.punchinError}<br>
    ${sessionScope.punchoutError}<br>
    ${sessionScope.overtimeError}<br>


</body>
</html>

