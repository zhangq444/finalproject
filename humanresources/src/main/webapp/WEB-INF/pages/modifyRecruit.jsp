<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/30
  Time: 15:24
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
        textarea{
            width: 500px;
            height: 150px;
        }

    </style>

</head>
<body>

<jsp:include page="head.jsp"></jsp:include>

<div>
    <form>
        <h2>修改招聘信息</h2>
        <br>
        <table>
            <tr>
                <td>招聘人数：</td>
                <td><input type="number" name="number"></td>
            </tr>
            <tr>
                <td>岗位描述：</td>
            </tr>
            <tr>
                <td colspan="2"><textarea name="description">${sessionScope.modifyRecruit.description}</textarea></td>
            </tr>
            <tr>
                <td>岗位要求：</td>
            </tr>
            <tr>
                <td colspan="2"><textarea name="requirement">${sessionScope.modifyRecruit.requirement}</textarea></td>
            </tr>
        </table>
        <input type="submit" value="提交修改">
    </form>
</div>

<%--
修改招聘信息

<form action="/modifyRecruit1">
    招聘人数：<br>
    岗位描述：<textarea name="description">${sessionScope.modifyRecruit.description}</textarea><br>
    岗位要求：<textarea name="requirement">${sessionScope.modifyRecruit.requirement}</textarea><br>
    <input type="submit" value="提交修改">
</form>
--%>



</body>
</html>

