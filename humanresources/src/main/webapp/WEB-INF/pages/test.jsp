<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/30
  Time: 10:20
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
            /*$("#button1").click(function () {
                alert("aa")
            })*/
            /*var optionArr=$(".option1");
            var sel;
            for(var i=0;i<optionArr.length;i++){
                if(optionArr[i].)
            }*/

            $("#selectDep").change(function () {
                var sel=$("#selectDep option:selected").val();
                /*alert(sel);*/
                /*$.ajax({
                    type:"post",
                    url:"test2",
                    data:"key="+sel,
                    success:function (obj) {
                        alert(obj)
                        obj.each(function () {
                            alert(this.id+":"+this.name)
                        })
                    }
                })*/

                $.getJSON("test2","key="+sel,function (obj) {
                    /*alert(obj)*/
                    $("#selectPosition").empty();
                    $(obj).each(function () {
                        /*alert(this.id+":"+this.name)*/
                        $("#selectPosition").append("<option value="+this.id+">"+this.name+"</option>")
                    })
                })


            })



        })



    </script>


</head>
<body>
    测试二级联动<br>

<form action="/test3">
    <select id="selectDep" name="selectDep">
        <option>请选择</option>
        <c:forEach var="i" begin="0" end="${fn:length(sessionScope.departmentList)-1}">
            <option value="${sessionScope.departmentList[i].id}" class="option1">${sessionScope.departmentList[i].name}</option>
        </c:forEach>
    </select>
    <select id="selectPosition" name="selectPosition">

    </select>
    <input type="submit" value="提交">
</form>

<input type="button" value="点击" id="button1">


</body>
</html>

