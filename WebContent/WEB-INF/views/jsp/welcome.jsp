<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="header.jsp"></jsp:include>

<%
	if (session.getAttribute("username") != null && !session.getAttribute("username").equals("")) {
%>

<title>Welcome to EHH Management!</title>
</head>
<body>

  <jsp:include page="menu.jsp"></jsp:include>
  
	  <!-- Page Content -->
        <div class="wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h2><spring:message
							code="label.welcome" /></h2>
                        <p><spring:message
							code="label.welcomeExp" /></p>
                    </div>
                </div>
            </div>
        </div>
 <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    </script>

</body>
</html>

<%
	} else {
%>
<jsp:include page="notLogged.jsp"></jsp:include>
<% }%>

