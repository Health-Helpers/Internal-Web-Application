<jsp:include page="header.jsp"></jsp:include>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<title>Login Page</title>
</head>
<body>
<%
if(request.getSession().getAttribute("badLogin")!=null){
	request.getSession().removeAttribute("badLogin");
%>
	<div id="badLogin" class="badLogin">
		<h3>Datos no correctos</h3>
	</div>
	
	<script type="text/javascript">
		setTimeout(function(){
			jQuery("#badLogin").hide();},2000);
	</script>

<%} %>

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