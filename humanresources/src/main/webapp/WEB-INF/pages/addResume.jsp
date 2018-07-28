<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/26
  Time: 20:42
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

<form action="addResume1">
    姓名：<input type="text" name="name"><br>
    性别：<input type="radio" name="gender" value="男">男<input type="radio" name="gender" value="女">女<br>
    电话：<input type="text" name="phone"><br>
    e-mail：<input type="text" name="email"><br>
    地址：<input type="text" name="address"><br>
    身份证：<input type="text" name="idcard"><br>
    生日：<input type="date" name="birthdaydate"><br>
    毕业学校：<input type="text" name="school"><br>
    专业：<input type="text" name="major"><br>
    教育经历：<textarea name="education"></textarea><br>
    工作经历：<textarea name="workexperience"></textarea><br>
    自我介绍：<textarea name="introduction"></textarea><br>
    简历名称：<input type="text" name="resumename"><br>
    <input type="submit" value="提交">
</form>

</body>
</html>

