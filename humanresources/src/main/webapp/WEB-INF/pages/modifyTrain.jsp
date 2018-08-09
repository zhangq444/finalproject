<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/2
  Time: 23:19
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
    <h2>欢迎来到培训修改页面</h2>
    <br>
    <form action="modifyTrain1">
        培训主题：<input type="text" name="theme"><br>
        培训内容：<textarea name="content"></textarea><br>
        开始时间：<input type="datetime-local" name="beginTime"><br>
        结束时间：<input type="datetime-local" name="endTime"><br>
        培训地点：<input type="text" name="address"><br>
        <br>
        <input type="submit" value="提交修改">
        <a href="returnManagerWelcome"><input type="button" value="返回主页"></a>
    </form>

</div>

</body>
</html>

