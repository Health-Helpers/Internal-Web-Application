<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/css/index_styles.css"	rel="stylesheet">
<link rel="stylesheet"	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script type="text/javascript">
	function confirmDeletion(label){
			return  confirm(label);
		}
</script>

<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>

</head>
<body data-target=".navbar">

<nav class="navbar navbar-default navbar-fixed-top" id="main-nav">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a  href="index">
      	<img src="resources/img/ic_EHH_72_48.png" />
      </a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav" >
        <li><a href="welcome">EHH</a></li>
      </ul>
      <ul class="nav navbar-nav" >
        <li><a href="user">USER</a></li>
        <li><a href="language">LANGUAGE</a></li>
      </ul>
      <ul class="nav navbar-nav  navbar-right" >
        <li><a href="index">admin</a></li>
      </ul>
    </div>
  </div>
</nav>



</body>
</html>
