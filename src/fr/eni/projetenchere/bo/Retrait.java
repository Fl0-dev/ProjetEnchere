package fr.eni.projetenchere.bo;

public class Retrait {
	
	private String rue_retrait;
	private String code_postal_retrait;
	private String ville_retrait;
	private ArticleVendu articleVendu_retrait;
	private int num_retrait;
	
	public int getNum_retrait() {
		return num_retrait;
	}
	public void setNum_retrait(int num_retrait) {
		this.num_retrait = num_retrait;
	}
	public String getRue_retrait() {
		return rue_retrait;
	}
	public void setRue_retrait(String rue_retrait) {
		this.rue_retrait = rue_retrait;
	}
	public String getCode_postal_retrait() {
		return code_postal_retrait;
	}
	public void setCode_postal_retrait(String code_postal_retrait) {
		this.code_postal_retrait = code_postal_retrait;
	}
	public String getVille_retrait() {
		return ville_retrait;
	}
	public void setVille_retrait(String ville_retrait) {
		this.ville_retrait = ville_retrait;
	}
	public ArticleVendu getArticleVendu_retrait() {
		return articleVendu_retrait;
	}
	public void setArticleVendu_retrait(ArticleVendu articleVendu_retrait) {
		this.articleVendu_retrait = articleVendu_retrait;
	}
	public Retrait() {
		super();
	}
		
	public Retrait(String rue_retrait, String code_postal_retrait, String ville_retrait) {
		super();
		this.rue_retrait = rue_retrait;
		this.code_postal_retrait = code_postal_retrait;
		this.ville_retrait = ville_retrait;
	}
	@Override
	public String toString() {
		return "Retrait [rue_retrait=" + rue_retrait + ", code_postal_retrait=" + code_postal_retrait
				+ ", ville_retrait=" + ville_retrait + ", articleVendu_retrait=" + articleVendu_retrait + "]";
	}
	
	

}
