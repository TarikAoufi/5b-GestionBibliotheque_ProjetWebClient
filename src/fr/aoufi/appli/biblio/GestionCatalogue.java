package fr.aoufi.appli.biblio;

import java.util.List;

import javax.naming.InitialContext;

import fr.aoufi.clientServer.IServiceFacade;
import fr.aoufi.clientServer.UserException;
import fr.aoufi.entity.Auteur;
import fr.aoufi.entity.Document;
import fr.aoufi.entity.Theme;
import fr.aoufi.ressources.Param;

/**
 * CU Afficher le catalogue
 *
 */
public class GestionCatalogue {

	private IServiceFacade 		serviceFacade;


	public List<Document> listeParCote() {
		init();
		List<Document> liste = serviceFacade.getAllDocumentByCote();
		return liste;
	}
	
	public List<Document> listeParTitre() {
		init();
		List<Document> liste = serviceFacade.getAllDocumentByTitre();
		return liste;
	}
	
	public List<Auteur> auteurParId() {
		init();
		List<Auteur> liste = serviceFacade.getAllAuteurById();
		return liste;
	}
	
	public List<Auteur> auteurParNom() {
		init();
		List<Auteur> liste = serviceFacade.getAllAuteurByNom();
		return liste;
	}
	
	public List<Auteur> rechercheAuteurByNom(String nom) {
		init();
		List<Auteur> liste = serviceFacade.rechercheAuteurByNom(nom);
		return liste;
	}
	
	public List<Theme> themeParId() {
		init();
		List<Theme> liste = serviceFacade.getAllThemeById();
		return liste;
	}
	
	public List<Theme> themeParNom() {
		init();
		List<Theme> liste = serviceFacade.getAllThemeByNom();
		return liste;
	}
	
	public Document get(String cote) throws UserException  {
		init();
		Document document = serviceFacade.getDocument(cote);
		return document;
	}
	
	public void init() {
		// initialisation de l'acces au service EJB
		InitialContext initialContext;
		try {
			initialContext 		= new InitialContext();
			serviceFacade  	= (IServiceFacade)  		initialContext.lookup(Param.EJB_SERVICE_FACADE);																   
		}
		catch (Exception e) {
			System.out.println("*** GestionCatalogue-init :  erreur link EJB");
			System.out.println(e);
		}
    }
}
