<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx"%>  
<%@ taglib prefix="s" uri="/struts-tags"%>  
<!DOCTYPE html>
<html>  
<sx:head/>  
<head> 	
	<meta charset="ISO-8859-1" />
	<title>Tests Ajax sur Auteur</title>
	<link rel="stylesheet" href="<s:url namespace= "/vue" action="css" />" />
</head>   
<body>  
	<jsp:include page="/WEB-INF/jsp/menuBiblio.jsp" />	 
 	<p>La recherche des auteurs par nom est codee dans la gestion de l'auteur (menu auteur)</p>
 	<p>Saisir les 1eres lettres du nom de l'auteur recherché : </p>
 	<s:form>  
 		<!-- showDownArrow : affichage de la fleche dans la boite  -->
  		<sx:autocompleter size="2" name="selectionAuteur" list="listeAuteurs" 
  		listKey="id" listValue="nom"
  			showDownArrow="true" label="Choisir un auteur"></sx:autocompleter>  
 		<s:submit ></s:submit>  
 	</s:form>  
</body>  
</html>  