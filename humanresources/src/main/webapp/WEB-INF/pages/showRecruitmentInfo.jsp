<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/29
  Time: 9:07
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
    <h2>简历详细信息</h2><br>
    <br>
    <table>
        <tr>
            <td>用户id：</td>
            <td>${sessionScope.showRecruitmentInfo.users.id}</td>
        </tr>
        <tr>
            <td>用户名称：</td>
            <td>${sessionScope.showRecruitmentInfo.users.name}</td>
        </tr>
        <tr>
            <td>招聘部门：</td>
            <td>${sessionScope.showRecruitmentInfo.recruit.position.name}</td>
        </tr>
        <tr>
            <td>招聘职位：</td>
            <td>${sessionScope.showRecruitmentInfo.recruit.position.name}</td>
        </tr>
        <tr>
            <td>姓名：</td>
            <td>${sessionScope.showRecruitmentInfo.resume.name}</td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>${sessionScope.showRecruitmentInfo.resume.gender}</td>
        </tr>
        <tr>
            <td>手机号:</td>
            <td>${sessionScope.showRecruitmentInfo.resume.phone}</td>
        </tr>
        <tr>
            <td>email：</td>
            <td>${sessionScope.showRecruitmentInfo.resume.email}</td>
        </tr>
        <tr>
            <td>教育经历:</td>
            <td>${sessionScope.showRecruitmentInfo.resume.education}</td>
        </tr>
        <tr>
            <td>工作经历：</td>
            <td>${sessionScope.showRecruitmentInfo.resume.workexperience}</td>
        </tr>
        <tr>
            <td>是否邀请：</td>
            <td>
                <c:if test="${sessionScope.showRecruitmentInfo.invite==0}">
                    尚未邀请面试
                </c:if>
                <c:if test="${sessionScope.showRecruitmentInfo.invite==1}">
                    已邀请面试
                </c:if>
            </td>
        </tr>
    </table>

    <form action="sendInterview">
        <input type="hidden" value="${sessionScope.showRecruitmentInfo.id}" name="sendInterviewId">
        <input type="submit" value="发送面试邀请">
        <a href="returnManagerWelcome"><input type="button" value="返回管理员主页面"></a>
    </form>


</div>



<%--

    获得简历详细信息<br>

    用户id：${sessionScope.showRecruitmentInfo.users.id}<br>
    用户名称：${sessionScope.showRecruitmentInfo.users.name}<br>
    招聘部门：${sessionScope.showRecruitmentInfo.recruit.department.name}<br>
    招聘职位：${sessionScope.showRecruitmentInfo.recruit.position.name}<br>
    姓名：${sessionScope.showRecruitmentInfo.resume.name}<br>
    是否邀请：${sessionScope.showRecruitmentInfo.invite}<br>

    <form action="sendInterview">
        <input type="hidden" value="${sessionScope.showRecruitmentInfo.id}" name="sendInterviewId">
        <input type="submit" value="发送面试邀请">
        <a href="returnManagerWelcome"><input type="button" value="返回管理员主页面"></a>
    </form>--%>

</body>
</html>

