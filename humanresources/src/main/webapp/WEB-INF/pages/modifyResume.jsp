<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/26
  Time: 21:57
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

<jsp:include page="head.jsp"></jsp:include>

<div>
    <h2>简历修改</h2>
    <br>

    <form action="modifyResume1">
        <input type="hidden" name="id" value="${sessionScope.showResumeInfo.id}" >
        <table >
            <tr>
                <td>姓名</td>
                <td><input type="text" name="name" value="${sessionScope.showResumeInfo.name}"></td>
                <td>性别</td>
                <td><input type="radio" name="gender" value="男">男<input type="radio" name="gender" value="女">女<br></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" name="phone" value="${sessionScope.showResumeInfo.phone}"></td>
                <td>e-mail</td>
                <td><input type="text" name="email" value="${sessionScope.showResumeInfo.email}"></td>
            </tr>
            <tr>
                <td>地址</td>
                <td><input type="text" name="address" value="${sessionScope.showResumeInfo.address}"></td>
                <td>身份证</td>
                <td><input type="text" name="idcard" value="${sessionScope.showResumeInfo.idcard}"></td>
            </tr>
            <tr>
                <td>生日</td>
                <td><input type="date" name="birthdaydate"></td>
                <td>毕业学校</td>
                <td><input type="text" name="school" value="${sessionScope.showResumeInfo.school}"></td>
            </tr>
            <tr>
                <td>专业</td>
                <td><input type="text" name="major" value="${sessionScope.showResumeInfo.major}"></td>
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
            <tr>
                <td>简历名称</td>
                <td><input type="text" name="resumename" value="${sessionScope.showResumeInfo.resumename}"></td>
            </tr>

        </table>
        <input type="submit" value="提交修改">
        <a href="resume"><input type="button" value="返回简历管理"></a>
    </form>
    <br>
    ${sessionScope.modifyResumeError}

</div>



<%--

    修改简历
    <form action="modifyResume1">
        <input type="hidden" name="id" value="${sessionScope.showResumeInfo.id}" >
        姓名：<input type="text" name="name" value="${sessionScope.showResumeInfo.name}"><br>
        性别：<input type="radio" name="gender" value="男">男<input type="radio" name="gender" value="女">女<br>
        电话：<input type="text" name="phone" value="${sessionScope.showResumeInfo.phone}"><br>
        e-mail：<input type="text" name="email" value="${sessionScope.showResumeInfo.email}"><br>
        地址：<input type="text" name="address" value="${sessionScope.showResumeInfo.address}"><br>
        身份证：<input type="text" name="idcard" value="${sessionScope.showResumeInfo.idcard}"><br>
        生日：<input type="date" name="birthdaydate"><br>
        毕业学校：<input type="text" name="school" value="${sessionScope.showResumeInfo.school}"><br>
        专业：<input type="text" name="major" value="${sessionScope.showResumeInfo.major}"><br>
        教育经历：<textarea name="education">${sessionScope.showResumeInfo.education}</textarea><br>
        工作经历：<textarea name="workexperience">${sessionScope.showResumeInfo.workexperience}</textarea><br>
        自我介绍：<textarea name="introduction">${sessionScope.showResumeInfo.introduction}</textarea><br>
        简历名称：<input type="text" name="resumename" value="${sessionScope.showResumeInfo.resumename}"><br>
        <input type="submit" value="提交修改">
        <a href="resume"><input type="button" value="返回简历管理"></a>
    </form>
--%>


</body>
</html>

