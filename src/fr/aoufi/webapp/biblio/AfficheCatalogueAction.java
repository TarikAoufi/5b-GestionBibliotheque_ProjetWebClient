package fr.aoufi.webapp.biblio;

import java.util.ArrayList;
import java.util.Collection;

import fr.aoufi.appli.biblio.GestionCatalogue;
import fr.aoufi.clientServer.UserException;
import fr.aoufi.entity.Document;
import fr.aoufi.webapp.ApplicationSupport;

public class AfficheCatalogueAction extends ApplicationSupport {


	private static final long serialVersionUID = 1L;

	// Pour jsp listeDocument
	private Collection<Document> listeDocuments = new ArrayList<>();
	
	// Pour jsp detailDocument
	private String cote;
	
	private Document document;

	private String msgErreur;

	public String byCote() {
		System.out.println("***** AfficheCatalogueAction - byCote() ");
		GestionCatalogue gestionCatalogue = new GestionCatalogue();
		listeDocuments = gestionCatalogue.listeParCote();
		return SUCCESS;
	}
	
	public String byTitre() {
		System.out.println("***** AfficheCatalogueAction - byTitre() ");
		GestionCatalogue gestionCatalogue = new GestionCatalogue();
		listeDocuments = gestionCatalogue.listeParTitre();
		return SUCCESS;
	}
	
	public String detail() {
		System.out.println("***** AfficheCatalogueAction - detail() ");
		System.out.println("************************ cote : " + cote);
		GestionCatalogue gestionCatalogue = new GestionCatalogue();
		try {
			document = gestionCatalogue.get(cote);
		} catch (UserException e) {
			document = null;
			msgErreur = "Le document n'existe pas";
		}
		return "detail";
	}
	
	public String getMsgErreur() {
		return msgErreur;
	}
	public void setMsgErreur(String msgErreur) {
		this.msgErreur = msgErreur;
	}
	
	public Collection<Document> getListeDocuments() {
		return listeDocuments;
	}
	
	public void setListeDocuments(Collection<Document> listeDocuments) {
		this.listeDocuments = listeDocuments;
	}
	
	public String getCote() {
		return cote;
	}
	
	public void setCote(String cote) {
		this.cote = cote;
	}
	
	public Document getDocument() {
		return document;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}

}
