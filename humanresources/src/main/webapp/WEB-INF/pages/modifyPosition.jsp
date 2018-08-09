<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/31
  Time: 16:43
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
    <h2>欢迎来到职位修改页面</h2>
    <br>
    <form action="modifyPosition1">
        请输入新的职位名称：<input type="text" name="newPositionName"><br>
        请输入职位基本薪资：<input type="number" name="newPositionSalary"><br>
        <br>
        <input type="submit" value="提交修改">
    </form>
    ${sessionScope.modifyPositionError}

</div>

<%--
    欢迎来到职位修改页面<br>
    <form action="modifyPosition1">
        请输入新的职位名称：<input type="text" name="newPositionName"><br>
        请输入职位的基本薪资：<input type="number" name="newPositionSalary"><br>
        <input type="submit" value="提交修改">
    </form>
    ${sessionScope.modifyPositionError}--%>

</body>
</html>

