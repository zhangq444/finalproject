<%@ taglib prefix="fnn" uri="/elFunction" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/4
  Time: 14:47
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
    <h2>欢迎查看个人信息</h2>
    <br>
    <table>
        <tr>
            <td>员工id:</td>
            <td>${sessionScope.loginEmployee.id}</td>
        </tr>
        <tr>
            <td>员工姓名:</td>
            <td>${sessionScope.loginEmployee.name}</td>
        </tr>
        <tr>
            <td>员工性别:</td>
            <td>${sessionScope.loginEmployee.gender}</td>
        </tr>
        <tr>
            <td>员工电话:</td>
            <td>${sessionScope.loginEmployee.phone}</td>
        </tr>
        <tr>
            <td>员工email:</td>
            <td>${sessionScope.loginEmployee.email}</td>
        </tr>
        <tr>
            <td>员工地址:</td>
            <td>${sessionScope.loginEmployee.address}</td>
        </tr>
        <tr>
            <td>员工身份证:</td>
            <td>${sessionScope.loginEmployee.idcard}</td>
        </tr>
        <tr>
            <td>员工生日:</td>
            <td>${fnn:DateToString(sessionScope.loginEmployee.birthday)}</td>
        </tr>
        <tr>
            <td>员工学校:</td>
            <td>${sessionScope.loginEmployee.school}</td>
        </tr>
        <tr>
            <td>员工专业:</td>
            <td>${sessionScope.loginEmployee.major}</td>
        </tr>
        <tr>
            <td>员工所属部门:</td>
            <td>${sessionScope.loginEmployee.department.name}</td>
        </tr>
        <tr>
            <td>员工所属职位:</td>
            <td>${sessionScope.loginEmployee.position.name}</td>
        </tr>
        <tr>
            <td>员工入职日期:</td>
            <td>${fnn:DateToString(sessionScope.loginEmployee.hiredate)}</td>
        </tr>
        <tr>
            <td>员工状态:</td>
            <td>
                <c:if test="${sessionScope.loginEmployee.state==0}">
                    试用期
                </c:if>
                <c:if test="${sessionScope.loginEmployee.state==1}">
                    正式
                </c:if>
                <c:if test="${sessionScope.loginEmployee.state==-1}">
                    离职
                </c:if>
            </td>
        </tr>
        <tr>
            <td>员工用户名:</td>
            <td>${sessionScope.loginEmployee.uname}</td>
        </tr>
        <tr>
            <td>员工密码:</td>
            <td>${sessionScope.loginEmployee.upassword}</td>
        </tr>
    </table>
    <br>
    <a href="modifyPersonInfo"><input type="button" value="修改个人信息"></a>
    <a href="checkSalary"><input type="button" value="查看个人薪资"></a>
    <a href="checkAttendance"><input type="button" value="查看考勤记录"></a>
    <a href="returnEmployeeWelcome"><input type="button" value="返回主页"></a>

<%--

    <br>
    <br>
    员工id:${sessionScope.loginEmployee.id}<br>
    员工姓名:${sessionScope.loginEmployee.name}<br>
    员工性别:${sessionScope.loginEmployee.gender}<br>
    员工电话:${sessionScope.loginEmployee.phone}<br>
    员工email:${sessionScope.loginEmployee.email}<br>
    员工地址:${sessionScope.loginEmployee.address}<br>
    员工身份证:${sessionScope.loginEmployee.idcard}<br>
    员工生日:${fnn:DateToString(sessionScope.loginEmployee.birthday)}<br>
    员工学校:${sessionScope.loginEmployee.school}<br>
    员工专业:${sessionScope.loginEmployee.major}<br>
    员工所属部门:${sessionScope.loginEmployee.department.name}<br>
    员工所属职位:${sessionScope.loginEmployee.position.name}<br>
    员工入职日期:${fnn:DateToString(sessionScope.loginEmployee.hiredate)}<br>
    员工状态:
    <c:if test="${sessionScope.loginEmployee.state==0}">
        试用期
    </c:if>
    <c:if test="${sessionScope.loginEmployee.state==1}">
        正式
    </c:if>
    <c:if test="${sessionScope.loginEmployee.state==-1}">
        离职
    </c:if>
    <br>
    员工用户名:${sessionScope.loginEmployee.uname}<br>
    员工密码:${sessionScope.loginEmployee.upassword}<br>
    <br>
    <a href="modifyPersonInfo"><input type="button" value="修改个人信息"></a>
    <a href="checkSalary"><input type="button" value="查看个人薪资"></a>
    <a href="checkAttendance"><input type="button" value="查看考勤记录"></a>
    <a href="returnEmployeeWelcome"><input type="button" value="返回主页"></a>
--%>

</div>

</body>
</html>

