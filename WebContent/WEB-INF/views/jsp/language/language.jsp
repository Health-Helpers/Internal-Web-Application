
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../header.jsp"></jsp:include>

<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>

<%@page import="cat.ehh.web.model.Language"%>
<%@page import="java.util.List"%>


<title><spring:message code="label.langMng" /></title>
</head>
<body>

	<%
		List<Language> languages = (List<Language>) session.getAttribute("languages");
	%>

	<div class="wrapper">
		<div class="container-fluid">
			<table class="table table-hover">
				<thead>
					<tr>
						<th><spring:message code="label.id" /></th>
						<th><spring:message code="label.code" /></th>
						<th><spring:message code="label.name" /></th>
						<th><span class="glyphicon glyphicon-pencil"></span></th>
						<th><span class="glyphicon glyphicon-trash"></span></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="language" items="${languages}">
						<tr>
							<td><c:out value="${language.languageId}" /></td>
							<td><c:out value="${language.code}" /></td>
							<td><c:out value="${language.name}" /></td>
							<td><a
								href="${pageContext.request.contextPath}/language/read?id=${language.languageId}"><spring:message
										code="label.editar" /></a></td>
							<td><a
								onclick="return confirm('<spring:message code="label.sure" />')"
								href="${pageContext.request.contextPath}/language/remove?id=${language.languageId}"><spring:message
										code="label.eliminar" /></a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>



		<form action="${pageContext.request.contextPath}/language/add"
			method="GET">
			<div class="centered">
				<input type="submit" class="btn btn-primary"
					value="<spring:message code="label.addLang" />" /> <a
					href="${pageContext.request.contextPath}"><input type="button"
					class="btn btn-primary"
					value="<spring:message code="label.return" />" /></a>
			</div>
		</form>

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