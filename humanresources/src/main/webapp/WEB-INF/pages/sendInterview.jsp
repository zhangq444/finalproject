<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/29
  Time: 10:07
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
        td{
            width: 150px;
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
    <h2>添加面试邀请</h2>
    <br>
    <form action="sendInterview1">
        <table>
            <tr>
                <td>面试时间：</td>
                <td><input type="datetime-local" name="interviewTime"></td>
            </tr>
            <tr>
                <td>面试地点：</td>
                <td><input type="text" name="interviewAddress"></td>
            </tr>
            <tr>
                <td>选择面试官：</td>
                <td>
                    <select name="interviewEmpId">
                        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.employeeList)-1}">
                            <option value="${sessionScope.employeeList[i].id}">${sessionScope.employeeList[i].name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <br>
        <input type="submit" value="生成面试邀请">
      </form>

</div>

<%--

    <form action="sendInterview1">
        面试时间：<input type="datetime-local" name="interviewTime"><br>
        面试地点：<input type="text" name="interviewAddress"><br>
        选择面试官：
        <select name="interviewEmpId">
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.employeeList)-1}">
                <option value="${sessionScope.employeeList[i].id}">${sessionScope.employeeList[i].name}</option>
            </c:forEach>
        </select>
        <br>
        <input type="submit" value="生成面试邀请">

    </form>

--%>


</body>
</html>

