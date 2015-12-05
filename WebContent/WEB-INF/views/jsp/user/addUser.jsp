<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../header.jsp"></jsp:include>

<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>


  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });

  $(function($){
	    $.datepicker.regional['es'] = {
	        closeText: 'Cerrar',
	        prevText: '<Ant',
	        nextText: 'Sig>',
	        currentText: 'Hoy',
	        monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
	        monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
	        dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
	        dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
	        dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
	        weekHeader: 'Sm',
	        dateFormat: 'dd/mm/yy',
	        firstDay: 1,
	        isRTL: false,
	        showMonthAfterYear: false,
	        yearSuffix: ''
	    };
	    $.datepicker.setDefaults($.datepicker.regional['es']);
	});
  </script>

<title>Add User</title>


</head>


<fieldset>
	<legend>Formulario Alta</legend>
	<form class="formularioAlta" action="addUser" method="POST">
		DNI: <input type="text" name="iddoc" /><br /> 
		Nombre:	<input type="text" name="nombre" /><br /> 
		Apellidos: <input type="text" name="surname" /><br /> 
		Fecha de nacimiento: <input type="text" name="birthdate" id="datepicker"/><br /> 
		Teléfono: <input type="text" name="phone" /><br />
		<fieldset>
			<legend>Tipo de Usuario</legend>
			<input type="radio" name="tipo" value="Paciente" />Paciente <br/>
			 <input type="radio" name="tipo" value="Responsable" /> Responsable

		</fieldset>

		<input type="submit" value="Dar de alta" />

	</form>

</fieldset>



</body>
</html>


<%
	} else {
%>
<jsp:include page="../notLogged.jsp"></jsp:include>
<%
	}
%>