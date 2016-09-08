package fr.aoufi.webapp.biblio;

import java.util.ArrayList;
import java.util.Collection;

import fr.aoufi.appli.biblio.GestionCatalogue;
import fr.aoufi.appli.biblio.GestionDocument;
import fr.aoufi.clientServer.UserException;
import fr.aoufi.entity.Auteur;
import fr.aoufi.entity.Document;
import fr.aoufi.entity.Theme;
import fr.aoufi.webapp.ApplicationSupport;

public class CrudDocumentAction extends ApplicationSupport {


	private static final long serialVersionUID = 1L;
	private Document document;

	// gestion de la liste des auteurs
	private Collection<Auteur> auteurs = new ArrayList<>();
	
	private String selectionAuteur ;

	// gestion de la liste des themes
	private Collection<Theme> themes  = new ArrayList<>();
	
	private String[] selectionTheme;

	private String msgErreur;


	/**
	 * Remet le formulaire dans l'etat initial (vide)
	 */
	private void resetDocument() {
		document = new Document();
		selectionAuteur = null;
		selectionTheme  = null;
		initListeAuteurTheme();
	}
	
	/**
	 * Positionne le formumlaire avec l'etat d'un document
	 * @param document
	 */
	private void initDoc(Document document) {
		initListeAuteurTheme();
		selectionAuteur = null;
		selectionTheme  = null;
		// positionne les valeurs pour le document
		if (document != null) {
			if (document.getAuteur()!=null) selectionAuteur = document.getAuteur().getId();
			if (document.getThemes()!= null) {
				int i = 0;
				selectionTheme = new String[document.getThemes().size()];
				for(Theme theme: document.getThemes()) {
					selectionTheme[i] = theme.getId();
					i++;
				}
			}
		}	
	}
	
	
	/**
	 * Remplissage des listes auteurs et themes
	 */
	private void initListeAuteurTheme() {
		GestionCatalogue gestionCatalogue 	= new GestionCatalogue();
		auteurs = (ArrayList<Auteur>) gestionCatalogue.auteurParId();
		themes  = (ArrayList<Theme>) gestionCatalogue.themeParId();	
	}

	/**
	 * Affichage initial du formulaire 
	 */
	public String affiche() {
		System.out.println("***** CrudDocumentAction - affiche() ");
		initListeAuteurTheme();
		return "populate";
	}

	public String reset() {
		System.out.println("***** CrudDocumentAction - affiche() ");
		resetDocument();
		return "populate";
	}
	
	public String creation() {
		System.out.println("***** CrudDocumentAction - creation() ");
		String codeRetour = SUCCESS;
		GestionDocument gestionDocument = new GestionDocument();
		try {
			gestionDocument.creer(document, selectionAuteur, selectionTheme);
		} catch (UserException e) {
			System.out.println("***** CrudDocumentAction - creation()  ERROR : " + e.getMessage());
			setMsgErreur(e.getMessage());
			codeRetour = INPUT;
		}
		initListeAuteurTheme();
		return codeRetour;
	}

	public String modification() {
		System.out.println("***** CrudDocumentAction - modification() ");
		String codeRetour = SUCCESS;
		GestionDocument gestionDocument = new GestionDocument();
		try {
			gestionDocument.modifier(document, selectionAuteur, selectionTheme);

		} catch (UserException e) {
			System.out.println("***** CrudDocumentAction - modification()  ERROR : " + e.getMessage());
			msgErreur = e.getMessage();
			codeRetour = INPUT;
		}
		initListeAuteurTheme();
		return codeRetour;
	}

	public String suppression() {
		System.out.println("***** CrudDocumentAction - suppression() ");
		String codeRetour = SUCCESS;
		String cote = document.getCote();
		GestionDocument gestionDocument = new GestionDocument();
		try {
			gestionDocument.supprimer(cote);;
		} catch (UserException e) {
			System.out.println("***** CrudDocumentAction - suppression()  ERROR : " + e.getMessage());
			resetDocument();
			document.setCote(cote);
			msgErreur = e.getMessage();
			codeRetour = INPUT;
		}		
		return codeRetour;
	}

	public String recherche() {
		System.out.println("***** CrudDocumentAction - recherche() ");
		String codeRetour = SUCCESS;
		String cote = document.getCote();
		GestionDocument gestionDocument = new GestionDocument();
		try {
			document = gestionDocument.rechercherDocument(cote);
			initDoc(document);
		} catch (UserException e) {
			System.out.println("***** CrudDocumentAction - recherche()  ERROR : " + e.getMessage());			
			resetDocument();
			document.setCote(cote);
			msgErreur = e.getMessage();
			codeRetour = INPUT;
		}		
		return codeRetour;
	}

	/*
	 * Ces 2 méthodes ne doivent pas exister sinon struts cherche
	 *   à prendre la liste des themes et des auteurs de la JSP et à les 
	 *   mapper dans ces collections lorsqu'on click sur création
	 *   => cela ne fonctionne pas et on ne souhaite pas reconstruire
	 *      la liste à partir des données
	 *      de la Jsp.
	 */
	//	public void setAuteurs(Collection<Auteur> auteurs) {
	//	this.auteurs = auteurs;
	//}
	//	public void setThemes(Collection<Theme> themes) {
	//	this.themes = themes;
	//}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Collection<Auteur> getAuteurs() {
		return auteurs;
	}

	public String getSelectionAuteur() {
		return selectionAuteur;
	}

	public void setSelectionAuteur(String selectionAuteur) {
		this.selectionAuteur = selectionAuteur;
	}

	public Collection<Theme> getThemes() {
		return themes;
	}

	public String[] getSelectionTheme() {
		return selectionTheme;
	}

	public void setSelectionTheme(String[] selectionTheme) {
		this.selectionTheme = selectionTheme;
	}

	public String getMsgErreur() {
		return msgErreur;
	}

	public void setMsgErreur(String msgErreur) {
		this.msgErreur = msgErreur;
	}


}
