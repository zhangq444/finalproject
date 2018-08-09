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
    <style>
        div{
            width: 1000px;
            margin-left: auto;
            margin-right: auto;
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
        textarea{
            width: 500px;
            height: 120px;
        }

    </style>


</head>
<body>

<jsp:include page="head.jsp"></jsp:include>

<div>
    <h2>添加招聘信息</h2>
    <br>
    <form action="addRecruit2">
        <table>
            <tr>
                <td>请选择部门和职位：</td>
                <td>
                    <select id="selectDep" name="selectDep">
                        <option>请选择</option>
                        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.departmentList)-1}">
                            <option value="${sessionScope.departmentList[i].id}" class="option1">${sessionScope.departmentList[i].name}</option>
                        </c:forEach>
                    </select>
                    <select id="selectPosition" name="selectPosition">
                        <option>请选择</option>

                    </select>
                </td>
            </tr>
            <tr>
                <td>请输入招聘人数:</td>
                <td><input type="number" min="1" name="number"></td>
            </tr>
            <tr>
                <td>岗位描述：</td>
            </tr>
            <tr>
                <td colspan="4"><textarea name="description"></textarea></td>
            </tr>
            <tr>
                <td>岗位要求：</td>
            </tr>
            <tr>
                <td colspan="4"><textarea name="requirement"></textarea></td>
            </tr>
        </table>
        <input type="submit" value="提交">




        <%--请选择部门和职位：
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
        <input type="submit" value="提交">--%>

    </form>

</div>

</body>
</html>

