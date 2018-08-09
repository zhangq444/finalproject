<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/29
  Time: 11:17
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
    <h2>面试邀请内容</h2>
    <br>
    <table>
        <tr>
            <td>面试部门：</td>
            <td>${sessionScope.interviewInfo.recruit.department.name}</td>
        </tr>
        <tr>
            <td>面试职位：</td>
            <td>${sessionScope.interviewInfo.recruit.position.name}</td>
        </tr>
        <tr>
            <td>面试人姓名：</td>
            <td>${sessionScope.interviewInfo.resume.name}</td>
        </tr>
        <tr>
            <td>面试时间：</td>
            <td>${fnn:DateToString1(sessionScope.interviewInfo.time)}</td>
        </tr>
        <tr>
            <td>面试地点：</td>
            <td>${sessionScope.interviewInfo.address}</td>
        </tr>
        <tr>
            <td>面试官姓名：</td>
            <td>${sessionScope.interviewInfo.employee.name}</td>
        </tr>
    </table>
    <br>
    <form action="sendInterview2">
        <input type="submit" value="发送面试邀请">
        <a href="returnManagerWelcome"><input type="button" value="返回管理员界面"></a>
    </form>

</div>





<%--


    面试邀请内容<br>

    面试部门：${sessionScope.interviewInfo.recruit.department.name}<br>
    面试职位：${sessionScope.interviewInfo.recruit.position.name}<br>
    面试人姓名：${sessionScope.interviewInfo.resume.name}<br>
    面试时间：${fnn:DateToString1(sessionScope.interviewInfo.time)}<br>
    面试地点：${sessionScope.interviewInfo.address}<br>
    面试官姓名：${sessionScope.interviewInfo.employee.name}<br>

    <form action="sendInterview2">
        <input type="submit" value="发送面试邀请">
        <a href="returnManagerWelcome"><input type="button" value="返回管理员界面"></a>
    </form>
--%>


</body>
</html>

