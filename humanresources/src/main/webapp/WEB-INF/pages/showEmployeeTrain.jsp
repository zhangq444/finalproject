<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>

<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/1
  Time: 12:50
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
    <h2>查看员工培训</h2>
    <br>
    <table>
        <tr id="tr1">
            <th>培训id</th>
            <th>培训主题</th>
            <th>培训内容</th>
            <th>培训开始时间</th>
            <th>培训结束时间</th>
            <th>培训地点</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showEmployeeTrainList)-1}">
            <tr>
                <td>${sessionScope.showEmployeeTrainList[i].id}</td>
                <td>${sessionScope.showEmployeeTrainList[i].theme}</td>
                <td>${sessionScope.showEmployeeTrainList[i].content}</td>
                <td>${fnn:DateToString1(sessionScope.showEmployeeTrainList[i].begintime)}</td>
                <td>${fnn:DateToString1(sessionScope.showEmployeeTrainList[i].endtime)}</td>
                <td>${sessionScope.showEmployeeTrainList[i].address}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:forEach var="i" begin="1" end="${sessionScope.showEmployeeTrainListTotalPages}">
        <a href="checkEmployeeInfo?currentPage=${i}&checkInfo=${sessionScope.showEmployeeTrainListCheckInfo}
        &employeeInfoId=${sessionScope.showEmployeeTrainListEmployeeInfoId}">
            &nbsp;${i}&nbsp;
        </a>
    </c:forEach>
    <br>
    <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>


</div>




</body>
</html>

