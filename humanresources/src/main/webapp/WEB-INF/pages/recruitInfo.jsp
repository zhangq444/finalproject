<%@ taglib prefix="fnn" uri="/elFunction" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/28
  Time: 16:34
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
    </style>

</head>
<body>

<jsp:include page="head.jsp"></jsp:include>

<div>
    <h2>基本信息</h2>
    <br>
    <table>
        <tr>
            <td>招聘部门:</td>
            <td>${sessionScope.recruitInfo.department.name}</td>
        </tr>
        <tr>
            <td>招聘职位:</td>
            <td>${sessionScope.recruitInfo.position.name}</td>
        </tr>
        <tr>
            <td>招聘人数:</td>
            <td>${sessionScope.recruitInfo.number}</td>
        </tr>
        <tr>
            <td>基本薪资:</td>
            <td>${sessionScope.recruitInfo.salary}</td>
        </tr>
        <tr>
            <td>发布时间:</td>
            <td>${fnn:DateToString(sessionScope.recruitInfo.time)}</td>
        </tr>
        <tr>
            <td>岗位描述:</td>
            <td>${sessionScope.recruitInfo.description}</td>
        </tr>
        <tr>
            <td>职位要求:</td>
            <td>${sessionScope.recruitInfo.requirement}</td>
        </tr>
    </table>
    <br>
    <form action="sendResume">
        请选择你的简历：
        <select name="resumeChoiceId">
            <c:if test="${fn:length(sessionScope.resumeList)!=0}">
                <c:forEach var="i" begin="0" end="${fn:length(sessionScope.resumeList)-1}">
                    <option value="${sessionScope.resumeList[i].id}">${sessionScope.resumeList[i].resumename}</option>
                </c:forEach>
            </c:if>
        </select>
        <br>
        <br>
        <input type="hidden" value="${sessionScope.recruitInfo.id}" name="recruitChoiceId">
        <input type="submit" value="投递简历">
        <a href="returnWelcome"><input type="button" value="返回主页"></a>
    </form>
    <br>
    ${sessionScope.sendResumeResult}

</div>






<%--

基本信息<br>
招聘部门：${sessionScope.recruitInfo.department.name}<br>
招聘职位：${sessionScope.recruitInfo.position.name}<br>
招聘人数：${sessionScope.recruitInfo.number}<br>
基本薪资：${sessionScope.recruitInfo.salary}<br>
发布时间：${fnn:DateToString(sessionScope.recruitInfo.time)}<br>
<br>
岗位描述：${sessionScope.recruitInfo.description}<br>
<br>
职位要求：${sessionScope.recruitInfo.requirement}<br>
<br>
<form action="sendResume">
    请选择你的简历：
    <select name="resumeChoiceId">
        <c:if test="${fn:length(sessionScope.resumeList)!=0}">
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.resumeList)-1}">
                <option value="${sessionScope.resumeList[i].id}">${sessionScope.resumeList[i].resumename}</option>
            </c:forEach>
        </c:if>
    </select>
    <br>
    <input type="hidden" value="${sessionScope.recruitInfo.id}" name="recruitChoiceId">
    <input type="submit" value="投递简历">
    <a href="returnWelcome"><input type="button" value="返回主页"></a>
</form>
<br>
${sessionScope.sendResumeResult}--%>

</body>
</html>

