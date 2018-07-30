<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/30
  Time: 14:08
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
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
        $(function () {
            $("#selectDep").change(function () {
                var sel=$("#selectDep option:selected").val();
                $.getJSON("addRecruit1","key="+sel,function (obj) {
                    $("#selectPosition").empty();
                    $(obj).each(function () {
                        $("#selectPosition").append("<option value="+this.id+">"+this.name+"</option>")
                    })
                })
            })
        })
    </script>

</head>
<body>

<form action="/addRecruit2">
    请选择部门和职位：
    <select id="selectDep" name="selectDep">
        <option>请选择</option>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.departmentList)-1}">
            <option value="${sessionScope.departmentList[i].id}" class="option1">${sessionScope.departmentList[i].name}</option>
        </c:forEach>
    </select>
    <select id="selectPosition" name="selectPosition">
        <option>请选择</option>

    </select>
    <br>
    请输入招聘人数：<input type="number" min="1" name="number"><br>
    岗位描述：<textarea name="description"></textarea><br>
    岗位要求：<textarea name="requirement"></textarea><br>
    <br>
    <input type="submit" value="提交">

</form>


</body>
</html>

