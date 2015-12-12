<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="cat.ehh.web.util.DateUtil"%>
<%@page import="cat.ehh.web.model.UserEHH"%>
<%@page import="cat.ehh.web.model.Language"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"></jsp:include>
<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
		UserEHH user = (UserEHH) request.getSession().getAttribute("user");
		List<Language> languages = (List<Language>) request.getSession().getAttribute("languages");
%>


<title>User Edit</title>
</head>
<body>
 <jsp:include page="../menu.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container-fluid">
			<fieldset>
				<legend>Formulario edición</legend>
				<form class="formularioAlta" action="editUser" method="POST">
					<spring:message code="label.dni" />
					: <input type="text" name="iddoc" value="<%=user.getIddoc()%>" /><br />
					<spring:message code="label.name" />
					: <input type="text" name="nombre" value="<%=user.getName()%>" /><br />
					<spring:message code="label.surname" />
					: <input type="text" name="surname"
						value="<%=user.getSurname()%>" /><br />
					<spring:message code="label.birthdate" />
					: <input type="text" name="birthdate" id="datepicker"
						value="<%=DateUtil.getStringFromDate(user.getBirthdate())%>" /><br />
					<spring:message code="label.address" />
					: <input type="text" name="direccion"
						value="<%=user.getAdress()%>" /> <br />
					<spring:message code="label.phone" />
					: <input type="text" name="phone" value="<%=user.getPhone()%>" /><br />
					<spring:message code="label.language" />
					: <select name="language">
						<c:forEach items="${languages}" var="language">
							<c:choose>
								<c:when test="${language.languageId eq user.langid}">
									<option value="${language.languageId}" selected>${language.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${language.languageId}">${language.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<fieldset>
						<legend>Tipo de Usuario</legend>
						<input type="radio" name="tipo" value="Paciente"
							<%=user.getType().equals(new Integer("0")) ? "checked" : ""%> />
						<spring:message code="label.patient" />
						<br /> <input type="radio" name="tipo" value="Responsable"
							<%=user.getType().equals(new Integer("1")) ? "checked" : ""%> />
						<spring:message code="label.responsible" />

					</fieldset>

					<input class="btn btn-primary" type="submit"
						value="<spring:message
		code="label.saveChanges" />" />
					<button onclick="javascript:history.back();" type="button"
						class="btn btn-primary">
						<spring:message code="label.return" />
					</button>
				</form>

			</fieldset>
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