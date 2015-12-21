<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="cat.ehh.web.util.DateUtil"%>
<%@page import="cat.ehh.web.model.UserEHH"%>
<%@page import="cat.ehh.web.model.Language"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="../header.jsp"></jsp:include>
<%
if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
	UserEHH user = (UserEHH) request.getSession().getAttribute("user");
	List<Language> languages = (List<Language>) request.getSession().getAttribute("languages");
%>


<script src="//oss.maxcdn.com/bootbox/4.2.0/bootbox.min.js"></script>
<title>User Management</title>
</head>
<body>

	<%
		List<UserEHH> usuarios = (List<UserEHH>) session.getAttribute("usuarios");
	
	%>

	<div class="wrapper container">

		<div class="table-responsive">
			<table class="table table-bordered table-striped">
			<thead>
					<tr>
						<th><spring:message code="label.name" /></th>
						<th><spring:message code="label.phone" /></th>
						<th><span class="glyphicon glyphicon-pencil"></span></th>
						<th><span class="glyphicon glyphicon-trash"></span></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="user" items="${usuarios}">
					<tr>
						<td> <c:out value="${user.name}" />
						<td> <c:out value="${user.phone}" />
							
						</td>
						
						<td><button type="button" data-id="1" onclick="openModal(${user.userId});"  class="btn btn-default editButton">Edit</button></td>
						<td><button type="button" data-id="2" onclick="deleteUser(${user.userId})"  class="btn btn-default editButton">Delete</button></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>


			<form action="user/add" method="GET">

				<input onclick="openModalNew()" class="btn btn-primary" value="<spring:message code="label.addUser"/>" />
					<a href="${pageContext.request.contextPath}"><input type="button" class="btn btn-primary" value="<spring:message code="label.return"/>" /></a>
			</form>
			
			<!-- The form which is used to populate the item data -->
			<form id="userForm" method="post" class="form-horizontal" style="display: none;" action="user/edit">     
			    
			      <input type="hidden" class="form-control" name="id"/>
			    
			    <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.dni" /></label>
			        <div class="col-xs-5">
			            <input type="text" class="form-control" name="iddoc" />
			        </div>
			     </div>
			     
			     <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.name" /></label>
			        <div class="col-xs-5">
			            <input type="text" class="form-control" name="name"  />
			        </div>
			    </div>
			    
			     <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.surname" /></label>
			        <div class="col-xs-5">
			            <input type="text" class="form-control" name="surname" />
			        </div>
			    </div>
			    
			    <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.address" /></label>
			        <div class="col-xs-5">
			            <input type="text" class="form-control" name="adress" />
			        </div>
			    </div>
			    
			     <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.birthdate" /></label>
			        <div class="col-xs-5">
			            <input id="datepicker" type="text" class="form-control" name="birthdate" />
			        </div>
			    </div>
			    
			     <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.phone" /></label>
			        <div class="col-xs-5">
			            <input type="text" class="form-control" name="phone" />
			        </div>
			    </div>
			    
			     <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.language" /></label>
			        <div class="col-xs-5">
				          <select name="language" id="language">
							<c:forEach items="${languages}" var="language">
								<option value="${language.languageId}">${language.name}</option>
							</c:forEach>
						</select>
			        </div>
			    </div>
			    
			    
			    <fieldset>
						<legend>Tipo de Usuario</legend>
						<input type="radio" name="tipo" value="Paciente"/>
						<spring:message code="label.patient" />
						<br /> <input type="radio" name="tipo" value="Responsable" />
						<spring:message code="label.responsible" />
					</fieldset>
			
			
			    <div class="form-group">
			        <div class="col-xs-5 col-xs-offset-3">
			            <button type="submit" class="btn btn-default">Save</button>
			        </div>
			    </div>
			</form>


			
		</div>

	</div>
</body>

<script>
function deleteUser(idUser)
{
	 if(confirm("User will be deleted!")){
		$.ajax({
	        //url: 'UserController/readUser',
	        url: 'user/remove',
	        data:{
					id: idUser
	        },
	     method: 'GET'
	 }).success(function(response) {
		 alert("Delete Sucess!");
		 location.reload();
	 });
	 }
		
}
function openModal(idUser){

	//alert(id);

	$.ajax({
           //url: 'UserController/readUser',
           url: 'user/read2',
           data:{
				id: idUser
           },
        method: 'GET'
    }).success(function(response) {
    	
    	var json = response;
        obj = JSON.parse(json);                                    

        //alert(obj.User.userId);
        //alert(obj.User.tipo);      

         var birthDate = new Date(obj.User.birthdate);
        // Populate the form fields with the data returned from server
        $('#userForm')
       		.find('[name="id"]').val(obj.User.userId).end()
        	.find('[name="iddoc"]').val(obj.User.iddoc).end()
            .find('[name="name"]').val(obj.User.name).end()
            .find('[name="surname"]').val(obj.User.surname).end()
            .find('[name="adress"]').val(obj.User.adress).end()       
            .find('[name="birthdate"]').val((birthDate.getMonth() + 1) + "/" + birthDate.getDate() + "/" + birthDate.getFullYear()).end()
            .find('[name="phone"]').val(obj.User.phone).end()
            .find('[name="language"]').val(obj.User.language).end()
            .find('[name="tipo"]').val(obj.User.type).prop("checked", true).end();
            
        $('#userForm').get(0).setAttribute('action', 'user/edit'); //this works
        
        // Show the dialog
        bootbox
            .dialog({
                title: 'Edit User',
                message: $('#userForm'),
                show: false // We will show it manually later
            })
            .on('shown.bs.modal', function() {
                $('#userForm')
                    .show()                             // Show the login form
                    .formValidation('resetForm'); // Reset form
            })
            .on('hide.bs.modal', function(e) {
                // Bootbox will remove the modal (including the body which contains the login form)
                // after hiding the modal
                // Therefor, we need to backup the form
                $('#userForm').hide().appendTo('body');
            })
            .modal('show');
    });
	
}

function openModalNew(){

	  $('#userForm')
	  .find('[name="id"]').val("").end()
  	  .find('[name="iddoc"]').val("").end()
      .find('[name="name"]').val("").end()
      .find('[name="surname"]').val("").end()
      .find('[name="adress"]').val("").end()     
      .find('[name="birthdate"]').val("").end()
      .find('[name="phone"]').val("").end()
      .find('[name="language"]').val("").end()
      .find('[name="tipo"]').val("").attr('checked', 'checked').end();
		
	  $('#userForm').get(0).setAttribute('action', 'user/addUser'); //this works
        // Show the dialog
        bootbox
            .dialog({
                title: 'New User',
                message: $('#userForm'),
                show: false // We will show it manually later
            })
            .on('shown.bs.modal', function() {
                $('#userForm')
                    .show()                             // Show the login form
                    .formValidation('resetForm'); // Reset form
            })
            .on('hide.bs.modal', function(e) {
                // Bootbox will remove the modal (including the body which contains the login form)
                // after hiding the modal
                // Therefor, we need to backup the form
                $('#userForm').hide().appendTo('body');
            })
            .modal('show');
}
</script>
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
			dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Mi�rcoles', 'Jueves',
					'Viernes', 'S�bado' ],
			dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mi�', 'Juv', 'Vie', 'S�b' ],
			dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'S�' ],
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

</html>

<%
	} else {
%>
<jsp:include page="../notLogged.jsp"></jsp:include>
<%
	}
%>