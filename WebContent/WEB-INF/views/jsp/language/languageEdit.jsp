<%@page import="cat.ehh.web.model.Language"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../header.jsp"></jsp:include>


<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>


<%
	Language language = (Language) request.getSession().getAttribute("language");
%>

<title><spring:message code="label.editing" /> <%=language.getName()%>

</title>
</head>
<body>
 <jsp:include page="../menu.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container-fluid">
			<form class="form-horizontal" role="form" action="editLanguage"
				method="post">

				<input type="hidden" name="id" id="id"
					value="<%=language.getLanguageId()%>">

				<div class="form-group">
					<label class="control-label col-sm-1" for="codigo"><spring:message
							code="label.code" />:</label>
					<div class="col-sm-1">
						<input type="text" class="form-control" id="codigo" name="codigo"
							placeholder="<%=language.getCode()%>"
							value="<%=language.getCode()%>" required
							pattern="[A-Za-z]{2}_[A-Za-z]{2}"
							title="<spring:message code="label.localePattern" />" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-1" for="nombre"><spring:message
							code="label.name" />:</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="nombre" name="nombre"
							placeholder="<%=language.getName()%>"
							value="<%=language.getName()%>" required />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-3">
						<button type="submit" class="btn btn-primary">
							<spring:message code="label.saveChanges" />
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