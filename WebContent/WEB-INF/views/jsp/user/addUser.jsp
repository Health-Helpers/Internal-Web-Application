<%@page import="cat.ehh.web.model.Language"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../header.jsp"></jsp:include>

<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>


<script>
	$(function() {
		$("#datepicker").datepicker();
	});

	$(function($) {
		$.datepicker.regional['es'] = {
			closeText : 'Cerrar',
			prevText : '<Ant',
	        nextText: 'Sig>',
			currentText : 'Hoy',
			monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo',
					'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre',
					'Noviembre', 'Diciembre' ],
			monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun',
					'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
			dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves',
					'Viernes', 'Sábado' ],
			dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mié', 'Juv', 'Vie', 'Sáb' ],
			dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá' ],
			weekHeader : 'Sm',
			dateFormat : 'dd/mm/yy',
			firstDay : 1,
			isRTL : false,
			showMonthAfterYear : false,
			yearSuffix : ''
		};
		$.datepicker.setDefaults($.datepicker.regional['es']);
	});
</script>

<title>Add User</title>


</head>

<body>
 <jsp:include page="../menu.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container-fluid">
			<fieldset>
				<legend>Formulario Alta</legend>
				<form class="formularioAlta" action="addUser" method="POST">
					<label  for="iddoc"><spring:message
							code="label.dni" />:</label> <input type="text" name="iddoc" id="iddoc" required/><br />
					<label  for="name"><spring:message
							code="label.name" />:</label> <input type="text" name="nombre" id="name" /><br />
					<label  for="surname"><spring:message
							code="label.surname" />:</label> <input type="text" name="surname"
						id="surname" /><br /> <label 
						for="datepicker"><spring:message code="label.birthdate" />:</label>
					<input type="text" name="birthdate" id="datepicker" /><br /> <label
						 for="address"><spring:message
							code="label.address" />:</label> <input type="text" name="direccion"
						id="address" /> <br /> <label 
						for="phone"><spring:message code="label.phone" />:</label> <input
						type="text" name="phone" id="phone" required /><br /> <label
						 for="language"><spring:message
							code="label.language" />:</label> <select name="language" id="language">
						<c:forEach items="${languages}" var="language">
							<option value="${language.languageId}">${language.name}</option>
						</c:forEach>
					</select>
					<fieldset>
						<legend>Tipo de Usuario</legend>
						<input type="radio" name="tipo" value="Paciente" />
						<spring:message code="label.patient" />
						<br /> <input type="radio" name="tipo" value="Responsable" />
						<spring:message code="label.responsible" />

					</fieldset>

					<input class="btn btn-primary" type="submit"
						value="<spring:message code="label.save" />" />
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