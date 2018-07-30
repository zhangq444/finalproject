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
</head>
<body>

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
    </form>

</body>
</html>

