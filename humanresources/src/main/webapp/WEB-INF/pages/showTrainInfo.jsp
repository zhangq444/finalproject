<%@ taglib prefix="fnn" uri="/elFunction" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/2
  Time: 22:49
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

    显示培训详细信息<br>
    <br>
    <table>
        <tr>
            <th>培训id</th>
            <th>培训主题</th>
            <th>培训内容</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>培训地点</th>
            <th>发布时间</th>
        </tr>
        <tr>
            <td>${sessionScope.showTrainInfomation.id}</td>
            <td>${sessionScope.showTrainInfomation.theme}</td>
            <td>${sessionScope.showTrainInfomation.content}</td>
            <td>${fnn:DateToString1(sessionScope.showTrainInfomation.begintime)}</td>
            <td>${fnn:DateToString1(sessionScope.showTrainInfomation.endtime)}</td>
            <td>${sessionScope.showTrainInfomation.address}</td>
            <td>
                <c:if test="${sessionScope.showTrainInfomation.state==0}">
                    尚未发布
                </c:if>
                <c:if test="${sessionScope.showTrainInfomation.state==1}">
                    ${fnn:DateToString1(sessionScope.showTrainInfomation.releasetime)}
                </c:if>
            </td>
        </tr>
    </table>
    <br>
    <br>
    针对员工：<br>
    <table>
        <tr>
            <th>员工id</th>
            <th>员工姓名</th>
            <th>员工所属部门</th>
            <th>员工所属职位</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(showTrainInfomationEmployeeList)-1}">
            <tr>
                <td>${sessionScope.showTrainInfomationEmployeeList[i].id}</td>
                <td>${sessionScope.showTrainInfomationEmployeeList[i].name}</td>
                <td>${sessionScope.showTrainInfomationEmployeeList[i].department.name}</td>
                <td>${sessionScope.showTrainInfomationEmployeeList[i].position.name}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <br>
    <c:if test="${sessionScope.showTrainInfomation.state==0}">
        <form action="modifyTrain">
            <input type="hidden" value="${sessionScope.showTrainInfomation.id}" name="modifyTrainId">
            <input type="submit" value="进行修改">
        </form>
    </c:if>
    <c:if test="${sessionScope.showTrainInfomation.state!=0}">
        该培训已经发布，无法修改
    </c:if>




</body>
</html>

