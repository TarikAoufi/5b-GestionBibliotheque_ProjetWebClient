package fr.aoufi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;

public class ThemeDoc implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nom;
	private String description;
	private Collection<Document> documents = new ArrayList<Document>();

	// avant remove : on parcourt la collection de documents et on enleve les themes � la main
	@PreRemove
	private void removeThemeFromDocument() {
		if (documents != null) {
			for (Document document : documents) {
				document.getThemes().remove(this);
			}
		}
	}
	
	@PrePersist
	private void addThemeInDocument() {
		if (documents != null) {
			for (Document document : documents) {
				document.addTheme(this);
			}
		}
	}

	//	@Transient
	//	private String pasPersist;

	public ThemeDoc() { }

	public ThemeDoc(String id, String nom, String description) {
		this.id = id;
		this.nom = nom;
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Collection<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Collection<Document> documents) {
		this.documents = documents;
	}

	public void add(Document document) {
		if (document != null) {
			if (documents == null) documents = new ArrayList<Document>();
			if (!documents.contains(document)) {
				documents.add(document);
				document.addTheme(this);
			} 
		}
	}

	@Override
	public String toString() {
		String chaine = ""; 
		chaine += "Theme [" + getId() + ", " + getNom() + ", "
				+ getDescription();
		
		if (documents   != null) {
			chaine += ", Document [";
			for (Document document : documents) {
				chaine = chaine + document.getCote() + " " + document.getTitre() + " " + document.getDescriptif() + "," ;
			}
		}
		chaine += "]";
		return chaine;
	}

	// Voir classe Document pour les explications
	public ThemeDoc getDto () {

		ThemeDoc themeDto = new ThemeDoc(this.getId(), this.getNom(), this.getDescription());

		// on ajoute les documents du persistantBag dans le nouveau themeDto mais on ne charge pas
		// les themes des documents 
		
		if (this.getDocuments()!= null) {
			ArrayList<Document> listeDto = new ArrayList<Document>();
			for (Document document : this.getDocuments()) {
				listeDto.add(document.getDtoSansTheme());	
			}
			themeDto.setDocuments(listeDto);
		}
		return themeDto;
	}

}
