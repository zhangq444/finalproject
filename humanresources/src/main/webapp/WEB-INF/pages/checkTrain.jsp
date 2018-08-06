<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/3
  Time: 11:29
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

    查看培训信息<br>
    <c:if test="${fn:length(checkTrainList)==0}">
        没有查询到相关培训<br>
        <a href="returnEmployeeWelcome"><input type="button" value="返回主页"></a>
    </c:if>

    <c:if test="${fn:length(checkTrainList)!=0}">
        <table>
            <tr>
                <th>培训id</th>
                <th>培训主题</th>
                <th>培训内容</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>培训地点</th>
                <th>发布时间</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.checkTrainList)-1}">
                <tr>
                    <td>${sessionScope.checkTrainList[i].id}</td>
                    <td>${sessionScope.checkTrainList[i].theme}</td>
                    <td>${sessionScope.checkTrainList[i].content}</td>
                    <td>${fnn:DateToString1(sessionScope.checkTrainList[i].begintime)}</td>
                    <td>${fnn:DateToString1(sessionScope.checkTrainList[i].endtime)}</td>
                    <td>${sessionScope.checkTrainList[i].address}</td>
                    <td>${fnn:DateToString1(sessionScope.checkTrainList[i].releasetime)}</td>
                </tr>
            </c:forEach>

        </table>
        <a href="returnEmployeeWelcome"><input type="button" value="返回主页"></a>
    </c:if>

</body>
</html>

