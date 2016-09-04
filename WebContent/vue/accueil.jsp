<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>CDI's BIB : Notre bibliothèque</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/biblio.css" />
</head>
<body>
	<!--  Include pour la gestion des messages d'erreurs utilisateurs -->
	<jsp:include page="../WEB-INF/jsp/msgErreur.jsp" />
	<jsp:include page="../WEB-INF/jsp/menu.jsp" />
	
	<h1>Bienvenue sur Notre site</h1>
	<p> Vous pouvez vous diriger vers </p>
	<ul>
		<li>La <s:a namespace="/biblio" action="accueil" >bibliotheque</s:a></li>
		
		<li>La gestion du <s:a namespace="/pers"   action="accueil" >personnel</s:a></li>
	</ul>
	<br/><br/>
	<p>Attention : ouvrir avec firefox pour la prise en charge de html5</p>
</body>
</html>