package fr.eni.projetenchere.bo;


import java.util.Date;

public class Enchere {
	
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	private Date dateEnchere;
	private int montant_enchere;
	
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
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
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
	
	
	

}
