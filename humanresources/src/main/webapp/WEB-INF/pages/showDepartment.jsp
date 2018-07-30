<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/30
  Time: 20:05
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

<form action="checkPosition">
    请选择部门和职位：
    <select id="selectDep" name="selectDep">
        <option value="0">请选择</option>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showDepartmentList)-1}">
            <option value="${sessionScope.showDepartmentList[i].id}" class="option1">${sessionScope.showDepartmentList[i].name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="查看部门" name="showButton">&nbsp;<input type="submit" value="修改部门" name="modifyButton">
    &nbsp;<input type="submit" value="增加部门" name="addButton">&nbsp;<input type="submit" value="删除部门" name="deleteButton">
    &nbsp;<a href="returnManagerWelcome"><input type="button" value="返回主页面"></a>
</form>
<br>
<br>

<c:if test="${fn:length(sessionScope.showPositionList)==0}">
    该部门下没有职位
</c:if>

<c:if test="${fn:length(sessionScope.showPositionList)!=0}">
    <table>
        <tr>
            <th>职位id</th>
            <th>职位名称</th>
            <th>职位薪资</th>
            <th>所属部门</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showPositionList)-1}">
            <tr>
                <td>${sessionScope.showPositionList[i].id}</td>
                <td>${sessionScope.showPositionList[i].name}</td>
                <td>${sessionScope.showPositionList[i].salary}</td>
                <td>${sessionScope.showPositionList[i].department.name}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>

