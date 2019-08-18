<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>B+ve</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<style type="text/css">
.postDiv {
	background-color: #fff;
	border: 1px solid #ccc;
	margin: 10px;
	padding: 10px;
}

.commentDiv {
	border-radius: 10px;
	border: 1px solid #ccc;
	margin: 10px;
	padding: 10px;
}
</style>
</head>
<body style="background-color: #ccc">
	<div class="container">
		<div class="row">
			<div class="col-md-8 offset-md-2">
				<h1>Welcome ${user.getName()}</h1>
				<div class="postDiv">
					<form action="/showposts">
						<div>
						<c:if test="${not empty error}">${error}</c:if>
							<textarea id="myTextArea" rows="3" cols="93" name="post"
								placeholder="Speak Your Mind!"></textarea>
						</div>
						<div>
							<input type="submit" value="Post" class="btn btn-success">
						</div>
					</form>
				</div>
				<c:forEach var="post" items="${posts}">
					<div class="postDiv">
						<p>${post.getUser().getName()}</p>
						<p>${post.getCreated()}</p>
						<p>${post.getDescription()}</p>
						<c:forEach var="comment" items="${post.getComments()}">
							<div class="commentDiv">
								<p>
									<input type=text name="comment" placeholder="Comment here!">
								</p>
								<p>
									<input type="submit" value="Comment" class="btn btn-success">
								</p>
								<p>${comment.getDescription()}</p>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>