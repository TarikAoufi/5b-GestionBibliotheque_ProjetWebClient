package fr.aoufi.appli.biblio;

import java.util.ArrayList;

import javax.naming.InitialContext;

import fr.aoufi.clientServer.IServiceFacade;
import fr.aoufi.clientServer.UserException;
import fr.aoufi.entity.Auteur;
import fr.aoufi.entity.Document;
import fr.aoufi.entity.Localisation;
import fr.aoufi.entity.ThemeDoc;
import fr.aoufi.ressources.Erreur;
import fr.aoufi.ressources.Param;

/**
 * CU Gerer le catalogue
 *
 */
public class GestionDocument {

	private IServiceFacade 		serviceFacade;

	private void init() {
		// initialisation de l'acces au service EJB
		InitialContext initialContext;
		try {
			initialContext 		= new InitialContext();			
			serviceFacade  	= (IServiceFacade)  		initialContext.lookup(Param.EJB_SERVICE_FACADE);																   
		}
		catch (Exception e) {
			System.err.println("***GestionDocument-init :  erreur link EJB");
			System.err.println(e);
		}
    }
	
	/*
	 * DOCUMENT
	 */
	private boolean isDocumentValide(Document document) throws UserException {
		System.out.println("*******************  GestionDocument - isDocumentValide " + document);
		init();
		boolean ok = true;
		if (document == null) ok = false;
		else {
			if (document.getCote().isEmpty() )  throw new UserException(Erreur.DOC_COTE_OBLIGATOIRE.action(), Erreur.DOC_COTE_OBLIGATOIRE.getCode());
			if (document.getTitre().isEmpty() ) throw new UserException(Erreur.DOC_TITRE_OBLIGATOIRE.action(), Erreur.DOC_TITRE_OBLIGATOIRE.getCode());
			// trt Localisation
			Localisation localisation = document.getLocalisation();
			if (localisation != null) {
				if (localisation.getLieu().isEmpty() || localisation.getEmp().isEmpty()) document.setLocalisation(null);
			}
			// trt Auteur
			Auteur auteur = document.getAuteur();
			if (auteur != null) {
				if (auteur.getId().equals("0")) document.setAuteur(null);
				else {
					auteur = serviceFacade.getAuteur(auteur.getId());
					document.setAuteur(auteur);
				}
			}
			// trt Theme
			ArrayList<ThemeDoc> themes = (ArrayList<ThemeDoc>) document.getThemes();
			document.setThemes(null);
			
			for (ThemeDoc theme : themes) {
				if (!theme.getId().equals("0")) {
					theme = serviceFacade.getTheme(theme.getId());
					document.addTheme(theme);
				}
			}
		}
		return ok;
	}
	
	public Document creer (Document document) throws UserException {
		if (isDocumentValide(document)) {
			init();
			document = serviceFacade.ajouter(document);
		}
		return document;
	}
	
	public Document creer(Document document, String idAuteur, String[] idThemes) throws UserException {
		document = assembler(document, idAuteur, idThemes);
		document = creer(document);
		return document;
		
	}
	
	public Document creer(String cote, String titre, String descriptif, int nbExemplaireDispo, String lieu, String emplacement, String idAuteur, String[] idThemes) 
			throws UserException {
		
		Document document = assembler(cote, titre, descriptif, nbExemplaireDispo, lieu, emplacement, idAuteur, idThemes);
		document = creer(document);
		return document;
	}
	
	public Document modifier(Document document) throws UserException {
		if (isDocumentValide(document)) {
			init();
			document = serviceFacade.update(document);
		}
		return document;
	}
	
	public Document modifier(Document document, String idAuteur,String[] idThemes) throws UserException {
		document = assembler(document, idAuteur, idThemes);
		document = modifier(document);
		return document;		
	}
	
	// Ajouter themes
	public Document modifier(String cote, String titre, String descriptif, int nbExemplaireDispo, 
			String lieu, String emplacement, String idAuteur, String[] idThemes) throws UserException {
		Document document = assembler(cote, titre, descriptif, nbExemplaireDispo, lieu, emplacement, idAuteur, idThemes);
		document = modifier(document);
		return document;
	}
	
	public void supprimer(String cote) throws UserException {
		init();
		serviceFacade.removeDocument(cote);
	}	

	public Document rechercherDocument(String cote) throws UserException {
		init();
		return serviceFacade.getDocument(cote);	
	}	
	
	public Document assembler(Document document, String idAuteur, String[] idThemes) throws UserException {
			Auteur auteur		  = new Auteur();
			auteur.setId(idAuteur);
			document.setAuteur(auteur);
			for (int i = 0; i < idThemes.length; i++) {
				if (!idThemes[i].equals("0")) {
					ThemeDoc theme = new ThemeDoc();
					theme.setId(idThemes[i]);
					document.addTheme(theme);
				}
			}
			return document;		
	}
	
	public Document assembler(String cote, String titre, String descriptif, int nbExemplaireDispo, String lieu, String emplacement, String idAuteur, String[] idThemes) throws UserException {
		Document document 			= new Document(cote, titre, descriptif, nbExemplaireDispo);
		Localisation localisation 	= new Localisation(lieu, emplacement);
		document.setLocalisation(localisation);
		document = assembler(document,idAuteur, idThemes);
		return document;		
	}
	
	/*
	 * AUTEUR
	 */
	private boolean isAuteurValide(Auteur auteur) throws UserException {
		boolean ok = true;
		if (auteur == null) ok = false;
		else {
			if (auteur.getId().isEmpty() ) 	throw new UserException(Erreur.AUT_ID_INVALID.action(), Erreur.AUT_ID_INVALID.getCode());
			if (auteur.getNom().isEmpty() )	throw new UserException(Erreur.AUT_NOM_OBLIGATOIRE.action(), Erreur.AUT_NOM_OBLIGATOIRE.getCode());
		}
		return ok;
	}
	
	public Auteur creerAuteur(Auteur auteur) throws UserException {
		if (isAuteurValide(auteur)) {
			init();
			auteur = serviceFacade.ajouter(auteur);
		}
		return auteur;
	}
	
	public Auteur creerAuteur(String ref, String nom, String prenom, String nationalite) throws UserException {
		Auteur	auteur 			= new Auteur(ref, nom, prenom, nationalite, null);
		auteur = creerAuteur(auteur);
		return auteur;
	}
	
	public Auteur modifierAuteur(Auteur auteur) throws UserException {
		if (isAuteurValide(auteur)) {
			init();
			auteur = serviceFacade.update(auteur);
		}
		return auteur;
	}
	
	public Auteur modifierAuteur(String ref, String nom, String prenom, String nationalite) throws UserException {
		Auteur 		auteur 		= new Auteur(ref, nom, prenom, nationalite, null);
		auteur = modifierAuteur(auteur);
		return auteur;
	}
	
	public void supprimerAuteur(String ref) throws UserException {
		init();
		serviceFacade.removeAuteur(ref);
	}

	public Auteur rechercherAuteur(String ref) throws UserException {
		init();
		Auteur auteur = serviceFacade.getAuteur(ref);
		return auteur;
	}

	/*
	 * THEME
	 */
	private boolean isThemeValide(ThemeDoc theme) throws UserException {
		boolean ok = true;
		if (theme == null) ok = false;
		else {
			if (theme.getId().isEmpty() ) 	throw new UserException(Erreur.THE_ID_INVALID.action(), Erreur.THE_ID_INVALID.getCode());
			if (theme.getId().equals("0"))  throw new UserException(Erreur.THE_ID_INVALID.action(), Erreur.THE_ID_INVALID.getCode());
			if (theme.getNom().isEmpty() )  throw new UserException(Erreur.THE_NOM_OBLIGATOIRE.action(), Erreur.THE_NOM_OBLIGATOIRE.getCode());
		}
		return ok;
	}
	
	public ThemeDoc creerTheme(ThemeDoc theme) throws UserException {
		if (isThemeValide(theme)) {
			init();
			theme = serviceFacade.add(theme);
		}
		return theme;
	}
	
	public ThemeDoc creerTheme(String ref, String nom, String desc) throws UserException {
		ThemeDoc	theme = new ThemeDoc(ref, nom, desc);
		theme = creerTheme(theme);
		return theme;
	}
	
	public ThemeDoc modifierTheme(ThemeDoc theme) throws UserException {
		if (isThemeValide(theme)) {
			init();
			theme = serviceFacade.update(theme);
		}
		return theme;
	}
	
	public ThemeDoc modifierTheme(String ref, String nom, String desc) throws UserException {
		ThemeDoc	theme = new ThemeDoc(ref, nom, desc);
		theme = modifierTheme(theme);
		return theme;
	}

	public void supprimerTheme(String ref) throws UserException {
		init();
		serviceFacade.removeThemeById(ref);
		
	}

	public ThemeDoc rechercherTheme(String ref) throws UserException {
		init();
		return serviceFacade.getTheme(ref);
	}


}
