<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="fr.aoufi.entity.Auteur"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" />
	<title>Gestion des auteurs</title>
	<link rel="stylesheet" href="<s:url namespace= "/vue" action="css" />" />
	<link rel="stylesheet" href="<s:url namespace= "/biblio" action="cssFormAuteur" />" />
	<script src="../js/jquery-1.11.2.min.js"></script>
	<script>
	$(document).ready(function(){
		
		$("#nomAuteur").keyup(execLoad);
		
		function execLoad(event) {
			var nomSaisi 	= $("#nomAuteur").val();
//			alert("nomSaisi : " + nomSaisi);

			var data1 		= {nomRecherche : nomSaisi};
			$.ajax({
				
				url : "./rechercheParNomAuteur",
				data : data1,
				complete : function(xhr, result) {
					if (result != "success") return;
					// la directive de page est obligatoire dans la JSP sinon response est null
					var response = xhr.responseXML;
					// alert(response);

					// recuperation des éléments auteur
 					var auteurs = $(response).find("auteur");
					// alert(auteurs.text());

					// supprime les éléments enfants de div#listeAuteur
					$("div#listeAuteur").empty();
 					$("div#listeAuteur").append("<ul id='resultAuteur'>");
					$.each(auteurs, function() {
						$("#resultAuteur").append("<li>Auteur : " + $(this).attr("id") + " " + $(this).text() + "</li>");
 					});
				}
			}); // fin $.ajax
		} // fin execLoad

	});	// fin $(document).ready
	</script>
</head>
<body>
	<!--                                                       -->
	<!-- Ecrire le js pour le control de saisie ... longueur des champs, numéricité ...   -->
	<!--                                                       -->
	<jsp:include page="/WEB-INF/jsp/menuBiblio.jsp" />
	<p>Gestion des auteurs :</p>
	<s:form id="auteur" action="afficheAuteur" method="post" validate="false" theme="simple">
		<div id="d1">
			<p><s:label for="auteur.id" 			value="Ref :" /></p>
			<p><s:label for="auteur.nom" 			value="Nom :" /></p>
			<p><s:label for="auteur.prenom" 		value="Prénom :" /></p>
			<p><s:label for="auteur.nationalite" 	value="Nationalite :" /></p>
			<p><s:label for="auteur.dateNaissance" 	value="DateNaissance :" /></p>
		</div>
		<div id="d2">
			<p><s:textfield 	name="auteur.id" /></p>
			<p><s:textfield 	name="auteur.nom" /></p>
			<p><s:textfield 	name="auteur.prenom" /></p>
			<p><s:textfield 	name="auteur.nationalite" /></p>		
			<s:date name="auteur.dateNaissance" id="formattedDateNaissance" format="dd/MM/yyyy"/>
			<p><s:textfield 	name="auteur.dateNaissance" value="%{formattedDateNaissance}" /></p>
		</div>
		<div id="d4">	
			<!-- Creation des url de navigation -->
			<s:url namespace="/biblio" action="creationAuteur" 		var="creaAuteur" />
			<s:url namespace="/biblio" action="modificationAuteur" 	var="modifAuteur" />
			<s:url namespace="/biblio" action="suppressionAuteur" 	var="supAuteur" />
			<s:url namespace="/biblio" action="rechercheAuteur" 	var="rechAuteur" />
			
			<s:submit value="Reset" />
			<s:submit value="Creation" 		formaction="${creaAuteur}" />
			<s:submit value="Modification" 	formaction="${modifAuteur}" />
			<s:submit value="Suppression" 	formaction="${supAuteur}" />
			<s:submit value="Rechercher par reference" 	formaction="${rechAuteur}" />	
		</div>
	</s:form>
	<!--  Include pour la gestion des messages d'erreurs utilisateurs -->
	<jsp:include page="/WEB-INF/jsp/msgErreur.jsp" />
	
	<!-- Ajout de la fonctionnalite recherche par nom des auteurs -->
	<p> Recherche des auteurs par nom : <input type="text" id="nomAuteur" /></p>
	<br/>
	<div id="listeAuteur"></div>
</body>
</html>