<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
</head>
<body>
	<h1>Welcome ${user.name}</h1>
	<form action="/mainpage" method="post">
		<textarea id = "myTextArea"
			rows = "5"
			cols = "50" name="post" placeholder="Speak Your Mind!"></textarea>
		<input type="submit" value="Submit">
	</form>

</body>
</html>