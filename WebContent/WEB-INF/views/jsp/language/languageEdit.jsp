<%@page import="cat.ehh.web.model.Language"%>
<jsp:include page="../header.jsp"></jsp:include>


<%
	Language language = (Language) request.getSession().getAttribute("language");
%>

<title>Editing <%=language.getName()%></title>
</head>
<body>

	<form class="form-horizontal" role="form" action="editLanguage" method="post">
	
	<input type="hidden"  name="id" id="id" value="<%=language.getLanguageId()%>">
					
		<div class="form-group">
			<label class="control-label col-sm-2" for="codigo">Código:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="codigo" name="codigo"
					placeholder="<%=language.getCode()%>" value="<%=language.getCode()%>">
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2" for="nombre">Nombre:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="nombre" name="nombre"
					placeholder="<%=language.getName()%>" value="<%=language.getName()%>">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Guardar Cambios</button>
			</div>
		</div>
	</form>


</body>
</html>