<%@page import="cat.ehh.web.model.Language"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="../header.jsp"></jsp:include>


<title><spring:message code="label.newLang" /></title>
</head>
<body>
	<div class="wrapper">
		<form class="form-horizontal" role="form"
			action="${pageContext.request.contextPath}/language/addLanguage"
			method="post">


			<div class="form-group">
				<label class="control-label col-sm-2" for="codigo"><spring:message code="label.code" />:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="codigo" name="codigo"
						placeholder="<spring:message code="label.code" />">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="nombre"><spring:message code="label.name" />:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="nombre" name="nombre"
						placeholder="<spring:message code="label.name" />">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary"><spring:message code="label.save" /></button>
					<button onclick="javascript:history.back();" type="button"
						class="btn btn-primary"><spring:message code="label.return" /></button>
				</div>
			</div>
		</form>

	</div>
</body>
</html>