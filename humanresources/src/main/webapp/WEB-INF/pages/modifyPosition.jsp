<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/31
  Time: 16:43
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

    欢迎来到职位修改页面<br>
    <form action="modifyPosition1">
        请输入新的职位名称：<input type="text" name="newPositionName"><br>
        请输入职位的基本薪资：<input type="number" name="newPositionSalary"><br>
        <input type="submit" value="提交修改">
    </form>
    ${sessionScope.modifyPositionError}

</body>
</html>

