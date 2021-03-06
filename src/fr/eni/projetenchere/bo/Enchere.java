package fr.eni.projetenchere.bo;


import java.time.LocalDate;


public class Enchere {
	
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	private LocalDate dateEnchere;
	private int montant_enchere;
	private int num_enchere;
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	@Override
	public String toString() {
		return "Enchere [utilisateur=" + utilisateur + ", articleVendu=" + articleVendu + ", dateEnchere=" + dateEnchere
				+ ", montant_enchere=" + montant_enchere + "]";
	}
	
	public Enchere() {
		super();
	}
	public Enchere(int montant_enchere, Utilisateur utilisateur, ArticleVendu articleVendu) {
		super();
		this.montant_enchere = montant_enchere;
		this.utilisateur = utilisateur;
		this.articleVendu = articleVendu;
	}
	public int getNum_enchere() {
		return num_enchere;
	}
	public void setNum_enchere(int num_enchere) {
		this.num_enchere = num_enchere;
	}

	
	
	

}
