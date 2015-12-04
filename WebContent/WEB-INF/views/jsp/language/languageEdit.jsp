<%@page import="cat.ehh.web.model.Language"%>
<jsp:include page="../header.jsp"></jsp:include>


<%
	Language language = (Language) request.getSession().getAttribute("language");
%>

<title>Editing <%=language.getName()%></title>
</head>
<body>
	<div class="wrapper">
		<form class="form-horizontal" role="form" action="editLanguage"
			method="post">

			<input type="hidden" name="id" id="id"
				value="<%=language.getLanguageId()%>">

			<div class="form-group">
				<label class="control-label col-sm-1" for="codigo">Código:</label>
				<div class="col-sm-1">
					<input type="text" class="form-control" id="codigo" name="codigo"
						placeholder="<%=language.getCode()%>"
						value="<%=language.getCode()%>">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-1" for="nombre">Nombre:</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="nombre" name="nombre"
						placeholder="<%=language.getName()%>"
						value="<%=language.getName()%>">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-3">
					<button type="submit" class="btn btn-primary">Guardar
						Cambios</button>
					<button onclick="javascript:history.back();" type="button" class="btn btn-primary">Volver</button>
				</div>
			</div>
		</form>

	</div>
</body>
</html>