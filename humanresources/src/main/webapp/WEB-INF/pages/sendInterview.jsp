<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/29
  Time: 10:07
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

    <form action="sendInterview1">
        面试时间：<input type="datetime-local" name="interviewTime"><br>
        面试地点：<input type="text" name="interviewAddress"><br>
        选择面试官：
        <select name="interviewEmpId">
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.employeeList)-1}">
                <option value="${sessionScope.employeeList[i].id}">${sessionScope.employeeList[i].name}</option>
            </c:forEach>
        </select>
        <br>
        <input type="submit" value="生成面试邀请">

    </form>



</body>
</html>

