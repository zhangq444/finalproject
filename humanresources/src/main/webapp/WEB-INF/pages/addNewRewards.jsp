<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/5
  Time: 20:43
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
    <h2>添加奖惩页面</h2>
    <br>
    <form action="addNewRewards1">
        奖惩金额：<input type="number" name="money">(说明：负数代表惩，正数代表奖)<br>
        奖惩原因：<input type="text" name="explain"><br>
        <br>
        <input type="hidden" value="${sessionScope.addNewRewardsEmpId}" name="addNewRewardsEmpId">
        <input type="submit" value="新增奖惩">
    </form>
</div>

</body>
</html>

