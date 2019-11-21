<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/webjars/org.webjars/bootstrap/4.3.1/css/bootstrap.css"
	rel="stylesheet">
<title>Result</title>
<%@include file="/WEB-INF/nav.jsp"%>
</head>
<body>
	<br>
	<div class="container jumbotron">

		<!-- ////////// -->
		<h1>
			Organizations Currently Helping Others with <strong>goodWork</strong>
		</h1>

		<br>
		<!-- <div class="row" align="left"> -->
		<div class="displayResults">
			<c:if test="${! empty displayAll}">
				<c:forEach items="${displayAll}" var="org">
					<%-- <a href="findOrgById.do?id=${org.id}">${org.orgName}</a> --%>
					<div>
						<hr>
						<h4 class="mb-1">
							<a href="findOrgById.do?id=${org.id}">${org.orgName}</a>
						</h4>
						<br>
						<p>
							<small class="text-muted">${org.orgDescription}</small>
						</p>
						<br> <small class="text-muted">${org.location.city},
							${org.location.state}</small> <br> <small class="text-muted">${org.orgType}</small>
						<br> <small class="text-muted"><a
							href="${org.website}" target="_blank">${org.website}</a></small>
					</div>

				</c:forEach>
			</c:if>
		</div>
		<br>
		<!-- /////Single Org///// -->
		<div>
			<c:if test="${! empty org}">
				<h1>Found it!</h1>
${org.orgName}<br>
				<br>
${org.location.address}<br>
				<br>
${org.location.city}<br>
				<br>
${org.location.state}<br>
				<br>
${org.orgType}<br>
				<br>
${org.orgNum}<br>
				<br>
${org.logoURL}<br>
				<br>
${org.website}<br>
				<br>
			</c:if>
		</div>
		<br>

		<!-- /////List of Users///// -->
		<div>
			<c:if test="${! empty users}">
				<h1>All Users:</h1>
				<c:forEach items="${users}" var="user">
${user.firstName}<br>
					<br>
${user.lastName}<br>
					<br>
${user.userName}<br>
					<br>
				</c:forEach>
			</c:if>
		</div>
		<br>
		<!-- /////Single User///// -->
		<div>
			<c:if test="${! empty user}">
				<h1>Found it!</h1>
${user.firstName}<br>
				<br>
${user.lastName}<br>
				<br>
${user.userName}<br>
				<br>
			</c:if>
			<br>
		</div>
		<br>
		<!-- ////////// -->
		<div>
			<c:if test="${! empty event}">
				<h1>Found it!</h1>
${event.eventName}<br>
				<br>
${event.description}<br>
				<br>
${event.eventDate}<br>
				<br>
${event.startTime}<br>
				<br>
${event.endTime}<br>
				<br>
${event.peopleNeeded}<br>
				<br>
			</c:if>
		</div>
		<br>


		<!-- ////////// -->

		<%-- 		<div>

			<c:if test="${! empty eventByCat}">
				<h1>All Events By Category:</h1>
				<c:forEach items="${eventByCat}" var="event">
${event.eventName}<br>
					<br>
${event.description}<br>
					<br>
${event.eventDate}<br>
					<br>
${event.startTime}<br>
					<br>
${event.endTime}<br>
					<br>
${event.peopleNeeded}<br>
					<br>
				</c:forEach>
			</c:if>
		</div> --%>

		<div>
			<h1>
				Events by Category with <strong>goodWork</strong>
			</h1>
			<c:if test="${! empty eventByCat}">
				<c:forEach items="${eventByCat}" var="event">
					<%-- <a href="findOrgById.do?id=${org.id}">${org.orgName}</a> --%>
					<div>
						<hr>
						<h4 class="mb-1">
							<a href="findEventByCategory.do?id=${event.id}">${event.eventName}</a>
						</h4>
						<br>
						<p>
							<small class="text-muted">${event.description}</small>
						</p>
						<br> <small class="text-muted">${event.eventDate}</small> <br>

					</div>

				</c:forEach>
			</c:if>
		</div>
		<!-- ////////// -->
		<%-- 		<div>
			<c:if test="${! empty eventByCat}">
				<h1>Found it!</h1>
${event.eventName}<br>
				<br>
${event.description}<br>
				<br>
${event.eventDate}<br>
				<br>
${event.startTime}<br>
				<br>
${event.endTime}<br>
				<br>
${event.peopleNeeded}<br>
				<br>
			</c:if>
		</div> --%>
		<br>
		<!-- /////Org Delete///// -->
		<div>
			<c:if test="${! empty successfulDelete}">
				<h1>Success!</h1>
				<h3>${successfulDelete}</h3>
			</c:if>

		</div>
		<!-- ////////// -->
	</div>
	<!-- </div> -->
	<hr>
	<footer class="text-center">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<p>Copyright © FluffyCarnage. All rights reserved.</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://cdn.jsdelivr.net/webjars/org.webjars.bower/jquery/3.3.1/dist/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="https://cdn.jsdelivr.net/webjars/org.webjars/popper.js/1.15.0/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/webjars/org.webjars/bootstrap/4.3.1/js/bootstrap.js"></script>
</body>
</html>