<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"  href="positiveStyle.css"/> 
<link href="https://fonts.googleapis.com/css?family=Indie+Flower&display=swap" rel="stylesheet">


<title>Login</title>
</head>
<body id="bodyIndex">

<div id="centerLogin">
	<p id="userWarning"><c:if test="${not empty user}">${user} is not a valid user</c:if></p>
	<h1>Welcome to The World of Being Positive!</h1>
	<form action="/login" method="post" id="loginCenter">
		<input type="text" name="userName" placeholder="User name"> 
		<input  type="submit"  value="login" class="btn btn-secondary btn-sm">
	</form>
</div>
	
</body>
</html>