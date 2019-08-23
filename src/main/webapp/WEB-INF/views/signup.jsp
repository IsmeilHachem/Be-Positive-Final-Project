<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="positiveStyle.css" />
<link
	href="https://fonts.googleapis.com/css?family=Indie+Flower&display=swap"
	rel="stylesheet">
<title>Sign Up</title>
</head>
<body class="bodyIndex">
	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>
	<h1>Sign Up!</h1>
	<form action="/submitsignup" autocomplete="off" method="post">
		<p>
			<label>User Name</label> <input name="name" />
		</p>
		<p>
			<label>First Name</label> <input name="firstname" />
		</p>
		<p>
			<label>Last Name</label> <input name="lastname" />
		</p>
		<button type="submit">Sign Up</button>
		
	</form>
</body>
</html>
