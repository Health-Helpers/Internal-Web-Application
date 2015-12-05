<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	rel="stylesheet">

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<title><spring:message code="label.notLogged" /></title>
</head>
<body>
	<div class="wrapper">
		<div class="container-fluid">
			<div class="span12 centered">
				<div class="centeredChuck">
					<h2>
						<spring:message code="label.sorry" />
					</h2>
					<h3>
						<spring:message code="label.sorryExp" />
					</h3>
					<img style=" display:block;margin:auto;"
						src="${pageContext.request.contextPath}/resources/img/chuckNorris.png" />


					<h2>
						<a href="${pageContext.request.contextPath}"><spring:message
								code="label.goLogin" /></a>
					</h2>
				</div>
			</div>
		</div>

	</div>
</body>
</html>