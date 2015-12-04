<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="header.jsp"></jsp:include>

<title>Welcome to EHH Management!</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<a href="user"><button class="btn btn-primary"><spring:message code="label.mngUsers" /></button></a> <a href="language"><br />
			<br />
				<button class="btn btn-primary"><spring:message code="label.mngLang" /></button></a>
		</div>
	</div>
</body>
</html>