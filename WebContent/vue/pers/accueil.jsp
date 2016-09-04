<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Accueil Personnel</title>
<link href="<%=request.getContextPath() %>/css/biblio.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<!--  Include pour la gestion des messages d'erreurs utilisateurs -->
	<jsp:include page="/WEB-INF/jsp/msgErreur.jsp" />
	<jsp:include page="/WEB-INF/jsp/menuPers.jsp" />
	
<h1>Bienvenue sur La gestion du personnel</h1>
</body>
</html>