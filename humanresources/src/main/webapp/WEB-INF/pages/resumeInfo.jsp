<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/26
  Time: 21:51
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

简历名称：${sessionScope.showResumeInfo.resumename}<br>
姓名：${sessionScope.showResumeInfo.name}<br>
性别：${sessionScope.showResumeInfo.gender}<br>
电话：${sessionScope.showResumeInfo.phone}<br>
e-mail：${sessionScope.showResumeInfo.email}<br>
地址：${sessionScope.showResumeInfo.address}<br>
教育经历：${sessionScope.showResumeInfo.education}<br>
工作经历：${sessionScope.showResumeInfo.workexperience}<br>
自我介绍：${sessionScope.showResumeInfo.introduction}<br>

<a href="modifyResume"><input type="button" value="修改"></a><a href="resume"><input type="button" value="返回简历管理"></a>


</body>
</html>

