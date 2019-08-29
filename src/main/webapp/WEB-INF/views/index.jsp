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
<title>Login</title>
</head>
<style>
a:link, a:visited {

  color: white;
  padding: 14px 25px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  

}

a:hover, a:active {
	background-color: gray;
}

form {
	display: inline-block;
}
</style>
<body class="bodyIndex">
	<div id="centerLogin">
		<p id="userWarning">
			${error} 
		</p>
		<h1>Welcome to The World of Positivity!</h1>
		<form action="/login" method="post" id="loginCenter">
			<input type="text" name="userName" placeholder="User name">
			 
			<input type="password" name="password" placeholder="Password" required />
	
			<input type="submit" value="Login" class="btn btn-secondary btn-sm">
		</form>
		<form action="/signupUser" method="post">
			<input type="submit" value="SignUp" class="btn btn-secondary btn-sm">
		</form>
	</div>
</body>
</html>