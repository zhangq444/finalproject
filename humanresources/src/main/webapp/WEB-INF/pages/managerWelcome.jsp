<%--
  Created by IntelliJ IDEA.
  User: grzha
  Date: 2018/7/28
  Time: 21:39
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
            width: auto;
            height: 50px;
            /*background-color: brown;*/
        }
        #div2{
            width: auto;
            height: 50px;
            /*background-color: lightblue;*/
        }
        ul{
            list-style-type: none;
            margin: 0px;
            padding: 0px;
        }
        li{
            list-style-type: none;
            float: left;
            margin: 20px;
            width: 100px;
            height: 30px;
        }

    </style>


</head>
<body>

<jsp:include page="head.jsp"></jsp:include>

<div>
    <h2>欢迎来到管理员界面</h2>

    <div id="div1">
    <ul>
        <li><a href="showRecruitment?currentPage=1"><input type="button" value="查看应聘简历"></a></li>
        <li><a href="admitEmployee"><input type="button" value="录取员工管理"></a></li>
        <li><a href="recruitManage"><input type="button" value="招聘信息管理"></a></li>
        <li><a href="showDepartment"><input type="button" value="部门职位管理"></a></li>
        <li><a href="employeeManage"><input type="button" value="员工信息管理"></a></li>
    </ul>
    </div>
    <br>
    <div id="div2">
    <ul>
        <li><a href="trainManage"><input type="button" value="培训信息管理"></a></li>
        <li><a href="salaryManage"><input type="button" value="员工薪资管理"></a></li>
        <li><a href="checkEmployeeAttendance"><input type="button" value="员工考勤查看"></a></li>
        <li><a href="rewardsManage"><input type="button" value="奖惩信息管理"></a></li>
    </ul>
    </div>


</div>



<%--
    管理员登录成功

    <ul>
        <li><a href="showRecruitment?currentPage=1"><input type="button" value="查看应聘简历"></a></li>
        <li><a href="admitEmployee"><input type="button" value="录取员工"></a></li>
        <li><a href="recruitManage"><input type="button" value="招聘管理"></a></li>
        <li><a href="showDepartment"><input type="button" value="部门职位管理"></a></li>
        <li><a href="employeeManage"><input type="button" value="员工管理"></a></li>
        <li><a href="trainManage"><input type="button" value="培训管理"></a></li>
        <li><a href="salaryManage"><input type="button" value="员工薪资管理"></a></li>
        <li><a href="checkEmployeeAttendance"><input type="button" value="员工考勤查看"></a></li>
        <li><a href="rewardsManage"><input type="button" value="奖惩管理"></a></li>
    </ul>

<br>--%>
<%--<a href="test"><input type="button" value="测试二级联动"></a>--%>





</body>
</html>

