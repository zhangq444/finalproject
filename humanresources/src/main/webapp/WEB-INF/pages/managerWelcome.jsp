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
        <li><a href="showRecruitment?currentPage=1"><input type="button" value="查看应聘简历"></a></li>
        <li><a href="admitEmployee"><input type="button" value="录取员工"></a></li>
        <li><a href="recruitManage"><input type="button" value="招聘管理"></a></li>
        <li><a href="showDepartment"><input type="button" value="部门职位管理"></a></li>
    </ul>

<br>
<%--<a href="test"><input type="button" value="测试二级联动"></a>--%>





</body>
</html>

