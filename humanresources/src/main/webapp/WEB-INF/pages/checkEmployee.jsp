<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/31
  Time: 16:21
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

<c:if test="${fn:length(sessionScope.checkEmployeeList)==0}">
    该职位下没有员工<br>
    <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>
</c:if>

<c:if test="${fn:length(sessionScope.checkEmployeeList)!=0}">
    <table>
        <tr>
            <th>员工id</th>
            <th>员工姓名</th>
            <th>员工电话</th>
            <th>员工email</th>
            <th>员工身份证号</th>
            <th>员工所属部门</th>
            <th>员工所属职位</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.checkEmployeeList)-1}">
            <tr>
                <td>${sessionScope.checkEmployeeList[i].id}</td>
                <td>${sessionScope.checkEmployeeList[i].name}</td>
                <td>${sessionScope.checkEmployeeList[i].phone}</td>
                <td>${sessionScope.checkEmployeeList[i].email}</td>
                <td>${sessionScope.checkEmployeeList[i].idcard}</td>
                <td>${sessionScope.checkEmployeeList[i].department.name}</td>
                <td>${sessionScope.checkEmployeeList[i].position.name}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>
</c:if>

</body>
</html>

