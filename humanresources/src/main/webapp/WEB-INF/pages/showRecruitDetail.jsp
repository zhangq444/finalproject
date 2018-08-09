<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/30
  Time: 15:11
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
    <h2>招聘信息预览</h2>
    <br>
    <table>
        <tr>
            <td>招聘部门：</td>
            <td>${sessionScope.showRecruitDetail.department.name}</td>
        </tr>
        <tr>
            <td>招聘职位：</td>
            <td>${sessionScope.showRecruitDetail.position.name}</td>
        </tr>
        <tr>
            <td>招聘数量：</td>
            <td>${sessionScope.showRecruitDetail.number}</td>
        </tr>
        <tr>
            <td>基本工资：</td>
            <td>${sessionScope.showRecruitDetail.salary}</td>
        </tr>
        <tr>
            <td>招聘时间：</td>
            <td>${fnn:DateToString1(sessionScope.showRecruitDetail.time)}</td>
        </tr>
        <tr>
            <td>岗位描述：</td>
            <td>${sessionScope.showRecruitDetail.description}</td>
        </tr>
        <tr>
            <td>岗位要求：</td>
            <td>${sessionScope.showRecruitDetail.requirement}</td>
        </tr>
    </table>
    <br>
    <form action="modifyRecruit">
        <input type="hidden" value="${sessionScope.showRecruitDetail.id}" name="modifyRecruitId">
        <input type="submit" value="修改">
        <a href="returnManagerWelcome"><input type="button" value="返回主页面"></a>
    </form>

</div>



<%--



招聘信息预览<br>

招聘部门：${sessionScope.showRecruitDetail.department.name}<br>
招聘职位：${sessionScope.showRecruitDetail.position.name}<br>
招聘数量：${sessionScope.showRecruitDetail.number}<br>
基本工资：${sessionScope.showRecruitDetail.salary}<br>
招聘时间：${fnn:DateToString1(sessionScope.showRecruitDetail.time)}<br>
岗位描述：${sessionScope.showRecruitDetail.description}<br>
岗位要求：${sessionScope.showRecruitDetail.requirement}<br>
<br>
<form action="modifyRecruit">
    <input type="hidden" value="${sessionScope.showRecruitDetail.id}" name="modifyRecruitId">
    <input type="submit" value="修改">
    <a href="returnManagerWelcome"><input type="button" value="返回主页面"></a>
</form>

--%>


</body>
</html>

