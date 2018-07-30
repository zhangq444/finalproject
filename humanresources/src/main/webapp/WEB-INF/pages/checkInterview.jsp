<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/29
  Time: 14:08
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

    查看面试邀请<br>
    <br>
    <table>
        <tr>
            <th>招聘部门</th>
            <th>招聘岗位</th>
            <th>面试时间</th>
            <th>面试地址</th>
            <th>面试官</th>
            <th>面试状态</th>
            <th>参加</th>
            <th>拒绝</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.checkInterviewList)-1}">
            <tr>
                <td>${sessionScope.checkInterviewList[i].recruit.department.name}</td>
                <td>${sessionScope.checkInterviewList[i].recruit.position.name}</td>
                <td>${fnn:DateToString1(sessionScope.checkInterviewList[i].time)}</td>
                <td>${sessionScope.checkInterviewList[i].address}</td>
                <td>${sessionScope.checkInterviewList[i].employee.name}</td>
                <td>
                    <c:if test="${sessionScope.checkInterviewList[i].state==0}">
                        未选择
                    </c:if>
                    <c:if test="${sessionScope.checkInterviewList[i].state==-1}">
                        已拒绝
                    </c:if>
                    <c:if test="${sessionScope.checkInterviewList[i].state==1}">
                        参加
                    </c:if>
                </td>
                <td>
                    <c:if test="${sessionScope.checkInterviewList[i].state==0}">
                        <form action="enterInterview">
                            <input type="hidden" value="${sessionScope.checkInterviewList[i].id}" name="enterInterviewId">
                            <input type="submit" value="参加面试">
                        </form>
                    </c:if>
                </td>
                <td>
                    <c:if test="${sessionScope.checkInterviewList[i].state==0}">
                        <form action="refuseInterview">
                            <input type="hidden" value="${sessionScope.checkInterviewList[i].id}" name="refuseInterviewId">
                            <input type="submit" value="拒绝面试">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

    </table>
    <br>
    <a href="returnWelcome"><input type="button" value="返回主页"></a>


</body>
</html>

