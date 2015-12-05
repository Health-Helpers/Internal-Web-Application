<jsp:include page="../header.jsp"></jsp:include>
<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>


<title>User Edit</title>
</head>
<body>

</body>
</html>

<%
	} else {
%>
<jsp:include page="../notLogged.jsp"></jsp:include>
<%
	}
%>