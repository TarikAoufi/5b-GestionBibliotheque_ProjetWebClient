<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" />
	<title>Gestion des documents</title>
	<link rel="stylesheet" href="<s:url namespace= "/vue" action="css" />" />
	<link rel="stylesheet" href="<s:url namespace= "/biblio" action="cssFormDocument" />" />
</head>
<body>
<!--                                                       -->
<!-- Ecrire le js pour le control de saisie ... longueur des champs, numéricité ...   -->
<!--                                                       -->
	<jsp:include page="/WEB-INF/jsp/menuBiblio.jsp" />	
	<p> Gestion des documents : </p>
	<s:form id="document" name="document" action="afficheDocument" method="post" validate="false" theme="simple">
		<div id="d1">
			<p><s:label for="document.cote" 				value="Cote :" /></p>
			<p><s:label for="document.titre" 				value="Titre :" /></p>
			<p><s:label for="document.descriptif" 			value="Descriptif :" /></p>
			<br/>
			<p><s:label for="document.nbExemplaireDispo" 	value="Nb exemplaires dispo :" /></p>
			<p><s:label for="document.localisation.lieu" 	value="Lieu :" /></p>
			<p><s:label for="document.localisation.emp" 	value="Emplacement :" /></p>
			<p><s:label for="document.auteur" 				value="Auteur :" /></p>
			<p><s:label for="document.themes" 				value="Theme :" /></p>
		</div>
		<div id="d2">
			<p><s:textfield	name="document.cote" 				cssClass="../biblio" /></p> 
			<p><s:textfield name="document.titre" 				cssClass="../biblio" /></p>
			<p><s:textarea 	name="document.descriptif" 			cssClass="../biblio" cols="40" rows="3" /></p> 
			<p><s:textfield name="document.nbExemplaireDispo" 	cssClass="../biblio" /></p>
			<p><s:textfield name="document.localisation.lieu" 	cssClass="../biblio" /></p>
			<p><s:textfield name="document.localisation.emp" 	cssClass="../biblio" /></p>	
			<p><s:select 	name="selectionAuteur"    list="auteurs" 
							listKey="id" 			  listValue="nom" 
							headerKey="0"   	      headerValue="Choisissez..."   	
							label="Auteur " />
			</p>
			<p><s:select 	name="selectionTheme"     list="themes" 
							listKey="id" 			  listValue="nom" 
							headerKey="0"   	      headerValue="Choisissez..."   	
							label="Themes " 		  multiple="true" size="3" /> 
			</p>
		</div>
		<div id="d4">	
			<!-- Creation des url de navigation -->
			<s:url namespace="/biblio" action="resetDocument" 			var="resetDocument" />
			<s:url namespace="/biblio" action="creationDocument" 		var="creaDocument" />
			<s:url namespace="/biblio" action="modificationDocument" 	var="modifDocument" />
			<s:url namespace="/biblio" action="suppressionDocument" 	var="supDocument" />
			<s:url namespace="/biblio" action="rechercheDocument" 		var="rechDocument" />
			
			<s:submit value="Reset"  		formaction="${resetDocument}" />
			<s:submit value="Creation" 		formaction="${creaDocument}" />
			<s:submit value="Modification" 	formaction="${modifDocument}" />
			<s:submit value="Suppression" 	formaction="${supDocument}" />
			<s:submit value="Rechercher" 	formaction="${rechDocument}" />	
		</div>
	</s:form>
	<!--  Include pour la gestion des messages d'erreurs utilisateurs -->
	<jsp:include page="/WEB-INF/jsp/msgErreur.jsp" />
</body>
</html>