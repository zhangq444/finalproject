<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/26
  Time: 21:57
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

    修改简历
    <form action="modifyResume1">
        <input type="hidden" name="id" value="${sessionScope.showResumeInfo.id}" >
        姓名：<input type="text" name="name" value="${sessionScope.showResumeInfo.name}"><br>
        性别：<input type="radio" name="gender" value="男">男<input type="radio" name="gender" value="女">女<br>
        电话：<input type="text" name="phone" value="${sessionScope.showResumeInfo.phone}"><br>
        e-mail：<input type="text" name="email" value="${sessionScope.showResumeInfo.email}"><br>
        地址：<input type="text" name="address" value="${sessionScope.showResumeInfo.address}"><br>
        教育经历：<textarea name="education">${sessionScope.showResumeInfo.education}</textarea><br>
        工作经历：<textarea name="workexperience">${sessionScope.showResumeInfo.workexperience}</textarea><br>
        自我介绍：<textarea name="introduction">${sessionScope.showResumeInfo.introduction}</textarea><br>
        简历名称：<input type="text" name="resumename" value="${sessionScope.showResumeInfo.resumename}"><br>
        <input type="submit" value="提交修改">
        <a href="resume"><input type="button" value="返回简历管理"></a>
    </form>


</body>
</html>

