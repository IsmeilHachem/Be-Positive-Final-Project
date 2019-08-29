<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="positiveStyle.css" />
<link
	href="https://fonts.googleapis.com/css?family=Indie+Flower&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>${title}</title>
</head>
<body id="mainPageBG">
	<nav class="navbar navbar-dark" style="background-color: darkblue;">
		<span class="navbar-brand mb-0 h1">Welcome ${user.getName()}</span>
		<c:if test="${sessionScope.user!= null}">
			<h4 id="logout">
				<a href="/logout">Logout</a>
			</h4>
		</c:if>
		<!-- <form class="form-inline">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form> -->
	</nav>
	<div class="container">
		<div class="row">
		<div class="col-sm-6" id="editMoveOver">
	<main >
	<h1>${title}</h1>

	<div id="postForm">
		<c:if test="${not empty postError}">
			<div class="alert alert-danger" role="alert">${postError}</div>
		</c:if>
		<form action="/editpost" method="post">
			<input type="hidden" name="postId" value="${post.getPostId()}" />
			<div>
				<textarea id="myTextArea" rows="4" cols="65" name="post"
					 required >${post.getDescription()}</textarea>
			</div>
			<div id="button">
				<input type="submit" value="Post" class="btn btn-warning">
				<a href="/posts"  type="submit" class="btn btn-warning" >Back</a>
				
			</div>
		</form>
	</div>
	</main>
	</div>
</div>	
</body>
</html>