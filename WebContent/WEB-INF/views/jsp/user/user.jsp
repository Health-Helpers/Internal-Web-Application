<%@page import="java.util.List"%>
<%@page import="cat.ehh.web.model.UserEHH"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../header.jsp"></jsp:include>

<title>User Management</title>
</head>
<body>

	<%
		List<UserEHH> usuarios = (List<UserEHH>) session.getAttribute("usuarios");
	%>

	<div class="wrapper">

		<div class="container">
			<table class="table table-hover">
				<c:forEach var="user" items="${usuarios}">
					<tr>
						<td><c:out value="${user.name}" /></td>
						<td><a href="user/read?id=${user.userId}">Editar</a></td>
						<td><a onclick="alert('Cuidao!')"
							href="user/remove?id=${user.userId}">Eliminar</a></td>

					</tr>
				</c:forEach>
			</table>


			<a href="user/add"><button class="btn btn-primary">Añadir
					Usuario</button></a>
		</div>
	</div>
</body>
</html>