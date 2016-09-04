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
	<p>D&eacute;tail d'un document</p>
	<!--  theme="simple" : Struts ne genere pas les <table>, <tr>, <td> (qu'il ajoute par defaut) -->
	<s:if test="document == null">
		<p>Le document n'existe pas</p>
	</s:if>
	<s:else>
			<table class="liste">
		<tr>
			<th>Cote</th>
			<th>Titre</th>
			<th>Description</th>
			<th>Exemplaires Dispo</th>
		</tr>
		<tr id="<s:property value="document.cote"/>">
			<td class="cote"><s:property value="document.cote"/></td>
			<td class="titre"><s:property value="document.titre"/></td>
			<td class="desc"><s:property value="document.descriptif"/></td>
			<td class="nb"><s:property value="document.nbExemplaireDispo"/></td>
    	</tr>
		</table>
	</s:else>
	<s:if test="document.localisation == null">
		<p>Pas de localisation</p>
	</s:if>
	<s:else>
	<br/><br/>
		<table class="liste">
			<tr>
				<th>idLoca</th>
				<th>Lieu</th>
				<th>Emplacement</th>
			</tr>
			<tr>
				<td class="cote"><s:property  value="document.localisation.idLocalisation" /></td>
				<td class="titre"><s:property value="document.localisation.lieu" /></td>
				<td class="desc"><s:property  value="document.localisation.emp" /></td>
	    	</tr>
		</table>
	</s:else>
	<s:if test="document.auteur == null">
		<p>Pas d'auteur</p>
	</s:if>
	<s:else>
		<br/><br/>
		<table class="liste">
			<tr>
				<th>Ref</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Nationalité</th>
				<th>date Naissance</th>
			</tr>
			<tr>
				<td class="reference"><s:property  value="document.auteur.id" /></td>
				<td class="nom"><s:property  value="document.auteur.nom" /></td>
				<td class="prenom"><s:property  value="document.auteur.prenom" /></td>
				<td class="nationalite"><s:property  value="document.auteur.nationalite" /></td>
				<td class="dateNaissance"><s:property  value="document.auteur.dateNaissance" /></td>
	    	</tr>
		</table>
	</s:else>
	<s:if test="document.themes == null || document.themes.size() == 0">
		<p>Pas de themes</p>
	</s:if>
	<s:else>
		<br/><br/>
		<table class="liste">
		<tr>
			<th>&nbsp;</th>
			<th>Ref</th>
			<th>Nom</th>
			<th>Description</th>
			<th>Documents</th>
		</tr>
		<s:iterator value="document.themes">
        	<tr id="<s:property value="id"/>">
				<td class="cote"><s:property value="id"/></td>
				<td class="titre"><s:property value="nom"/></td>
				<td class="desc"><s:property value="description"/></td>
				<td class="nb">
					<s:iterator value="document.themes.documents">
        					<s:property value="cote"/>,
        			</s:iterator>
        		</td>		
        	</tr>
 		</s:iterator>
 		</table>
	
	</s:else>
	<!--  Include pour la gestion des messages d'erreurs utilisateurs -->
	<jsp:include page="/WEB-INF/jsp/msgErreur.jsp" />
</body>
</html>