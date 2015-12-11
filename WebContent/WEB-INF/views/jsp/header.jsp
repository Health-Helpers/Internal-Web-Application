<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>

<head>
  <title>Elder Health Helper</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/css/styles.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

  <!-- Theme Made By www.w3schools.com - No Copyright -->


<!--   <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css"> -->
<!--   <link href="http://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css"> -->
  <link href="${pageContext.request.contextPath}/resources/css/index_styles.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/resources/css/modal_login.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico" type="image/x-icon"/>


  


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/modernizr.js"></script>

<script type="text/javascript">
	function confirmDeletion(label){
			return  confirm(label);
		}
</script>