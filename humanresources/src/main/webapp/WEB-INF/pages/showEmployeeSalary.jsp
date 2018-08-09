<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/1
  Time: 12:48
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
    <h2>显示员工薪资</h2>
    <br>
    <c:if test="${fn:length(sessionScope.showEmployeeSalaryList)==0}">
        未查询到相关薪资记录
    </c:if>

    <c:if test="${fn:length(sessionScope.showEmployeeSalaryList)!=0}">
        <table>
            <tr id="tr1">
                <th>薪资ID</th>
                <th>员工姓名</th>
                <th>基本工资</th>
                <th>绩效工资</th>
                <th>加班工资</th>
                <th>奖惩总额</th>
                <th>社保</th>
                <th>实际工资</th>
                <th>核算时间</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showEmployeeSalaryList)-1}">
                <tr>
                    <td>${sessionScope.showEmployeeSalaryList[i].id}</td>
                    <td>${sessionScope.showEmployeeSalaryList[i].employee.name}</td>
                    <td>${sessionScope.showEmployeeSalaryList[i].basesalary}</td>
                    <td>${sessionScope.showEmployeeSalaryList[i].performance}</td>
                    <td>${sessionScope.showEmployeeSalaryList[i].overtime}</td>
                    <td>${sessionScope.showEmployeeSalaryList[i].rewards}</td>
                    <td>${sessionScope.showEmployeeSalaryList[i].social}</td>
                    <td>${sessionScope.showEmployeeSalaryList[i].realsalary}</td>
                    <td>${fnn:DateToString(sessionScope.showEmployeeSalaryList[i].time)}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:forEach var="i" begin="1" end="${sessionScope.showEmployeeSalaryListTotalPages}">
            <a href="checkEmployeeInfo?currentPage=${i}&checkInfo=${sessionScope.showEmployeeSalaryListCheckInfo}
            &employeeInfoId=${sessionScope.showEmployeeSalaryListEmployeeInfoId}">
                &nbsp;${i}&nbsp;
            </a>
        </c:forEach>
        <br>
        <br>
        <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>

    </c:if>

</div>



</body>
</html>

