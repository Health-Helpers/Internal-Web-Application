<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<title>No se ha autenticado</title>
</head>
<body>
	<div class="wrapper">
		<div class="container-fluid">
			<div class="span12">
				<h1>Lo siento, para acceder a la aplicación es necessario
					autenticarse</h1>
				<div style="margin-left: 10%">
					<img
						src="${pageContext.request.contextPath}/resources/img/chuckNorris.png" />
				</div>
				<div style="margin-left: 25%">
					<h2>
						<a href="${pageContext.request.contextPath}">Identificarme</a>
					</h2>
				</div>
			</div>
		</div>

	</div>
</body>
</html>