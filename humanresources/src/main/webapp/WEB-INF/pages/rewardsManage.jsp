<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/5
  Time: 15:28
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

    欢迎来到奖惩管理界面<br>
    <br>
    请选择要查询员工的条件<br>
    <form action="rewardsManage1">
        部门和职位：
        <select id="selectDep" name="selectDep">
            <option value="0">请选择</option>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.rewardsManageDepartmentList)-1}">
                <option value="${sessionScope.rewardsManageDepartmentList[i].id}" class="option1">${sessionScope.rewardsManageDepartmentList[i].name}</option>
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
    ${sessionScope.rewardsManageError}<br>

    <c:if test="${fn:length(sessionScope.rewardsManageEmployeeList)==0}">
        未查询到相关员工
    </c:if>

    <c:if test="${fn:length(sessionScope.rewardsManageEmployeeList)!=0}">
        <table>
            <tr>
                <th>员工id</th>
                <th>员工姓名</th>
                <th>员工所属部门</th>
                <th>员工所属职位</th>
                <th>查看奖惩</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.rewardsManageEmployeeList)-1}">
                <tr>
                    <td>${sessionScope.rewardsManageEmployeeList[i].id}</td>
                    <td>${sessionScope.rewardsManageEmployeeList[i].name}</td>
                    <td>${sessionScope.rewardsManageEmployeeList[i].department.name}</td>
                    <td>${sessionScope.rewardsManageEmployeeList[i].position.name}</td>
                    <td>
                        <form action="checkEmployeeRewards">
                            <input type="hidden" value="${sessionScope.rewardsManageEmployeeList[i].id}" name="checkEmployeeRewardId">
                            <input type="submit" value="查看员工奖惩">
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <br>
        <c:forEach var="i" begin="1" end="${sessionScope.rewardsManageEmployeeListTotalPages}">
            <a href="rewardsManage1?currentPage=${i}&selectDep=${sessionScope.rewardsManageEmployeeList[0].department.id}&selectPosition=${sessionScope.rewardsManageEmployeeList[0].position.id}">&nbsp;${i}&nbsp;</a>
        </c:forEach>

    </c:if>






</body>
</html>

