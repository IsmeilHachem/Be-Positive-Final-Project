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


<title>Sign Up</title>
</head>

<body class="bodyIndex">
	<h1>Sign Up!</h1>
	<form action="/save-signup" autocomplete="off" >
	<p>
		<label>User Name</label> <input name="name" />
	</p>
	
<!--  	
	<p>
		<label>Email</label> <input type="email" name="email" required />
	</p>
	<p>
		<label>Password</label> <input type="password" name="password" required />
	</p>
	<p>
	
	-->
		<button type="submit">Sign Up!</button>
	</p>
	</form>
</body>
</html>
