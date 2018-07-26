<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/26
  Time: 15:30
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

<c:if test="${fn:length(sessionScope.showResumeList)==0}">
    <c:out value="未查询到相关简历"></c:out>
</c:if>
<br>
<c:if test="${fn:length(sessionScope.showResumeList)>0}">
    <table>
        <tr>
            <td>简历ID</td>
            <td>简历名称</td>
            <td>简历详情</td>

        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showResumeList)-1}">
            <tr>
                <td>${sessionScope.showResumeList[i].id}</td>
                <td>${sessionScope.showResumeList[i].resumename}</td>
                <td>
                    <form>
                        <input type="submit" value="简历详情">
                    </form>
                </td>
            </tr>

        </c:forEach>
    </table>
</c:if>



</body>
</html>

