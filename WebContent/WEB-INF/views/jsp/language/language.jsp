
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../header.jsp"></jsp:include>

<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>

<%@page import="cat.ehh.web.model.Language"%>
<%@page import="java.util.List"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet">
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//oss.maxcdn.com/bootbox/4.2.0/bootbox.min.js"></script>
<title><spring:message code="label.langMng" /></title>


</head>
<body>

	<%
		List<Language> languages = (List<Language>) session.getAttribute("languages");
	%>
	
	<div class="wrapper container">

		<div class="table-responsive">
			<table class="table table-bordered table-striped">
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
							
							
							
						<td><button type="button" data-id="1" onclick="openModal(${language.languageId});"  class="btn btn-default editButton">Edit</button></td>
						<td><button type="button" data-id="2" onclick="deleteLang(${language.languageId})"  class="btn btn-default editButton">Delete</button></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>

			<form action="${pageContext.request.contextPath}/language/add" method="GET">

				<input onclick="openModalNew()" class="btn btn-primary" value="<spring:message code="label.addLang"/>" />
					<a href="${pageContext.request.contextPath}"><input type="button" class="btn btn-primary" value="<spring:message code="label.return"/>" /></a>
			</form>
			
			<!-- The form which is used to populate the item data -->
			<form id="langForm" method="post" class="form-horizontal" style="display: none;" action="language/editLanguage">
			 			     
			    <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.id" /></label>
			        <div class="col-xs-5">
			            <input type="text" class="form-control" name="id" />
			        </div>
			    </div>
			    
			     <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.code" /></label>
			        <div class="col-xs-5">
			            <input type="text" class="form-control" name="code" />
			        </div>
			    </div>
			     
			     <div class="form-group">
			        <label class="col-xs-3 control-label"><spring:message code="label.name" /></label>
			        <div class="col-xs-5">
			            <input type="text" class="form-control" name="name"  />
			        </div>
			    </div>
			
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
function deleteLang(idLang)
{
	 alert("Language will be deleted!");
		$.ajax({
	        //url: 'UserController/readUser',
	        url: 'language/remove',
	        data:{
					id: idLang
	        },
	     method: 'GET'
	 }).success(function(response) {
		 alert("Delete Sucess!");
	 });
		
}
function openModal(idLang){

	//alert(id);

	$.ajax({
           url: 'language/read2',
           data:{
				id: idLang
           },
        method: 'GET'
    }).success(function(response) {

    	var json = response;
        obj = JSON.parse(json);                                    
        
        // Populate the form fields with the data returned from server
        $('#langForm')
        	.find('[name="id"]').val(obj.Lang.languageId).end()
        	.find('[name="code"]').val(obj.Lang.code).end()
            .find('[name="name"]').val(obj.Lang.name).end()
      
            
        $('#langForm').get(0).setAttribute('action', 'language/editLanguage'); //this works
        
        // Show the dialog
        bootbox
            .dialog({
                title: 'Edit Language',
                message: $('#langForm'),
                show: false // We will show it manually later
            })
            .on('shown.bs.modal', function() {
                $('#langForm')
                    .show()                             // Show the login form
                    .formValidation('resetForm'); // Reset form
            })
            .on('hide.bs.modal', function(e) {
                // Bootbox will remove the modal (including the body which contains the login form)
                // after hiding the modal
                // Therefor, we need to backup the form
                $('#langForm').hide().appendTo('body');
            })
            .modal('show');
    });
	
}

function openModalNew(){

	  $('#langForm')
	  .find('[name="id"]').val("").end()
  	  .find('[name="code"]').val("").end()
      .find('[name="name"]').val("").end()
		
	  $('#langForm').get(0).setAttribute('action', 'language/addLanguage'); //this works
        // Show the dialog
        bootbox
            .dialog({
                title: 'New Language',
                message: $('#langForm'),
                show: false // We will show it manually later
            })
            .on('shown.bs.modal', function() {
                $('#langForm')
                    .show()                             // Show the login form
                    .formValidation('resetForm'); // Reset form
            })
            .on('hide.bs.modal', function(e) {
                // Bootbox will remove the modal (including the body which contains the login form)
                // after hiding the modal
                // Therefor, we need to backup the form
                $('#langForm').hide().appendTo('body');
            })
            .modal('show');
}
	



</script>


</html>

<%
	} else {
%>
<jsp:include page="../notLogged.jsp"></jsp:include>
<%
	}
%>