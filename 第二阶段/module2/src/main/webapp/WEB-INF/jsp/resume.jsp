<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	table{
		margin:0 auto;
		border:1px solid #F00;
		width:800px;
	}
	tr:even{
		background-color:red;
	}
	.tiaozhuan{
		margin:0 auto;
		width:800px;
		text-align:center;
	}
	p{
		width: 800px;
		margin:0 auto;
		text-align:center;
	}
</style>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("tr:even").css({"background":"red"});
	});
</script>
</head>
<body>
    <a href="/resume/createView?id=0">创建</a><br/>
		<table>
		<tr><td>id</td><td>姓名</td><td>地址</td><td>手机</td><td>操作</td></tr>
	<c:forEach items="${list}" var="resume">
	<tr>
	    <td>${resume.id}</td>
	    <td>${resume.name}</td>
	    <td>${resume.address}</td>
	    <td>${resume.phone}</td>
		<td><a href='/resume/createView?id=${resume.id}'>修改</a>&nbsp;<a href='/resume/delete?id=${resume.id}'>删除</a></td></tr>
	</tr>
   	</c:forEach>
		</table>
</body>
</html>