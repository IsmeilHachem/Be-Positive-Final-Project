<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
	<link rel="stylesheet" href="positiveStyle.css" />
	<link
	href="https://fonts.googleapis.com/css?family=Indie+Flower&display=swap"
	rel="stylesheet">
<meta charset="UTF-8">
<title>Thanks</title>
</head>
<body class="bodyIndex">
	
	<c:if test="${empty error}">
	<div align="center" >
		<h1>Thanks for signing up with Be-Positive</h1>
		<h2>Welcome ${ user.name }!</h2>
		<p>
			<a href="/posts" class="btn btn-secondary btn-sm">Home</a>
		</p>
		</div>
	</c:if>
</body>
</html>