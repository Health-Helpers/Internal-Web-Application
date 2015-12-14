<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="header.jsp"></jsp:include>

 <%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>
<html>

<head>
<title>Welcome to EHH Management!</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


</head>

<body>

	<!-- <div class="wrapper">
		<div class="container">
			<a href="user"><button class="btn btn-primary">
					<spring:message code="label.mngUsers" />
				</button></a> <br />
			<br /> <a href="language"><button class="btn btn-primary">
					<spring:message code="label.mngLang" />
				</button></a>
		</div>
	</div>
	 -->
</body>
</html>

<%
	} else {
%>
<jsp:include page="notLogged.jsp"></jsp:include>
<% }%>

