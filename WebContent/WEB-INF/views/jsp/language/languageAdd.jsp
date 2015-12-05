<%@page import="cat.ehh.web.model.Language"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../header.jsp"></jsp:include>

<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>

<title><spring:message code="label.newLang" /></title>
</head>
<body>
	<div class="wrapper">
		<div class="container-fluid">
			<form class="form-horizontal" role="form"
				action="${pageContext.request.contextPath}/language/addLanguage"
				method="post">


				<div class="form-group">
					<label class="control-label col-sm-2" for="codigo"><spring:message
							code="label.code" />:</label>
					<div class="col-sm-1">
						<input type="text" class="form-control" id="codigo" name="codigo"
							placeholder="<spring:message code="label.code" />" required
							pattern="[A-Za-z]{2}_[A-Za-z]{2}"
							title="<spring:message code="label.localePattern" />" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="nombre"><spring:message
							code="label.name" />:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="nombre" name="nombre"
							placeholder="<spring:message code="label.name"  />" required />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<spring:message code="label.save" />
						</button>
						<button onclick="javascript:history.back();" type="button"
							class="btn btn-primary">
							<spring:message code="label.return" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>

<%
	} else {
%>
<jsp:include page="../notLogged.jsp"></jsp:include>
<%
	}
%>