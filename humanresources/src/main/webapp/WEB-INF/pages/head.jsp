<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/6
  Time: 15:01
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
        #head{
            background-image: url("/pic/head.jpg");
            width: 1000px;
            height: 85px;
            margin-right: auto;
            margin-left: auto;
        }


    </style>
</head>
<body>

    <div id="head">

    </div>

</body>
</html>

