<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>CMS</title>
	<tiles:insertAttribute name="include" /> 	
</head>
<body onunload="">
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" 	style="margin-bottom: 0">	
	  		<tiles:insertAttribute name="header" /> 
	  		<tiles:insertAttribute name="menu" />
	  	</nav>
	  	<tiles:insertAttribute name="body" />
	  
	 </div>
	 
	  <tiles:insertAttribute name="footer" />
</body>
</html>