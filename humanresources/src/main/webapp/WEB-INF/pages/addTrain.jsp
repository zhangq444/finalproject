<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/2
  Time: 17:09
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

    添加新培训页面<br>
    <br>
        ${sessionScope.addTrainError}
    <br>
    <form action="addTrain1">
        培训主题：<input type="text" name="theme"><br>
        培训内容：<textarea name="content"></textarea><br>
        开始时间：<input type="datetime-local" name="beginTime"><br>
        结束时间：<input type="datetime-local" name="endTime"><br>
        培训地点：<input type="text" name="address"><br>
        <br>
        按照部门选择培训人员：<br>
        请选择部门和职位：
        <select id="selectDep" name="selectDep">
            <option value="0">请选择</option>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.addTrainDepartmentList)-1}">
                <option value="${sessionScope.addTrainDepartmentList[i].id}" class="option1">${sessionScope.addTrainDepartmentList[i].name}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="按部门提交" name="button1"><br>
        <br>
        按照时间选择培训人员：<br>
        请选择入职时间：<input type="datetime-local" name="traindate">(说明：对该时间之后入职的员工进行培训)<br>
        <input type="submit" value="按时间提交" name="button2"><br>
    </form>




</body>
</html>

