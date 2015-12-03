<%@page import="java.util.List"%>
<%@page import="cat.ehh.web.model.UserEHH"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Management</title>
</head>
<body>

<%
List<UserEHH> usuarios = (List<UserEHH>)session.getAttribute("usuarios");
%>



<table>
<c:forEach var="user" items="${usuarios}">
	<tr>
		<td>
			<c:out value="${user.name}"/>
		</td>
		<td><a href="user/read?id=${user.userId}">Editar</a></td>
		<td><a onclick="alert('Cuidao!')" href="user/remove?id=${user.userId}">Eliminar</a></td>
		
	</tr>
</c:forEach>
</table>




<form action="user/add" method="GET">

<input type="submit" value="Añadir Usuario"/>

</form>



</body>
</html>