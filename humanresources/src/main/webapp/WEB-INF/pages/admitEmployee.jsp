<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/29
  Time: 15:40
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

<table>
    <tr>
        <th>收取简历信息id</th>
        <th>招聘部门</th>
        <th>招聘职位</th>
        <th>应聘人姓名</th>
        <th>录取</th>
        <th>不录取</th>
    </tr>
    <c:forEach var="i" begin="0" end="${fn:length(sessionScope.admitInterviewList)-1}">
        <tr>
            <td>${sessionScope.admitInterviewList[i].id}</td>
            <td>${sessionScope.admitInterviewList[i].recruit.department.name}</td>
            <td>${sessionScope.admitInterviewList[i].recruit.position.name}</td>
            <td>${sessionScope.admitInterviewList[i].resume.name}</td>
            <td>
                <form action="admitEmployee1">
                    <input type="hidden" value="${sessionScope.admitInterviewList[i].id}" name="admitEmployeeId">
                    <input type="submit" value="录取">
                </form>
            </td>
            <td>
                <form action="">
                    <input type="hidden" value="${sessionScope.admitInterviewList[i].id}" name="passEmployeeId">
                    <input type="submit" value="不录取">
                </form>
            </td>
        </tr>

    </c:forEach>





</table>



</body>
</html>

