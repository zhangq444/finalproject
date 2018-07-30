<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/30
  Time: 15:24
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

修改招聘信息

<form action="/modifyRecruit1">
    招聘人数：<input type="number" name="number"><br>
    岗位描述：<textarea name="description">${sessionScope.modifyRecruit.description}</textarea><br>
    岗位要求：<textarea name="requirement">${sessionScope.modifyRecruit.requirement}</textarea><br>
    <input type="submit" value="提交修改">
</form>



</body>
</html>

