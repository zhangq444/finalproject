<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/5
  Time: 13:15
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
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
        $(function () {
            $("#selectDep").change(function () {
                var sel=$("#selectDep option:selected").val();
                $.getJSON("addRecruit1","key="+sel,function (obj) {
                    $("#selectPosition").empty();
                    $(obj).each(function () {
                        $("#selectPosition").append("<option value="+this.id+">"+this.name+"</option>")
                    })
                })
            })
        })
    </script>
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
    <h2>请选择要查询员工的条件</h2>
    <br>
    <form action="checkEmployeeAttendance1">
        部门和职位：
        <select id="selectDep" name="selectDep">
            <option value="0">请选择</option>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.EmpDepartmentList)-1}">
                <option value="${sessionScope.EmpDepartmentList[i].id}" class="option1">${sessionScope.EmpDepartmentList[i].name}</option>
            </c:forEach>
        </select>
        <select id="selectPosition" name="selectPosition">
            <option value="0">请选择</option>

        </select>
        <br>
        <input type="submit" value="查询员工">
        <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>
    </form>
    <br>
    ${sessionScope.checkEmployeeAttendanceError}<br>
    <br>
    <c:if test="${fn:length(sessionScope.checkEmployeeAttendanceList)==0}">
        未查询到相关员工
    </c:if>

    <c:if test="${fn:length(sessionScope.checkEmployeeAttendanceList)!=0}">
        <table>
            <tr id="tr1">
                <th>员工id</th>
                <th>员工姓名</th>
                <th>员工所属部门</th>
                <th>员工所属职位</th>
                <th>查看考勤</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.checkEmployeeAttendanceList)-1}">
                <tr>
                    <td>${sessionScope.checkEmployeeAttendanceList[i].id}</td>
                    <td>${sessionScope.checkEmployeeAttendanceList[i].name}</td>
                    <td>${sessionScope.checkEmployeeAttendanceList[i].department.name}</td>
                    <td>${sessionScope.checkEmployeeAttendanceList[i].position.name}</td>
                    <td>
                        <form action="checkEmployeeAttendanceInfo">
                            <input type="hidden" value="${sessionScope.checkEmployeeAttendanceList[i].id}" name="checkEmployeeAttendanceId">
                            <input type="submit" value="查看员工考勤">
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <br>
        <c:forEach var="i" begin="1" end="${sessionScope.checkEmployeeAttendanceTotalPages}">
            <a href="checkEmployeeAttendance1?currentPage=${i}&selectDep=${sessionScope.checkEmployeeAttendanceList[0].department.id}&selectPosition=${sessionScope.checkEmployeeAttendanceList[0].position.id}">&nbsp;${i}&nbsp;</a>
        </c:forEach>

    </c:if>


</div>




</body>
</html>

