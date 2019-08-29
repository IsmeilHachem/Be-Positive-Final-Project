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
<style>
table, tr, td {
	padding: 5px
}
</style>
</head>
<body class="bodyIndex">
	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>
	<h1>Sign Up!</h1>
	<br>
	<div align="center">
		<form action="/submitsignup" autocomplete="off" method="post">
			<table>
				<tr>
					<td><label>User Name</label></td>
					<td><input name="name" /></td>
				</tr>
				<tr>
					<td><label>First Name</label></td>
					<td><input name="firstname" /></td>
				</tr>
				<tr>
					<td><label>Last Name</label></td>
					<td><input name="lastname" /></td>
				</tr>
				<tr>
					<td><label>Password</label></td>
					<td><input type= "password" name = "password" required /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit" class="btn btn-secondary btn-sm">Sign Up</button>
						<a href ="/" class="btn btn-secondary btn-sm">Home</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
