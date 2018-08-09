<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/5
  Time: 14:50
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
    <c:if test="${fn:length(sessionScope.showCheckEmployeeAttendanceList)==0}">
        没有查到相关考勤记录
    </c:if>

    <c:if test="${fn:length(sessionScope.showCheckEmployeeAttendanceList)!=0}">
        <table>
            <tr id="tr1">
                <th>考勤id</th>
                <th>员工姓名</th>
                <th>考勤日期</th>
                <th>上班打卡</th>
                <th>下班打卡</th>
                <th>加班打卡</th>
                <th>考勤状态</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showCheckEmployeeAttendanceList)-1}">
                <tr>
                    <td>${sessionScope.showCheckEmployeeAttendanceList[i].id}</td>
                    <td>${sessionScope.showCheckEmployeeAttendanceList[i].employee.name}</td>
                    <td>${fnn:DateToString(sessionScope.showCheckEmployeeAttendanceList[i].date)}</td>
                    <td>${fnn:DateToString1(sessionScope.showCheckEmployeeAttendanceList[i].punchin)}</td>
                    <td>${fnn:DateToString1(sessionScope.showCheckEmployeeAttendanceList[i].punchout)}</td>
                    <td>${fnn:DateToString1(sessionScope.showCheckEmployeeAttendanceList[i].overtime)}</td>
                    <td>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==0}">
                            正常上班
                        </c:if>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==1}">
                            迟到
                        </c:if>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==2}">
                            早退
                        </c:if>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==3}">
                            迟到和早退
                        </c:if>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==4}">
                            旷工
                        </c:if>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==5}">
                            正常上班和加班
                        </c:if>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==6}">
                            迟到和加班
                        </c:if>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==7}">
                            早退和加班
                        </c:if>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==8}">
                            迟到早退和加班
                        </c:if>
                        <c:if test="${sessionScope.showCheckEmployeeAttendanceList[i].state==9}">
                            旷工和加班
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:forEach var="i" begin="1" end="${sessionScope.showCheckEmployeeAttendanceListTotalPages}">
            <a href="checkEmployeeAttendanceInfo?currentPage=${i}&checkEmployeeAttendanceId=${sessionScope.showCheckEmployeeAttendanceList[0].employee.id}">&nbsp;${i}&nbsp;</a>
        </c:forEach>
    </c:if>
    <br>
    <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>

</div>


</body>
</html>

