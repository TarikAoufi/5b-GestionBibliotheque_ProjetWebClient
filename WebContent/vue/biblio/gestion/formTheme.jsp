<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="fr.aoufi.entity.Theme"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" />
	<title>Gestion des themes</title>
	<link rel="stylesheet" href="<s:url namespace= "/vue" action="css" />" />
	<link rel="stylesheet" href="<s:url namespace= "/biblio" action="cssFormTheme" />" />
</head>
<body>
	<!--                                                       -->
	<!-- Ecrire le js pour le control de saisie ... longueur des champs, numéricité ...   -->
	<!--                                                       -->
	<jsp:include page="/WEB-INF/jsp/menuBiblio.jsp" />
	<p>Gestion des themes :</p>
	<s:form id="unTheme" action="afficheTheme" method="post" validate="false" theme="simple">
		<div id="d1">
			<p><s:label for="unTheme.id" value="Id :" /></p>
			<p><s:label for="unTheme.nom" value="Nom :" /></p>
			<p><s:label for="unTheme.description" value="Description :" /></p>
		</div>
		<div id="d2">
			<p><s:textfield 	name="unTheme.id" /></p>
			<p><s:textfield 	name="unTheme.nom" /></p>
			<p><s:textfield 	name="unTheme.description" /></p>
		</div>
		<div id="d4">	
			<!-- Creation des url de navigation -->
			<s:url namespace="/biblio" action="creationTheme" 		var="creaTheme" />
			<s:url namespace="/biblio" action="modificationTheme" 	var="modifTheme" />
			<s:url namespace="/biblio" action="suppressionTheme" 	var="supTheme" />
			<s:url namespace="/biblio" action="rechercheTheme" 		var="rechTheme" />
			
			<s:submit value="Reset" />
			<s:submit value="Creation" 		formaction="${creaTheme}" />
			<s:submit value="Modification" 	formaction="${modifTheme}" />
			<s:submit value="Suppression" 	formaction="${supTheme}" />
			<s:submit value="Rechercher" 	formaction="${rechTheme}" />	
		</div>
	</s:form>
	<!--  Include pour la gestion des messages d'erreurs utilisateurs -->
	<jsp:include page="/WEB-INF/jsp/msgErreur.jsp" />
</body>
</html>