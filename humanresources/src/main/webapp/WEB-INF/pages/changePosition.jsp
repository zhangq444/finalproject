<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/8/1
  Time: 13:21
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

                    /*$("#test").empty();
                    $("#test").append("<tr><th>部门职位</th><th>职位薪资</th><th>查看职员</th></tr>")
                    $(obj).each(function () {
                        /!*$("#selectPosition").append("<option value="+this.id+">"+this.name+"</option>")*!/
                        $("#test").append("<tr><td>"+this.name+"</td><td>"+this.salary+"</td><td><input type='button' value='查看职员'></td></tr>")

                    })*/

                })
            })
        })
    </script>
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
    <h2>请选择需要更换的岗位</h2>
    <br>
    <form action="changePosition1">
        请选择部门和职位：
        <select id="selectDep" name="selectDep">
            <option value="0">请选择</option>
            <c:forEach var="i" begin="0" end="${fn:length(sessionScope.departmentList)-1}">
                <option value="${sessionScope.departmentList[i].id}" class="option1">${sessionScope.departmentList[i].name}</option>
            </c:forEach>
        </select>
        <select id="selectPosition" name="selectPosition">
            <option value="0">请选择</option>

        </select>
        <br>
        <br>
        <input type="submit" value="提交">

    </form>
    <br>
    ${sessionScope.changePositionError}<br>

</div>

<%--    <table id="test">

    </table>--%>


</body>
</html>

