<%@page import="java.util.List"%>
<%@page import="cat.ehh.web.model.UserEHH"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="../header.jsp"></jsp:include>
<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>



<title>User Management</title>
</head>
<body>

	<%
		List<UserEHH> usuarios = (List<UserEHH>) session.getAttribute("usuarios");
	%>

	<div class="wrapper">

		<div class="container-fluid">
			<table class="table table-hover">
			<thead>
					<tr>
						<th><spring:message code="label.name" /></th>
						<th><span class="glyphicon glyphicon-pencil"></span></th>
						<th><span class="glyphicon glyphicon-trash"></span></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="user" items="${usuarios}">
					<tr>
						<td><c:out value="${user.name}" /></td>
						<td><a href="user/read?id=${user.userId}"><spring:message code="label.editar"/></a></td>
						<td><a
								onclick="return confirmDeletion('<spring:message
									code="label.sure" />');"
								href="${pageContext.request.contextPath}/user/remove?id=${user.userId}"><spring:message
										code="label.eliminar" /></a></td>

					</tr>
				</c:forEach>
				</tbody>
			</table>





			<form action="user/add" method="GET">

				<input type="submit" class="btn btn-primary" value="<spring:message code="label.addUser"/>" />
					<a href="${pageContext.request.contextPath}"><input type="button" class="btn btn-primary" value="<spring:message code="label.return"/>" /></a>
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