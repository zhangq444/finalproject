<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/2
  Time: 14:45
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

    欢迎来到培训管理界面<br>
    <c:if test="${fn:length(sessionScope.trainList)==0}">
        未查到相关培训<br>
        <form>
            <input type="submit" value="添加培训">
        </form>
    </c:if>

<c:if test="${fn:length(sessionScope.trainList)!=0}">
    <table>
        <tr>
            <th>培训id</th>
            <th>培训主题</th>
            <th>培训地点</th>
            <th>培训发布时间</th>
            <th>查看详情</th>
            <th>发布</th>
            <th>撤回</th>
            <th>删除</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.trainList)-1}">
            <tr>
                <td>${sessionScope.trainList[i].id}</td>
                <td>${sessionScope.trainList[i].theme}</td>
                <td>${sessionScope.trainList[i].address}</td>
                <td>
                    <c:if test="${sessionScope.trainList[i].releasetime==null}">
                        尚未发布
                    </c:if>
                    <c:if test="${sessionScope.trainList[i].releasetime!=null}">
                        ${fnn:DateToString1(sessionScope.trainList[i].releasetime)}
                    </c:if>
                </td>
                <td>
                    <form action="showTrainInfo">
                        <input type="hidden" value="${sessionScope.trainList[i].id}" name="trainInfoId">
                        <input type="submit" value="查看详情">
                    </form>
                </td>
                <td>
                    <c:if test="${sessionScope.trainList[i].state==0}">
                        <form action="releaseTrain">
                            <input type="hidden" value="${sessionScope.trainList[i].id}" name="releaseTrainId">
                            <input type="submit" value="发布">
                        </form>
                    </c:if>
                </td>
                <td>
                    <form action="withdrawTrain">
                        <input type="hidden" value="${sessionScope.trainList[i].id}" name="withdrawTrainId">
                        <input type="submit" value="撤回">
                    </form>
                </td>
                <td>
                    <form action="deleteTrain">
                        <input type="hidden" value="${sessionScope.trainList[i].id}" name="deleteTrainId">
                        <input type="submit" value="删除">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="addTrain">
        <input type="submit" value="添加培训">
        <a href="returnManagerWelcome"><input type="button" value="返回主页面"></a>
    </form>

</c:if>
<br>
${sessionScope.withdrawTrainError}




</body>
</html>

