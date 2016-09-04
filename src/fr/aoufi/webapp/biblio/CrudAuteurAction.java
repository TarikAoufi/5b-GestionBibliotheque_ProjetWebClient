package fr.aoufi.webapp.biblio;

import java.util.List;

import fr.aoufi.appli.biblio.GestionCatalogue;
import fr.aoufi.appli.biblio.GestionDocument;
import fr.aoufi.clientServer.UserException;
import fr.aoufi.entity.Auteur;
import fr.aoufi.webapp.ApplicationSupport;

public class CrudAuteurAction extends ApplicationSupport {


	private static final long serialVersionUID = 1L;
	private Auteur auteur;
	private String msgErreur;
	
	// Pour recherche des auteurs par nom (ajax)
	private List<Auteur> 	listeAuteurs;
	private String 			nomRecherche;
	
	/**
	 * Affichage initial du formulaire 
	 */
	public String affiche() {
		System.out.println("***** CrudAuteurAction - affiche() ");
		return "populate";
	}
	
	public String creation() {
		System.out.println("***** CrudAuteurAction - creation() " + auteur);
		GestionDocument gestionDocument = new GestionDocument();
		try {
			gestionDocument.creerAuteur(auteur);
		} catch (UserException e) {
			System.out.println("***** CrudAuteurAction - creation()  ERROR : " + e.getMessage());
			msgErreur = e.getMessage();
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String modification() {
		System.out.println("***** CrudAuteurAction - modification() ");
		GestionDocument gestionDocument = new GestionDocument();
		try {
			gestionDocument.modifierAuteur(auteur);
		} catch (UserException e) {
			System.out.println("***** CrudAuteurAction - modification()  ERROR : " + e.getMessage());
			msgErreur = e.getMessage();
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String suppression() {
		System.out.println("***** CrudAuteurAction - suppression() ");
		GestionDocument gestionDocument = new GestionDocument();
		try {
			gestionDocument.supprimerAuteur(auteur.getId());
		} catch (UserException e) {
			System.out.println("***** CrudAuteurAction - suppression()  ERROR : " + e.getMessage());
			msgErreur = e.getMessage();
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String recherche() {
		System.out.println("***** CrudAuteurAction - recherche() ");
		GestionDocument gestionDocument = new GestionDocument();
		try {
			auteur = gestionDocument.rechercherAuteur(auteur.getId());
		} catch (UserException e) {
			System.out.println("***** CrudAuteurAction - recherche()  ERROR : " + e.getMessage());
			this.auteur = null;
			msgErreur = e.getMessage();
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	public String rechercheParNom() {
		System.out.println("***** CrudAuteurAction - rechercheParNom()");
		GestionCatalogue gestionCatalogue = new GestionCatalogue();
		listeAuteurs = gestionCatalogue.rechercheAuteurByNom(nomRecherche);
		return "ajaxRecherche";
	}
	
	public String rechercheParNomV2() {
		System.out.println("***** CrudAuteurAction - rechercheParNomV2()");
		GestionCatalogue gestionCatalogue = new GestionCatalogue();
		listeAuteurs = gestionCatalogue.auteurParNom();
		return "ajaxRechercheV2";
	}

	
	public Auteur getAuteur() {
		return auteur;
	}
	
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public List<Auteur> getListeAuteurs() {
		return listeAuteurs;
	}

	public void setListeAuteurs(List<Auteur> listeAuteurs) {
		this.listeAuteurs = listeAuteurs;
	}

	public String getNomRecherche() {
		return nomRecherche;
	}

	public void setNomRecherche(String nomRecherche) {
		this.nomRecherche = nomRecherche;
	}

	public String getMsgErreur() {
		return msgErreur;
	}

	public void setMsgErreur(String msgErreur) {
		this.msgErreur = msgErreur;
	}

}
