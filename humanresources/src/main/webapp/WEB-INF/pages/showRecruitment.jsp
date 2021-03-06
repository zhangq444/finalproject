<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/28
  Time: 23:28
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
    <h2>收到的简历列表</h2>
    <br>
    <table>
        <tr id="tr1">
            <th>收取简历信息id</th>
            <th>用户id</th>
            <th>招聘部门</th>
            <th>招聘职位</th>
            <th>应聘人姓名</th>
            <th>是否已读</th>
            <th>是否邀请面试</th>
            <th>详细信息</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showRecruitmentList)-1}">
            <tr>
                <td>${sessionScope.showRecruitmentList[i].id}</td>
                <td>${sessionScope.showRecruitmentList[i].users.id}</td>
                <td>${sessionScope.showRecruitmentList[i].recruit.department.name}</td>
                <td>${sessionScope.showRecruitmentList[i].recruit.position.name}</td>
                <td>${sessionScope.showRecruitmentList[i].resume.name}</td>
                <td>
                    <c:if test="${sessionScope.showRecruitmentList[i].read==0}">
                        未阅读
                    </c:if>
                    <c:if test="${sessionScope.showRecruitmentList[i].read!=0}">
                        已阅读
                    </c:if>
                </td>
                <td>
                    <c:if test="${sessionScope.showRecruitmentList[i].invite==0}">
                        未邀请
                    </c:if>
                    <c:if test="${sessionScope.showRecruitmentList[i].invite!=0}">
                        已邀请
                    </c:if>
                </td>
                <td>
                    <form action="showRecruitmentInfo">
                        <input type="hidden" value="${sessionScope.showRecruitmentList[i].id}" name="showRecruitmentInfoId">
                        <input type="submit" value="查看详情">
                    </form>
                </td>
            </tr>

        </c:forEach>
    </table>
    <br>
    <c:forEach var="i" begin="1" end="${sessionScope.showRecruitmentListTotalPages}">
        <a href="showRecruitment?currentPage=${i}">&nbsp;${i}&nbsp;</a>
    </c:forEach>
    <br>
    <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>



</div>





<%--

    <table>
        <tr>
            <th>收取简历信息id</th>
            <th>用户id</th>
            <th>招聘部门</th>
            <th>招聘职位</th>
            <th>应聘人姓名</th>
            <th>是否已读</th>
            <th>是否邀请面试</th>
            <th>详细信息</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showRecruitmentList)-1}">
            <tr>
                <td>${sessionScope.showRecruitmentList[i].id}</td>
                <td>${sessionScope.showRecruitmentList[i].users.id}</td>
                <td>${sessionScope.showRecruitmentList[i].recruit.department.name}</td>
                <td>${sessionScope.showRecruitmentList[i].recruit.position.name}</td>
                <td>${sessionScope.showRecruitmentList[i].resume.name}</td>
                <td>
                    <c:if test="${sessionScope.showRecruitmentList[i].read==0}">
                        未阅读
                    </c:if>
                    <c:if test="${sessionScope.showRecruitmentList[i].read!=0}">
                        已阅读
                    </c:if>
                </td>
                <td>
                    <c:if test="${sessionScope.showRecruitmentList[i].invite==0}">
                        未邀请
                    </c:if>
                    <c:if test="${sessionScope.showRecruitmentList[i].invite!=0}">
                        已邀请
                    </c:if>
                </td>
                <td>
                    <form action="showRecruitmentInfo">
                        <input type="hidden" value="${sessionScope.showRecruitmentList[i].id}" name="showRecruitmentInfoId">
                        <input type="submit" value="查看详情">
                    </form>
                </td>
            </tr>

        </c:forEach>




    </table>

--%>

</body>
</html>

