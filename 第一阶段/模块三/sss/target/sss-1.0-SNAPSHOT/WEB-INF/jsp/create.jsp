<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
    <form action="/resume/create" method="post">
	<table align="center" border="1">
		<tr>
			<td>id(必填):</td>
			<c:choose>
			<c:when test="${resume != null}">
			<td><input type="text" name="id" value="${resume.id}"></td>
			</c:when>
			<c:otherwise>
			<td><input type="text" name="id" value=""></td>
			</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td>name:</td>
			<c:choose>
			<c:when test="${resume != null}">
			<td><input type="text" name="name" value="${resume.name}"></td>
			</c:when>
			<c:otherwise>
			<td><input type="text" name="name" value=""></td>
			</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td>address:</td>
			<c:choose>
			<c:when test="${resume != null}">
			<td><input type="text" name="address" value="${resume.address}"></td>
			</c:when>
			<c:otherwise>
			<td><input type="text" name="address" value=""></td>
			</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td>phone:</td>
			<c:choose>
			<c:when test="${resume != null}">
			<td><input type="text" name="phone" value="${resume.phone}"></td>
			</c:when>
			<c:otherwise>
			<td><input type="text" name="phone" value=""></td>
			</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td><input type="submit" value="提交" name="submit"></td>
			<td><input type="reset" value="重置" name="reset"></td>
		</tr>
	</table>
	</form>
</body>
</html>