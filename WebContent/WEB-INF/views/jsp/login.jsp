<jsp:include page="header.jsp"></jsp:include>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<title>Login Page</title>
</head>
<body>

	<div class="wrapper">
		<div class="container-fluid">
			<form action="login" method="post">

				<spring:message code="label.userName" />
				<input type="text" name="username" required />
				<spring:message code="label.password" />
				<input type="password" name="password" required /> <input
					type="submit" class="btn btn-primary" />
			</form>
		</div>
	</div>
</body>
</html>