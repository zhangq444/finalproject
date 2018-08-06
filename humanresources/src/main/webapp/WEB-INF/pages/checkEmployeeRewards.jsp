<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/5
  Time: 16:54
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

    查看员工的奖惩记录<br>
    <br>
    <c:if test="${fn:length(checkEmployeeRewardsList)==0}">
        未查询到相关奖惩信息
    </c:if>

    <c:if test="${fn:length(checkEmployeeRewardsList)!=0}">
        <table>
            <tr>
                <th>奖惩id</th>
                <th>奖惩金额</th>
                <th>奖惩生成时间</th>
                <th>员工姓名</th>
                <th>奖惩原因</th>
                <th>修改</th>
                <th>删除</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(checkEmployeeRewardsList)-1}">
                <tr>
                    <td>${sessionScope.checkEmployeeRewardsList[i].id}</td>
                    <td>${sessionScope.checkEmployeeRewardsList[i].money}</td>
                    <td>${fnn:DateToString1(sessionScope.checkEmployeeRewardsList[i].time)}</td>
                    <td>${sessionScope.checkEmployeeRewardsList[i].employee.name}</td>
                    <td>${sessionScope.checkEmployeeRewardsList[i].explain}</td>
                    <td>
                        <form action="modifyRewards">
                            <input type="hidden" value="${sessionScope.checkEmployeeRewardsList[i].id}" name="modifyRewardsId">
                            <input type="submit" value="修改">
                        </form>
                    </td>
                    <td>
                        <form action="deleteRewards">
                            <input type="hidden" value="${sessionScope.checkEmployeeRewardsList[i].id}" name="deleteRewardsId">
                            <input type="submit" value="删除">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:forEach var="i" begin="1" end="${sessionScope.checkEmployeeRewardsListTotalPages}">
            <a href="checkEmployeeRewards?currentPage=${i}&checkEmployeeRewardId=${sessionScope.checkEmployeeRewardId}">&nbsp;${i}&nbsp;</a>
        </c:forEach>
    </c:if>
    <br>
    <form action="addNewRewards">
        <input type="hidden" value="${sessionScope.checkEmployeeRewardId}" name="addNewRewardsEmpId">
        <input type="submit" value="增加新的奖惩">
        <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>
    </form>



</body>
</html>

