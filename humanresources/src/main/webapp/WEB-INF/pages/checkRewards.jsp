<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/4
  Time: 20:51
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

    欢迎查看个人奖惩记录<br>
    <br>
    <form action="checkRewards1">
        请选择查询时间：<input type="date" name="searchDate">(说明：将按照所选日期的年月进行查询)<br>
        <input type="submit" value="开始查询">&nbsp;<a href="returnEmployeeWelcome"><input type="button" value="返回主页"></a>
    </form>
    <br>
    <br>
    <c:if test="${fn:length(sessionScope.checkRewardsList)==0}">
        未查询到相关奖惩记录
    </c:if>
    <c:if test="${fn:length(sessionScope.checkRewardsList)!=0}">
        <table>
            <tr>
                <th>奖惩id</th>
                <th>奖惩金额</th>
                <th>奖惩时间</th>
                <th>奖惩原因</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.checkRewardsList)-1}">
                <tr>
                    <td>${sessionScope.checkRewardsList[i].id}</td>
                    <td>${sessionScope.checkRewardsList[i].money}</td>
                    <td>${fnn:DateToString1(sessionScope.checkRewardsList[i].time)}</td>
                    <td>${sessionScope.checkRewardsList[i].explain}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:forEach var="i" begin="1" end="${sessionScope.checkRewardsTotalPages}">
            <a href="checkRewards1?currentPage=${i}&searchDate=${fnn:DateToString2(sessionScope.checkRewardsList[0].time)}">&nbsp;${i}&nbsp;</a>
        </c:forEach>
    </c:if>









</body>
</html>

