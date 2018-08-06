<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/4
  Time: 16:43
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
</head>
<body>

<form action="checkEmployeeDetail1">
    请选择部门和职位：
    <select id="selectDep" name="selectDep">
        <option value="0">请选择</option>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.checkEmployeeDepartmentList)-1}">
            <option value="${sessionScope.checkEmployeeDepartmentList[i].id}" class="option1">${sessionScope.checkEmployeeDepartmentList[i].name}</option>
        </c:forEach>
    </select>
    <select id="selectPosition" name="selectPosition">
        <option value="0">请选择</option>

    </select>
    <input type="submit" value="查询">
</form>
<br>
<br>
${sessionScope.checkEmployeeDetailError}<br>

<c:if test="${fn:length(sessionScope.checkEmployeeDetailList)==0}">
    未查询到该职位部门下的员工<br>
    <a href="/returnEmployeeWelcome"><input type="button" value="返回主页"></a>
</c:if>

<c:if test="${fn:length(sessionScope.checkEmployeeDetailList)!=0}">
    <table>
        <tr>
            <th>员工id</th>
            <th>员工姓名</th>
            <th>员工性别</th>
            <th>员工手机</th>
            <th>员工邮箱</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.checkEmployeeDetailList)-1}">
            <tr>
                <td>${sessionScope.checkEmployeeDetailList[i].id}</td>
                <td>${sessionScope.checkEmployeeDetailList[i].name}</td>
                <td>${sessionScope.checkEmployeeDetailList[i].gender}</td>
                <td>${sessionScope.checkEmployeeDetailList[i].phone}</td>
                <td>${sessionScope.checkEmployeeDetailList[i].email}</td>
            </tr>
        </c:forEach>
    </table><br>
    <a href="/returnEmployeeWelcome"><input type="button" value="返回主页"></a>

</c:if>


</body>
</html>

