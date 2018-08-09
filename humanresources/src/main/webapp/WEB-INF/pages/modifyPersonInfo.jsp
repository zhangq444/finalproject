<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/4
  Time: 15:14
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
    <h2>修改个人信息页面</h2>
    <br>
    <form action="modifyPersonInfo1">
        <table>
            <tr>
                <td>员工电话:</td>
                <td><input type="text" value="${sessionScope.loginEmployee.phone}" name="phone"></td>
            </tr>
            <tr>
                <td>员工email:</td>
                <td><input type="text" value="${sessionScope.loginEmployee.email}" name="email"></td>
            </tr>
            <tr>
                <td>员工地址:</td>
                <td><input type="text" value="${sessionScope.loginEmployee.address}" name="address"></td>
            </tr>
            <tr>
                <td>员工身份证:</td>
                <td><input type="text" value="${sessionScope.loginEmployee.idcard}" name="idcard"></td>
            </tr>
            <tr>
                <td>员工生日:</td>
                <td><input type="date" name="birthday"></td>
            </tr>
            <tr>
                <td>员工学校:</td>
                <td><input type="text" value="${sessionScope.loginEmployee.school}" name="school"></td>
            </tr>
            <tr>
                <td>员工专业:</td>
                <td><input type="text" value="${sessionScope.loginEmployee.major}" name="major"></td>
            </tr>
        </table>
        <input type="submit" value="提交修改"><a href="returnEmployeeWelcome"><input type="button" value="返回主页"></a>
    </form>





<%--
    修改个人信息页面<br>
    <br>
    <form action="modifyPersonInfo1">
        员工电话:<input type="text" value="${sessionScope.loginEmployee.phone}" name="phone"><br>
        员工email:<input type="text" value="${sessionScope.loginEmployee.email}" name="email"><br>
        员工地址:<input type="text" value="${sessionScope.loginEmployee.address}" name="address"><br>
        员工身份证:<input type="text" value="${sessionScope.loginEmployee.idcard}" name="idcard"><br>
        员工生日:<input type="date" name="birthday"><br>
        员工学校:<input type="text" value="${sessionScope.loginEmployee.school}" name="school"><br>
        员工专业:<input type="text" value="${sessionScope.loginEmployee.major}" name="major"><br>
        <input type="submit" value="提交修改"><a href="returnEmployeeWelcome"><input type="button" value="返回主页"></a>
    </form>--%>

</div>

</body>
</html>

