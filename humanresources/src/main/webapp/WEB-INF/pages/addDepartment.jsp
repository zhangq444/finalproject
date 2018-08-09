<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/30
  Time: 22:40
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
    <h2>欢迎来到增加部门页面</h2>
    <br>
    <br>
    <form action="addDepartment">
        请输入新部门的名字：<input type="text" name="departmentName"><br>
        <br>
        <input type="submit" value="提交"><a href="returnManagerWelcome"><input type="button" value="返回主页面"></a>
    </form>
    ${sessionScope.addDepartmentError}

</div>



</body>
</html>

