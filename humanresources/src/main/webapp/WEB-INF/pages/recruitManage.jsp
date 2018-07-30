<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnn" uri="/elFunction" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/30
  Time: 13:47
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

    <table>
        <tr>
            <th>招聘信息id</th>
            <th>招聘部门</th>
            <th>招聘职位</th>
            <th>招聘信息创建时间</th>
            <th>招聘信息状态</th>
            <th>详细信息</th>
            <th>发布</th>
            <th>取消发布</th>
            <th>删除</th>
        </tr>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.recruitList)-1}">
            <tr>
                <td>${sessionScope.recruitList[i].id}</td>
                <td>${sessionScope.recruitList[i].department.name}</td>
                <td>${sessionScope.recruitList[i].position.name}</td>
                <td>${fnn:DateToString1(sessionScope.recruitList[i].time)}</td>
                <td>
                    <c:if test="${sessionScope.recruitList[i].state==0}">
                        未发布
                    </c:if>
                    <c:if test="${sessionScope.recruitList[i].state==1}">
                        已发布
                    </c:if>
                </td>
                <td>
                    <form action="showRecruitDetail">
                        <input type="hidden" value="${sessionScope.recruitList[i].id}" name="showRecruitInfoId">
                        <input type="submit" value="预览招聘信息">
                    </form>
                </td>
                <td>
                    <c:if test="${sessionScope.recruitList[i].state==0}">
                        <form action="releaseRecruit">
                            <input type="hidden" value="${sessionScope.recruitList[i].id}" name="releaseRecruitId">
                            <input type="submit" value="发布">
                        </form>
                    </c:if>
                </td>
                <td>
                    <c:if test="${sessionScope.recruitList[i].state==1}">
                        <form action="unreleaseRecruit">
                            <input type="hidden" value="${sessionScope.recruitList[i].id}" name="unreleaseRecruitId">
                            <input type="submit" value="取消发布">
                        </form>
                    </c:if>
                </td>
                <td>
                    <c:if test="${sessionScope.recruitList[i].state==0}">
                        <form action="deleteRecruit">
                            <input type="hidden" value="${sessionScope.recruitList[i].id}" name="deleteRecruitId">
                            <input type="submit" value="删除">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="addRecruit">
        <input type="submit" value="添加招聘信息">
        <a href="returnManagerWelcome"><input type="button" value="返回主页面"></a>
    </form>



</body>
</html>

