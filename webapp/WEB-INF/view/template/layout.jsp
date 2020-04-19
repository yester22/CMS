<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	 <title>Dron Management System</title>
	<tiles:insertAttribute name="include" /> 	
</head>
<body class="hold-transition sidebar-mini">
	<div id="wrapper">
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">	
	  		<tiles:insertAttribute name="header" /> 
	  	</nav>
	  	<aside class="main-sidebar sidebar-dark-primary elevation-4">
		  	<tiles:insertAttribute name="menu" />
  		</aside>
  		
	  	<tiles:insertAttribute name="body" />
	  
	 </div>
	 
	  <tiles:insertAttribute name="footer" />
</body>
</html>