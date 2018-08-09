<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/31
  Time: 15:07
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

    <h2>您是要在${sessionScope.addPosDepartment.name}下增加新的职位</h2>
    <br>
    <form action="addNewPosition1">
        职位名称：<input type="text" name="positionName"><br>
        职位薪资：<input type="number" name="positionSalary"><br>
        <input type="submit" value="提交">&nbsp;<a href="returnManagerWelcome"><input type="button" value="返回主页"></a>
    </form>
    ${sessionScope.addPositionNameError}


</div>



<%--


    您是要在${sessionScope.addPosDepartment.name}下增加新的职位<br>
    <form action="addNewPosition1">
        职位名称：<input type="text" name="positionName"><br>
        职位薪资：<input type="number" name="positionSalary"><br>
        <input type="submit" value="提交">&nbsp;<a href="returnManagerWelcome"><input type="button" value="返回主页"></a>
    </form>
    ${sessionScope.addPositionNameError}
--%>

</body>
</html>

