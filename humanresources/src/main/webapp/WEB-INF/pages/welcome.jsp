<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>

<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/25
  Time: 19:52
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

<div id="div1">
    <form action="loginAndRegister">
        用户：<input type="text" name="name">&nbsp;&nbsp;
        密码：<input type="text" name="password">&nbsp;&nbsp;
        <input type="submit" value="登录" name="login">&nbsp;&nbsp;
        <input type="submit" value="注册" name="register">&nbsp;&nbsp;
        <input type="submit" value="直接登录" name="loginDirect">&nbsp;&nbsp;
    </form>
</div>

<div id="div2">
    <h2>欢迎${sessionScope.loginUser.name}来到公司的招聘页面</h2><br>
    ${sessionScope.loginError}
    <c:if test="${sessionScope.loginUser==null}">
        ${sessionScope.resumeError}
        ${sessionScope.interviewError}
    </c:if>
</div>

<div id="div3">
    <a href="resume"><input type="button" value="简历管理"></a>&nbsp;&nbsp;
    <a href="checkInterview"><input type="button" value="查看面试邀请"></a><br>
</div>

<div id="div4">
    <h2>招聘信息</h2>
</div>

<div id="div4">
    <table>
        <tr id="tr1">
            <th>招聘部门</th>
            <th>招聘职位</th>
            <th>招聘数量</th>
            <th>基本薪资</th>
            <th>招聘时间</th>
            <th>招聘详情</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.recruitList)-1}">
            <tr>
                <td>${sessionScope.recruitList[i].department.name}</td>
                <td>${sessionScope.recruitList[i].position.name}</td>
                <td>${sessionScope.recruitList[i].number}</td>
                <td>${sessionScope.recruitList[i].salary}</td>
                <td>${fnn:DateToString(sessionScope.recruitList[i].time)}</td>
                <%--<td>${sessionScope.recruitList[i].time}</td>--%>
                <td>
                    <form action="recruitInfo">
                        <input type="hidden" name="recruitInfoId" value="${sessionScope.recruitList[i].id}">
                        <input type="submit" value="查看详情">
                    </form>
                </td>
            </tr>

        </c:forEach>
    </table><br>

    <c:forEach var="i" begin="1" end="${sessionScope.totalPagesRecrultList}">
        <a href="?currentPage1=${i}">${i}</a>
    </c:forEach>
</div>

</body>
</html>

