<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/28
  Time: 21:39
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

    管理员登录成功

    <ul>
        <li><a href="showRecruitment?currentPage=1">查看应聘简历</a></li>
    </ul>

</body>
</html>

