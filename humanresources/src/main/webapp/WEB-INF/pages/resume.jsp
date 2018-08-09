<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/26
  Time: 15:30
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
        #div1{
            margin-left: auto;
            margin-right: auto;
            width: 1000px;

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

<div id="div1">
    <c:if test="${fn:length(sessionScope.showResumeList)==0}">
        <c:out value="未查询到相关简历"></c:out>
    </c:if>
    <br>

    <c:if test="${fn:length(sessionScope.showResumeList)>0}">
        <table>
            <tr id="tr1">
                <th>简历ID</th>
                <th>简历名称</th>
                <th>简历详情</th>
                <th>删除简历</th>
            </tr>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.showResumeList)-1}">
                <tr>
                    <td>${sessionScope.showResumeList[i].id}</td>
                    <td>${sessionScope.showResumeList[i].resumename}</td>
                    <td>
                        <form action="resumeInfo">
                            <input type="hidden" value="${sessionScope.showResumeList[i].id}" name="resumeInfoId">
                            <input type="submit" value="简历详情">
                        </form>
                    </td>
                    <td>
                        <form action="deleteResume">
                            <input type="hidden" value="${sessionScope.showResumeList[i].id}" name="deleteResumeId">
                            <input type="submit" value="删除简历">
                        </form>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </c:if>

    <form action="addResume">
        <input type="submit" value="增加新简历">
        <a href="returnWelcome"><input type="button" value="返回主页面"></a>
    </form>

    <c:forEach var="i" begin="1" end="${sessionScope.showResumeListTotalPages}">
        <a href="resume?currentPage=${i}">&nbsp;${i}&nbsp;</a>
    </c:forEach>


</div>

</body>
</html>

