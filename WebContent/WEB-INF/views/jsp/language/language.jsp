<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../header.jsp"></jsp:include>

<%@page import="cat.ehh.web.model.Language"%>
<%@page import="java.util.List"%>


<title>Languages Management</title>
</head>
<body>

	<%
		List<Language> languages = (List<Language>) session.getAttribute("languages");
	%>

	<div class="wrapper">
		<div class="container">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Código</th>
						<th>Nombre</th>
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
								href="${pageContext.request.contextPath}/language/read?id=${language.languageId}">Editar</a></td>
							<td><a
								onclick="return confirm('Está seguro?')" href="${pageContext.request.contextPath}/language/remove?id=${language.languageId}">Eliminar</a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>



		<form action="${pageContext.request.contextPath}/language/add"
			method="GET">
			<div class="centered">
				<input type="submit" class="btn btn-primary" value="Añadir Idioma" />
				<a href="${pageContext.request.contextPath}"><input type="button" class="btn btn-primary" value="Volver" /></a>
			</div>
		</form>
	</div>
</body>
</html>