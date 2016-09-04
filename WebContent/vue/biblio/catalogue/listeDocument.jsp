<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" />
	<title>Liste des documents</title>
	<link rel="stylesheet" href="<s:url namespace= "/vue" action="css" />" />
	<link rel="stylesheet" href="<s:url namespace= "/biblio" action="cssCatalogue" />" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menuBiblio.jsp" />
	<p>Liste des documents :</p>
	<!--  theme="simple" : Struts ne genere pas les <table>, <tr>, <td> (qu'il ajoute par defaut) -->
	<s:form id="document"  method="post" action="detailCatalogue" theme="simple" > 
		<table class="liste">
		<tr>
			<th>D&eacute;tail</th>
			<th>Cote</th>
			<th>Titre</th>
			<th>Description</th>
			<th>Exemplaires Dispo</th>
		</tr>		
        <s:iterator value="listeDocuments">
        <tr id="<s:property value="cote"/>">
			<td class="radio"><s:radio	  name="cote" label="." list="{cote}" /></td>
			<td class="cote"><s:property value="cote"/></td>
			<td class="titre"><s:property value="titre"/></td>
			<td class="desc"><s:property value="descriptif"/></td>
			<td class="nb"><s:property value="nbExemplaireDispo"/></td>
        </tr>
 		</s:iterator>
 		</table>
 		<s:url namespace="/biblio" action="detailCatalogue" 	var="detailCat" />
 		<s:submit value="Fiche detail" 		formaction="${detailCat}" />
	</s:form>

	<!--  Include pour la gestion des messages d'erreurs utilisateurs -->
	<jsp:include page="/WEB-INF/jsp/msgErreur.jsp" />
</body>
</html>