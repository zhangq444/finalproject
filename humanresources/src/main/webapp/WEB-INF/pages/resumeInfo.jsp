<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/26
  Time: 21:51
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
        textarea{
            width: 500px;
            height: 150px;
        }
        div{
            width: 1000px;
            margin-left: auto;
            margin-right: auto;
        }

    </style>
</head>
<body>

<jsp:include page="head.jsp"></jsp:include>

<div>
    <h2>简历详情</h2>
    <br>
    <table >
        <tr>
            <td colspan="4">${sessionScope.showResumeInfo.resumename}</td>
        </tr>
        <tr>
            <td>姓名</td>
            <td>${sessionScope.showResumeInfo.name}</td>
            <td>性别</td>
            <td>${sessionScope.showResumeInfo.gender}</td>
        </tr>
        <tr>
            <td>电话</td>
            <td>${sessionScope.showResumeInfo.phone}</td>
            <td>e-mail</td>
            <td>${sessionScope.showResumeInfo.email}</td>
        </tr>
        <tr>
            <td>地址</td>
            <td>${sessionScope.showResumeInfo.address}</td>
            <td>身份证</td>
            <td>${sessionScope.showResumeInfo.idcard}</td>
        </tr>
        <tr>
            <td>生日</td>
            <td>${sessionScope.showResumeInfoBirthday}</td>
            <td>毕业学校</td>
            <td>${sessionScope.showResumeInfo.school}</td>
        </tr>
        <tr>
            <td>专业</td>
            <td>${sessionScope.showResumeInfo.major}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>教育经历</td>
        </tr>
        <tr>
            <td colspan="4"><textarea name="education" >${sessionScope.showResumeInfo.education}</textarea></td>
        </tr>
        <tr>
            <td>工作经历</td>
        </tr>
        <tr>
            <td colspan="4"><textarea name="workexperience">${sessionScope.showResumeInfo.workexperience}</textarea></td>
        </tr>
        <tr>
            <td>自我介绍</td>
        </tr>
        <tr>
            <td colspan="4"><textarea name="introduction">${sessionScope.showResumeInfo.introduction}</textarea></td>
        </tr>
    </table>
    <br>
    <a href="modifyResume"><input type="button" value="修改"></a><a href="resume"><input type="button" value="返回简历管理"></a>

</div>




</body>
</html>

