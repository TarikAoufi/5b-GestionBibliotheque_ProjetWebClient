<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Accueil Biblio</title>
<link href="<%=request.getContextPath()%>/css/biblio.css"
	rel="stylesheet" type="text/css" />
</head>
<body>

	<!--  Include pour la gestion des messages d'erreurs utilisateurs -->
	<jsp:include page="../../WEB-INF/jsp/msgErreur.jsp" />
	<jsp:include page="../../WEB-INF/jsp/menuBiblio.jsp" />

	<h1>Bienvenue sur La Biblioth&egrave;que</h1>
	<p>Attention : ouvrir avec firefox pour la prise en charge de html5</p>
	<p>A Faire :</p>
	<ul>
		<li>dans rechercher document : selection themes</li>
		<li>dans modifier document : prendre en compte les themes</li>
		<li>prendre en compte la date de naissance de l'auteur</li>
		<li>Passer en html5</li>
		<li>Revoir le menu</li>
		<li>...</li>
	</ul>
	<p>Fin prevue : 2029 !!!</p>	
</body>
</html>