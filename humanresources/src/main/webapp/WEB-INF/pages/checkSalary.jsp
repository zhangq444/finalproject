<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/5
  Time: 9:49
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
    <style>
        div{
            width: 1000px;
            margin-left: auto;
            margin-right: auto;
        }
        #tr1{
            background-color: lightblue;
        }
        th{
            width: 200px;
        }
        td{
            width: 200px;
        }
        form{
            margin: 0px;
            padding: 0px;
        }
        a{
            text-decoration: none;
        }

    </style>
</head>
<body>

<jsp:include page="head.jsp"></jsp:include>

<div>
    <h2>欢迎来到工资查询页面</h2>
    <br>
    <a href="returnEmployeeWelcome"><input type="button" value="返回主页"></a><br>
    <br>
    <c:if test="${fn:length(sessionScope.checkSalaryList)==0}">
        未查询到工资信息
    </c:if>

    <c:if test="${fn:length(sessionScope.checkSalaryList)!=0}">
        <table>
            <tr id="tr1">
                <th>薪资id</th>
                <th>员工姓名</th>
                <th>基本工资</th>
                <th>绩效工资</th>
                <th>加班工资</th>
                <th>奖惩金额</th>
                <th>社保</th>
                <th>实际工资</th>
                <th>工资状态</th>
                <th>工资结算时间</th>
                <th>申请复议</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.checkSalaryList)-1}">
                <tr>
                    <td>${sessionScope.checkSalaryList[i].id}</td>
                    <td>${sessionScope.checkSalaryList[i].employee.name}</td>
                    <td>${sessionScope.checkSalaryList[i].basesalary}</td>
                    <td>${sessionScope.checkSalaryList[i].performance}</td>
                    <td>${sessionScope.checkSalaryList[i].overtime}</td>
                    <td>${sessionScope.checkSalaryList[i].rewards}</td>
                    <td>${sessionScope.checkSalaryList[i].social}</td>
                    <td>${sessionScope.checkSalaryList[i].realsalary}</td>
                    <td>
                        <c:if test="${sessionScope.checkSalaryList[i].state==0}">
                            正常
                        </c:if>
                        <c:if test="${sessionScope.checkSalaryList[i].state==1}">
                            申请复议
                        </c:if>
                        <c:if test="${sessionScope.checkSalaryList[i].state==2}">
                            复议结束
                        </c:if>
                    </td>
                    <td>${fnn:DateToString(sessionScope.checkSalaryList[i].time)}</td>
                    <td>
                        <form action="reconsider">
                            <input type="hidden" value="${sessionScope.checkSalaryList[i].id}" name="reconsiderId">
                            <input type="submit" value="申请复议">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
        <c:forEach var="i" begin="1" end="${sessionScope.checkSalaryTotalPages}">
            <a href="checkSalary?currentPage=${i}">&nbsp;${i}&nbsp;</a>
        </c:forEach>

    </c:if>

</div>


</body>
</html>

