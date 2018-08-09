<%@ taglib prefix="fnn" uri="/elFunction" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/1
  Time: 12:24
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
    <h2>显示员工详细信息</h2>
    <br>
    <table>
        <tr>
            <td>员工id:</td>
            <td>${sessionScope.showEmployeeInfo.id}</td>
        </tr>
        <tr>
            <td>员工姓名:</td>
            <td>${sessionScope.showEmployeeInfo.name}</td>
        </tr>
        <tr>
            <td>员工性别:</td>
            <td>${sessionScope.showEmployeeInfo.gender}</td>
        </tr>
        <tr>
            <td>员工电话:</td>
            <td>${sessionScope.showEmployeeInfo.phone}</td>
        </tr>
        <tr>
            <td>员工email:</td>
            <td>${sessionScope.showEmployeeInfo.email}</td>
        </tr>
        <tr>
            <td>员工地址:</td>
            <td>${sessionScope.showEmployeeInfo.address}</td>
        </tr>
        <tr>
            <td>员工身份证:</td>
            <td>${sessionScope.showEmployeeInfo.idcard}</td>
        </tr>
        <tr>
            <td>员工生日:</td>
            <td>${fnn:DateToString(sessionScope.showEmployeeInfo.birthday)}</td>
        </tr>
        <tr>
            <td>员工学校:</td>
            <td>${sessionScope.showEmployeeInfo.school}</td>
        </tr>
        <tr>
            <td>员工专业:</td>
            <td>${sessionScope.showEmployeeInfo.major}</td>
        </tr>
        <tr>
            <td>员工教育经历:</td>
            <td>${sessionScope.showEmployeeInfo.education}</td>
        </tr>
        <c:if test="${sessionScope.showEmployeeInfo.department!=null}">
            <tr>
                <td>员工所属部门:</td>
                <td>${sessionScope.showEmployeeInfo.department.name}</td>
            </tr>
        </c:if>
        <c:if test="${sessionScope.showEmployeeInfo.position!=null}">
            <tr>
                <td>员工职位:</td>
                <td>${sessionScope.showEmployeeInfo.position.name}</td>
            </tr>
        </c:if>
        <tr>
            <td>员工入职时间:</td>
            <td>${fnn:DateToString(sessionScope.showEmployeeInfo.hiredate)}</td>
        </tr>
        <tr>
            <td>员工状态:</td>
            <td>
                <c:if test="${sessionScope.showEmployeeInfo.state==-1}">
                    离职
                </c:if>
                <c:if test="${sessionScope.showEmployeeInfo.state==0}">
                    试用期
                </c:if>
                <c:if test="${sessionScope.showEmployeeInfo.state==1}">
                    正式
                </c:if>
            </td>
        </tr>
        <tr>
            <td>员工用户名:</td>
            <td>${sessionScope.showEmployeeInfo.uname}</td>
        </tr>
        <tr>
            <td>员工密码:</td>
            <td>${sessionScope.showEmployeeInfo.upassword}</td>
        </tr>
    </table>
    <br>
    <a href="returnEmployeeManage"><input type="button" value="返回查询页面"></a>


</div>

<%--

    显示用户详细信息：<br>
    员工id:${sessionScope.showEmployeeInfo.id}<br>
    员工姓名:${sessionScope.showEmployeeInfo.name}<br>
    员工性别:${sessionScope.showEmployeeInfo.gender}<br>
    员工电话:${sessionScope.showEmployeeInfo.phone}<br>
    员工email:${sessionScope.showEmployeeInfo.email}<br>
    员工地址:${sessionScope.showEmployeeInfo.address}<br>
    员工身份证:${sessionScope.showEmployeeInfo.idcard}<br>
    员工生日:${fnn:DateToString(sessionScope.showEmployeeInfo.birthday)}<br>
    &lt;%&ndash;员工生日:${sessionScope.showEmployeeInfo.birthday}<br>&ndash;%&gt;
    员工学校:${sessionScope.showEmployeeInfo.school}<br>
    员工专业:${sessionScope.showEmployeeInfo.major}<br>
    员工教育经历:${sessionScope.showEmployeeInfo.education}<br>
    <c:if test="${sessionScope.showEmployeeInfo.department==null}">

    </c:if>
    <c:if test="${sessionScope.showEmployeeInfo.department!=null}">
        员工所属部门:${sessionScope.showEmployeeInfo.department.name}<br>
    </c:if>
    <c:if test="${sessionScope.showEmployeeInfo.position==null}">

    </c:if>
    <c:if test="${sessionScope.showEmployeeInfo.position!=null}">
        员工职位:${sessionScope.showEmployeeInfo.position.name}<br>
    </c:if>

    员工入职时间:${fnn:DateToString(sessionScope.showEmployeeInfo.hiredate)}<br>
    员工状态:${sessionScope.showEmployeeInfo.state}<br>
    员工用户名:${sessionScope.showEmployeeInfo.uname}<br>
    员工密码:${sessionScope.showEmployeeInfo.upassword}<br>
    <br>
    <a href="returnEmployeeManage"><input type="button" value="返回查询页面"></a>
--%>



</body>
</html>

