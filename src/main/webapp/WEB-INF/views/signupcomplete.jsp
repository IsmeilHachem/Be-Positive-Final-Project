<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Thanks</title>
</head>
<body>
	
	<c:if test="${empty error}">
		<h1>Thanks for signing up with Be-Positive</h1>
		<h2>Welcome ${ user.name }!</h2>
		<p>
			<a href="/posts">Home Page</a>
		</p>
	</c:if>
</body>
</html>