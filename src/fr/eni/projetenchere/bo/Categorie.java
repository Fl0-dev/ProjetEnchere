package fr.eni.projetenchere.bo;

import java.util.List;

public class Categorie {

	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> listeArticlesCategorie;
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<ArticleVendu> getListeArticlesCategorie() {
		return listeArticlesCategorie;
	}
	public void setListeArticlesCategorie(List<ArticleVendu> listeArticlesCategorie) {
		this.listeArticlesCategorie = listeArticlesCategorie;
	}
	public Categorie() {
		super();
	}
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", listeArticlesCategorie="
				+ listeArticlesCategorie + "]";
	}
	
}
