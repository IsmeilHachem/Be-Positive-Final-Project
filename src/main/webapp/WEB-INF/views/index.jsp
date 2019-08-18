<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<p><c:if test="${not empty user}">${user} is not a valid user</c:if></p>
	<h1>Welcome to The World of Being Positive!</h1>
	<form action="/login" method="post">
		<input type="text" name="userName" placeholder="User name"> <input
			type="submit" value="login">
	</form>
</body>
</html>