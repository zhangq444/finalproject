<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/4
  Time: 22:56
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

    欢迎来到员工工资管理页面<br>
    <br>
    <a href="settleSalary"><input type="button" value="工资结算"></a>(说明：每月工资仅能结算一次，结算上月工资)<br>
    <a href="reconsiderSalary"><input type="button" value="员工薪资复议"></a>
    <a href="returnManagerWelcome"><input type="button" value="返回主页"></a><br>
    <br>
    ${sessionScope.settleSalaryError}<br>
    <br>
    <c:if test="${fn:length(sessionScope.reconsiderSalaryList)==0}">
        未查询到复议的薪资
    </c:if>

    <c:if test="${fn:length(sessionScope.reconsiderSalaryList)!=0}">
        <table>
            <tr>
                <th>工资id</th>
                <th>员工姓名</th>
                <th>基本薪资</th>
                <th>绩效</th>
                <th>加班工资</th>
                <th>奖惩</th>
                <th>社保</th>
                <th>实际工资</th>
                <th>工资状态</th>
                <th>工资结算时间</th>
                <th>完成复议</th>
                <th>添加处理结果</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.reconsiderSalaryList)-1}">
                <tr>
                    <td>${sessionScope.reconsiderSalaryList[i].id}</td>
                    <td>${sessionScope.reconsiderSalaryList[i].employee.name}</td>
                    <td>${sessionScope.reconsiderSalaryList[i].basesalary}</td>
                    <td>${sessionScope.reconsiderSalaryList[i].performance}</td>
                    <td>${sessionScope.reconsiderSalaryList[i].overtime}</td>
                    <td>${sessionScope.reconsiderSalaryList[i].rewards}</td>
                    <td>${sessionScope.reconsiderSalaryList[i].social}</td>
                    <td>${sessionScope.reconsiderSalaryList[i].realsalary}</td>
                    <td>
                        <c:if test="${sessionScope.reconsiderSalaryList[i].state==1}">
                            申请复议
                        </c:if>
                        <c:if test="${sessionScope.reconsiderSalaryList[i].state==2}">
                            复议结束
                        </c:if>
                    </td>
                    <td>${fnn:DateToString(sessionScope.reconsiderSalaryList[i].time)}</td>
                    <td>
                        <c:if test="${sessionScope.reconsiderSalaryList[i].state==1}">
                            <form action="finishReconsiderSalary">
                                <input type="hidden" value="${sessionScope.reconsiderSalaryList[i].id}" name="finishReconsiderSalaryId">
                                <input type="submit" value="完成工资复议">
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <form action="reconsiderAddRewards">
                            <input type="hidden" value="${sessionScope.reconsiderSalaryList[i].employee.id}" name="addRewardsEMPId">
                            <input type="submit" value="添加处理结果">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
        <c:forEach var="i" begin="1" end="${sessionScope.reconsiderSalaryListTotalPages}">
            <a href="reconsiderSalary?currentPage=${i}">&nbsp;${i}&nbsp;</a>
        </c:forEach>

    </c:if>

</body>
</html>

