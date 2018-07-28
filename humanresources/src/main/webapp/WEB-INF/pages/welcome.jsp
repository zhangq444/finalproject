<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>

<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/25
  Time: 19:52
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

<form action="loginAndRegister">
    用户:<input type="text" name="name"><br>
    密码:<input type="text" name="password"><br>
    <input type="submit" value="登录" name="login">
    <input type="submit" value="注册" name="register">
    <input type="submit" value="直接登录" name="loginDirect">
</form>

${sessionScope.loginUser.name}<br>
${sessionScope.loginUser.password}<br>

${sessionScope.loginError}<br>

<a href="resume"><input type="button" value="简历管理"></a><br>
${sessionScope.resumeError}<br>



招聘信息

<table>
    <tr>
        <th>招聘部门</th>
        <th>招聘职位</th>
        <th>招聘数量</th>
        <th>基本薪资</th>
        <th>招聘时间</th>
        <th>招聘详情</th>
        <th>投递选择</th>
    </tr>
    <c:forEach var="i" begin="0" end="${fn:length(sessionScope.recruitList)-1}">
        <tr>
            <td>${sessionScope.recruitList[i].department.name}</td>
            <td>${sessionScope.recruitList[i].position.name}</td>
            <td>${sessionScope.recruitList[i].number}</td>
            <td>${sessionScope.recruitList[i].salary}</td>
            <td>${fnn:DateToString(sessionScope.recruitList[i].time)}</td>
            <%--<td>${sessionScope.recruitList[i].time}</td>--%>
            <td>
                <form action="recruitInfo">
                    <input type="hidden" name="recruitInfoId" value="${sessionScope.recruitList[i].id}">
                    <input type="submit" value="查看详情">
                </form>
            </td>
            <td>
                <input type="checkbox" id="sendResume" name="sendResume" value="${sessionScope.recruitList[i].id}">
            </td>

        </tr>

    </c:forEach>
</table>





</body>
</html>

