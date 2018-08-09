<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/31
  Time: 21:32
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
    <form action="selectEmployee">
        部门职位：&nbsp;
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
        在职情况：&nbsp;
        <select name="selectOnJob">
            <option value="0">请选择</option>
            <option value="1">在职</option>
            <option value="-1">离职</option>
        </select>
        &nbsp;
        (说明：离职员工不分部门与职位)
        <br>
        <input type="submit" value="根据部门查询" name="onDep">
        <input type="submit" value="根据部门职位查询" name="onDepPos">
        <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>
    </form>
    <br>
    ${sessionScope.selectEmployeeError}${sessionScope.showEmployeeInfoError}${sessionScope.comfirmationEmployeeError}<br>

    <br>

    <c:if test="${fn:length(sessionScope.showEmployeeList)==0}">
        未查询到相关职员
    </c:if>

    <c:if test="${fn:length(sessionScope.showEmployeeList)!=0}">
        <table>
            <tr id="tr1">
                <th>员工id</th>
                <th>员工姓名</th>
                <th>员工所属部门</th>
                <th>员工职位</th>
                <th>员工状态</th>
                <th>选择</th>
                <th>查看</th>
                <th>换岗</th>
                <th>转正</th>
                <th>离职</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showEmployeeList)-1}">
                <tr>
                    <td>${sessionScope.showEmployeeList[i].id}</td>
                    <td>${sessionScope.showEmployeeList[i].name}</td>
                    <td>${sessionScope.showEmployeeList[i].department.name}</td>
                    <td>${sessionScope.showEmployeeList[i].position.name}</td>
                    <td>
                        <c:if test="${sessionScope.showEmployeeList[i].state==-1}">
                            离职
                        </c:if>
                        <c:if test="${sessionScope.showEmployeeList[i].state==0}">
                            试用期
                        </c:if>
                        <c:if test="${sessionScope.showEmployeeList[i].state==1}">
                            正式
                        </c:if>
                    </td>
                    <form action="checkEmployeeInfo">
                        <td>
                            <select name="checkInfo">
                                <option value="1">基本信息</option>
                                <option value="2">薪资</option>
                                <option value="3">培训</option>
                            </select>
                        </td>
                        <td>
                            <input type="hidden" value="${sessionScope.showEmployeeList[i].id}" name="employeeInfoId">
                            <input type="submit" value="查看">
                        </td>
                    </form>
                    <td>
                        <form action="changePosition">
                            <input type="hidden" value="${sessionScope.showEmployeeList[i].id}" name="changePositionId">
                            <input type="submit" value="换岗">
                        </form>
                    </td>
                    <td>
                        <form action="comfirmationEmployee">
                            <input type="hidden" value="${sessionScope.showEmployeeList[i].id}" name="comfirmEmployeeId">
                            <input type="submit" value="转正">
                        </form>
                    </td>
                    <td>
                        <form action="departureEmployee">
                            <input type="hidden" value="${sessionScope.showEmployeeList[i].id}" name="departureId">
                            <input type="submit" value="离职">
                        </form>
                    </td>
                </tr>

            </c:forEach>
        </table>
        <c:forEach var="i" begin="1" end="${sessionScope.showEmployeeListTotalPages}">
            <a href="selectEmployee?currentPage=${i}&selectDep=${sessionScope.showEmployeeListSelectDep}
            &selectPosition=${sessionScope.showEmployeeListSelectPosition}&selectOnJob=${sessionScope.showEmployeeListSelectOnJob}
            &onDep=${sessionScope.showEmployeeListOnDep}&onDepPos=${sessionScope.showEmployeeListOnDepPos}">
                &nbsp;${i}&nbsp;
            </a>
        </c:forEach>

    </c:if>

</div>




</body>
</html>

