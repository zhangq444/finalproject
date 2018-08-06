<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/5
  Time: 22:06
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

添加奖惩页面<br>
<br>
<form action="reconsiderAddRewards1">
    奖惩金额：<input type="number" name="money">(说明：负数代表惩，正数代表奖)<br>
    奖惩原因：<input type="text" name="explain"><br>
    <input type="hidden" value="${sessionScope.addRewardsEMPId}" name="addRewardsEMPId">
    <input type="submit" value="新增奖惩">
</form>

</body>
</html>

