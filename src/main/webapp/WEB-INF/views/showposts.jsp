<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<!-- style="background-color: #ccc" -->
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
			<div class="col-sm-3" id="quoMoveDown">
				<h2>Quote</h2>
				<blockquote class="blockquote">
					<p>${list.quoteText}</p>
					<p>- ${list.quoteAuthor}</p>
				</blockquote>
			</div>
			<div class="col-sm-6">
				<c:if test="${not empty postError}">
					<div class="alert alert-danger" role="alert">${postError}</div>
				</c:if>
				<div class="postDiv">
					<form action="createposts" method="post">
						<div>
							<textarea id="myTextArea" rows="4" cols="65" name="post"
								placeholder="Speak Your Mind!"></textarea>
						</div>
						<div id="button">
							<input type="submit" value="Post" class="btn btn-warning">
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
						<p>${post.getDescription()}</p>
						<div id="commentForm">
							<form action="/createcomments" method="post">
								<div>
									<input type="hidden" name="postId" value="${post.getPostId()}" />
									<a href= "/deletepost?id=${post.getPostId()}">Delete</a>
									<input type=text name="comment" style="width: 100%"
										placeholder="Comment here!">
								</div>
								<div id="button">
								<a href="/createposts/upvote?id=${post.getPostId()}">
								<img
								src="1338-thumbs-up-sign.png" alt="Up" border=3 height=25
								width=25 /></a>
									<input type="submit" value="Comment"
										class="btn btn-warning btn-sm">
								</div>
							</form>
						</div>
						<c:forEach var="comment" items="${post.getComments()}">
							<div class="commentDiv">
								<p>${comment.getDescription()}</p>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			<div class="col-sm-3" id="quoMoveDown">
			<table border=1>
			<tr>
						<td>Tones</td>
						<td>Description</td>
					</tr>
					<tr>
					<td>Fear</td>
					<td>Fear is a response to impending danger. It is a survival mechanism that is triggered as a reaction to some negative stimulus. Fear can be a mild caution or an extreme phobia. (An emotional tone.)</td>
					<tr>
					<td>Joy</td>
					<td>Joy (or happiness) has shades of enjoyment, satisfaction, and pleasure. Joy brings a sense of well-being, inner peace, love, safety, and contentment. (An emotional tone.)
					</td></tr>	
					<tr>
					<td>Sadness</td>
					<td>Sadness indicates a feeling of loss and disadvantage. When a person is quiet, less energetic, and withdrawn, it can be inferred that they feel sadness. (An emotional tone.)
					</td></tr>		
					<tr>
					<td>Analytical</td>
					<td>An analytical tone indicates a person's reasoning and analytical attitude about things. An analytical person might be perceived as intellectual, rational, systematic, emotionless, or impersonal. (A language tone.)
					</td></tr>		
					<tr>
					<td>Confident</td>
					<td>A confident tone indicates a person's degree of certainty. A confident person might be perceived as assured, collected, hopeful, or egotistical. (A language tone.)
					</td></tr>
					<tr>
					<td>Tentative</td>
					<td>A tentative tone indicates a person's degree of inhibition. A tentative person might be perceived as questionable, doubtful, or debatable. (A language tone.)
					</td>
					</tr>					
			</table>
				<table style="width: 200px">
					<tr>
						<td colspan=2><h6>User Tone Analysis Report</h6></td>
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
			</div>
		</div>
	</div>
</body>
</html>