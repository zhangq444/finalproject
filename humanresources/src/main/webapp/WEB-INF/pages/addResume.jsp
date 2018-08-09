<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/26
  Time: 20:42
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
    <h2>添加简历</h2>
    <br>
    <form action="addResume1">
        <table >
            <tr>
                <td>姓名</td>
                <td><input type="text" name="name"></td>
                <td>性别</td>
                <td><input type="radio" name="gender" value="男">男<input type="radio" name="gender" value="女">女<br></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" name="phone"></td>
                <td>e-mail</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>地址</td>
                <td><input type="text" name="address"></td>
                <td>身份证</td>
                <td><input type="text" name="idcard"></td>
            </tr>
            <tr>
                <td>生日</td>
                <td><input type="date" name="birthdaydate"></td>
                <td>毕业学校</td>
                <td><input type="text" name="school"></td>
            </tr>
            <tr>
                <td>专业</td>
                <td><input type="text" name="major"></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>教育经历</td>
            </tr>
            <tr>
                <td colspan="4"><textarea name="education" ></textarea></td>
            </tr>
            <tr>
                <td>工作经历</td>
            </tr>
            <tr>
                <td colspan="4"><textarea name="workexperience"></textarea></td>
            </tr>
            <tr>
                <td>自我介绍</td>
            </tr>
            <tr>
                <td colspan="4"><textarea name="introduction"></textarea></td>
            </tr>
            <tr>
                <td>简历名称</td>
                <td><input type="text" name="resumename"></td>
            </tr>

        </table>
        <input type="submit" value="提交">
        <br>

    </form>
    <br>
    ${sessionScope.addResumeError}

</div>

</body>
</html>

