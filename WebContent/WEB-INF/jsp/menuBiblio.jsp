<%@ taglib prefix="s" uri="/struts-tags"%>
<hr />
<ul id="menu">
  <li><s:a namespace="" 		action="accueil" >Accueil</s:a></li>
  <li><s:a namespace="/biblio" 	action="afficheDocument" >Document</s:a></li>
  <li><s:a namespace="/biblio" 	action="afficheAuteur" >Auteur</s:a></li>
  <li><s:a namespace="/biblio" 	action="afficheTheme" >Theme</s:a></li>
  <li><s:a namespace="/biblio" 	action="byCoteCatalogue" >Catalogue : Par cote</s:a></li>
  <li><s:a namespace="/biblio" 	action="byTitreCatalogue" > - Par titre</s:a></li>
  <li><s:a namespace="/biblio" 	action="rechercheParNomV2Auteur" >Auteur Ajax</s:a></li>
</ul>
<hr/>

	