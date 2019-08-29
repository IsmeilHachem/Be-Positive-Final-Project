<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://cloudinary.com/jsp/taglib" prefix="cl"%>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<title>B+ve</title>
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

.tone-Analytical {
	background-color: royalblue;
}

.tone-Joy {
	background-color: yellow;
}

.tone-Sadness {
	background-color: #6588ba;
}

.tone-Fear {
	background-color: green;
}

.tone-Tentative {
	background-color: turquoise;
}

.tone-Confident {
	background-color: purple;
}
</style>
</head>
<body id="mainPageBG">
	<nav class="navbar navbar-dark"
		style="background-color: darkblue; font-style: italic;">
		<span class="navbar-brand mb-0 h1">Welcome ${user.getName()}</span>
		<c:if test="${sessionScope.user!= null}">
			<h4 id="logout">
				<a href="/logout">Logout</a>
			</h4>
		</c:if>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm-3" id="quoMoveDown">
				<c:if test="${not empty quote}">
					<div id="quoteBorder">
						<h2>Quote</h2>
						<hr id="hrMovedown" />
						<blockquote class="blockquote">
							<p>${quote.quoteText}</p>
							<p>- ${quote.quoteAuthor}</p>
						</blockquote>
					</div>
				</c:if>
				<table id="followOthers" style="width: 100%;">
					<c:if test="${not empty follows}">
						<tr>
							<td colspan="2"><h2 id="followBanner">Following</h2></td>
						</tr>
						<c:forEach var="follow" items="${follows}">
							<tr>
								<td>${follow.getFirstName()}${follow.getLastName()}</td>
								<td><a class="btn btn-warning btn-sm"
									href="/unfollow?followUserId=${follow.getUserId()}">
										UnFollow</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="2"><hr id="hrMovedown" /></td>
						</tr>
					</c:if>
					<c:if test="${not empty otherUsers}">
						<tr>
							<td colspan="2"><h2 id="followBanner">Follow Them</h2></td>
						</tr>
						<c:forEach var="otherUser" items="${otherUsers}">
							<tr>
								<td>${otherUser.getFirstName()}${otherUser.getLastName()}</td>
								<td><a class="btn btn-warning btn-sm"
									href="/follow?followUserId=${otherUser.getUserId()}">
										Follow</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
			<div class="col-sm-6">
				<c:if test="${not empty postError}">
					<div class="alert alert-danger" role="alert">${postError}</div>
				</c:if>
				<div class="postDiv">
					<form action="createposts" method="post">
						<div>
							<textarea id="myTextArea" rows="4" cols="65" name="post"
								placeholder="Speak Your Mind!" required></textarea>
						</div>
						<div id="button">
							<input type="submit" value="Post" class="btn btn-warning">
							<a href="/uploadphoto">Upload Photo</a>
						</div>
					</form>
				</div>
				<c:if test="${not empty commentError}">
					<div class="alert alert-danger" role="alert">${commentError}</div>
				</c:if>
				<c:forEach var="post" items="${posts}">
					<div class="postDiv">
						<table style="width: 100%">
							<th style="width: 50px"><img src="person.png" height="42"
								width="42" /></th>
							<th>
								<p>${post.getUser().getName()}</p>
								<p>${post.getElapsed()}</p>
							</th>
							<th style="width: 25%">
								<p>${post.getMaxTone()}</p>
								<p>
									<!-- https://getbootstrap.com/docs/4.3/components/progress/ -->
									<c:set var="percent" value="${post.getMaxScore() * 100}" />
								<div class="progress">
									<div class="progress-bar tone-${post.getMaxTone()}"
										role="progressbar" style="width:${percent}%"
										aria-valuenow="${percent}" aria-valuemin="0"
										aria-valuemax="100"></div>
								</div>
								</p>
							</th>
						</table>
						<hr />
						<div style="padding-bottom: 5px;">
							<p id="commentDes">${post.getDescription()}</p>
							<div class="actionDiv">
								<a href="/editpost?id=${post.getPostId()}"><i
									class="fas fa-edit"></i></a> <a
									href="/deletepost?id=${post.getPostId()}"><i
									class="fas fa-trash"></i></a>
							</div>
							<div style="padding-top: 5px;">
								<p id="rating">
									<strong>${post.rating}</strong>
								</p>
								<a href="/createposts/upvote?id=${post.getPostId()}"> <img
									src="1338-thumbs-up-sign.png" alt="Up" height=20 width=20 /></a>
							</div>
						</div>
						<c:if test="${not empty post.getImageId()}">
							<!-- add version then / then id -->
							<cl:image
								src="http://res.cloudinary.com/bepositive/image/upload/w_250,h_250,c_fill/${post.getVersion()}/${post.getImageId()}" />
						</c:if>
						<div id="commentForm">
							<form action="/createcomments" method="post">
								<div>
									<input type="hidden" name="postId" value="${post.getPostId()}" />
									<input type=text name="comment" style="width: 100%"
										placeholder="Comment here!" required>
								</div>
								<div id="button">
									<input type="submit" value="Comment"
										class="btn btn-warning btn-sm">
								</div>
							</form>
						</div>
						<c:forEach var="comment" items="${post.getComments()}">
							<div class="commentDiv">
								<p id="commentDes">${comment.getDescription()}</p>
								<div class="actionDiv">
									<a href="/deletecomment?id=${comment.getCommentId()}"><i
										class="fas fa-trash"></i></a>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			<div class="col-sm-3" id="quoMoveDown">
				<c:if test="${not empty toneSummaries}">
					<table style="width: 100%">
						<tr>
							<td colspan=2><h2>User Tone Analysis Report</h2></td>
						</tr>
						<c:forEach var="toneSummary" items="${toneSummaries}">
							<tr>
								<td>${toneSummary.getTone()}</td>
								<td style="width: 100px"><c:set var="tonePercent"
										value="${toneSummary.getAverage() * 100}" />
									<div class="progress">
										<div class="progress-bar tone-${toneSummary.getTone()}"
											role="progressbar" style="width:${tonePercent}%"
											aria-valuenow="${tonePercent}" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div></td>
							</tr>
						</c:forEach>
					</table>
					<hr id="hrMovedown" />
				</c:if>
				<jsp:include page="glossary.jsp" />
			</div>
		</div>
	</div>
</body>
</html>