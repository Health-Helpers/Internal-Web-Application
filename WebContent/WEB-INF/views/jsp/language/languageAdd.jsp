<%@page import="cat.ehh.web.model.Language"%>
<jsp:include page="../header.jsp"></jsp:include>


<title>Nuevo idioma</title>
</head>
<body>
	<div class="wrapper">
		<form class="form-horizontal" role="form"
			action="${pageContext.request.contextPath}/language/addLanguage"
			method="post">


			<div class="form-group">
				<label class="control-label col-sm-2" for="codigo">Código:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="codigo" name="codigo"
						placeholder="Código">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="nombre">Nombre:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="nombre" name="nombre"
						placeholder="Nombre">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Guardar</button>
					<button onclick="javascript:history.back();" type="button"
						class="btn btn-primary">Volver</button>
				</div>
			</div>
		</form>

	</div>
</body>
</html>