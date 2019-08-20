<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<c:if test="${sessionScope.user!= null}">
					<h2>Welcome ${user.getName()}</h2>
					<h4 style="text-align: right">
						<a href="/logout">Logout</a>
					</h4>
				</c:if>
				<c:if test="${not empty postError}">
					<div class="alert alert-danger" role="alert">${postError}</div>
				</c:if>
				<div class="postDiv">
					<form action="/showposts">
						<div>
							<textarea id="myTextArea" rows="3" cols="93" name="post"
								placeholder="Speak Your Mind!"></textarea>
						</div>
						<div>
							<input type="submit" value="Post" class="btn btn-success">
						</div>
						
					</form>
					
					
				</div>
				
				<c:if test="${not empty commentError}">
					<div class="alert alert-danger" role="alert">${commentError}</div>
						</c:if>
						
				<c:forEach var="post" items="${posts}">
					<div class="postDiv">
						<p>${post.getUser().getName()}</p>
						<p>${post.getElapsed()}</p>
						<p>${post.getDescription()}</p>
			
						
						
						<form action="/showcomments">
							<input type="hidden" name="postId" value="${post.getPostId()}" />
							<input type=text name="comment" placeholder="Comment here!">
							<input type="submit" value="Comment" class="btn btn-success">
						</form>
						<c:forEach var="comment" items="${post.getComments()}">
							<div class="commentDiv">
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