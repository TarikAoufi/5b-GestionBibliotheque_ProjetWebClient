package fr.aoufi.webapp.biblio;

import fr.aoufi.appli.biblio.GestionDocument;
import fr.aoufi.clientServer.UserException;
import fr.aoufi.entity.ThemeDoc;
import fr.aoufi.webapp.ApplicationSupport;

public class CrudThemeAction extends ApplicationSupport {

	private static final long serialVersionUID = 1L;
	
	private ThemeDoc unTheme;
	
	private String msgErreur;
	
	/**
	 * Affichage initial du formulaire 
	 */
	public String affiche() {
		System.out.println("***** CrudThemeAction - affiche() ");
		return "populate";
	}
	
	public String creation() {
		System.out.println("***** CrudThemeAction - creation() ");
		GestionDocument gestionDocument = new GestionDocument();
		try {
			gestionDocument.creerTheme(unTheme);
		} catch (UserException e) {
			System.out.println("DM***** CrudThemeAction - creation()  ERROR : " + e.getMessage());
			setMsgErreur(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String modification() {
		System.out.println("***** CrudThemeAction - modification() ");
		GestionDocument gestionDocument = new GestionDocument();
		try {
			gestionDocument.modifierTheme(unTheme);
		} catch (UserException e) {
			System.out.println("***** CrudThemeAction - modification()  ERROR : " + e.getMessage());
			msgErreur = e.getMessage();
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String suppression() {
		System.out.println("***** CrudThemeAction - suppression() ");
		GestionDocument gestionDocument = new GestionDocument();
		try {
			gestionDocument.supprimerTheme(unTheme.getId());
		} catch (UserException e) {
			System.out.println("***** CrudThemeAction - suppression()  ERROR : " + e.getMessage());
			msgErreur = e.getMessage();
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String recherche() {
		System.out.println("***** CrudThemeAction - recherche() ");
		GestionDocument gestionDocument = new GestionDocument();
		try {
			unTheme = gestionDocument.rechercherTheme(unTheme.getId());
		} catch (UserException e) {
			System.out.println("***** CrudThemeAction - recherche()  ERROR : " + e.getMessage());
			this.unTheme = null;
			msgErreur = e.getMessage();
			return INPUT;
		}
		return SUCCESS;
	}

	public ThemeDoc getUnTheme() {
		return unTheme;
	}

	public void setUnTheme(ThemeDoc unTheme) {
		this.unTheme = unTheme;
	}

	public String getMsgErreur() {
		return msgErreur;
	}

	public void setMsgErreur(String msgErreur) {
		this.msgErreur = msgErreur;
	}


}
