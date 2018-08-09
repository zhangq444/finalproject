<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/29
  Time: 16:06
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

    <h2>录取新员工</h2>
    <br>
    <form action="admitEmployee2">
        <table>
            <tr>
                <td>员工登录用户名:</td>
                <td><input type="text" name="uname"></td>
            </tr>
            <tr>
                <td>员工登录密码：</td>
                <td><input type="password" name="upassword"></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="提交">
        <a href="returnManagerWelcome"><input type="button" value="返回主页面"></a>
    </form>

    ${sessionScope.admitEmployeeError}

</div>

</body>
</html>

