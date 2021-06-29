package fr.eni.projetenchere.dal;

import java.util.List;

import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;

import fr.eni.projetenchere.bo.Utilisateur;

/**
 * @author fgirard2021
 *
 */
public interface EnchereDAO {

	/**
	 * selectionne en DB toutes les enchères en cours et les met dans une liste
	 * @return listeEncheres
	 */
	List<Enchere> selectAllEnchere();

	
	/**
	 * selectionne en base de données email, pseudo et mot de passe des utilisateurs et les met dans une liste
	 * @return listeEncheres
	 */
	List<Utilisateur> selectConnexion();
	
	
	/**
	 * selectionne en DB toutes les enchères en cours selon
	 * @param noCategorie 
	 * @param nomArticle 
	 * @return listeEncheresBy
	 */
	List<Enchere> selectEnchereByCatAndArt(String nomArticle, int noCategorie);

	/**
	 * récupère les catégories et
	 * @return listeCategories
	 */
	List<Categorie> selectCategorie();

	/**
	 * ajoute un utilisateur en DB
	 * @param newUtilisateur
	 * @return newUtilisateur avec un noUtilisateur
	 */
	Utilisateur insertUtilisateur(Utilisateur newUtilisateur);


	/**
	 * selectionne en DB toutes les enchères en cours selon 
	 * @param nomArticle 
	 * @return listeEncheresByNom
	 */
	List<Enchere> selectEnchereByArticle(String nomArticle);

	/**
	 * sélectionne l'utilisateur à partir de son pseudo
	 * @param pseudo
	 * @return utilisateur
	 */
	Utilisateur selectUtilisateurByPseudo(String pseudo);


	/**
	 * permet de modifier le profil d'un utilisateur
	 * @param utilisateur
	 */
	void updateUtilisateur(Utilisateur utilisateur);

	/**permet de changer la valeur du boolean actif
	 * pour désactiver un utilisateur
	 * @param utilisateurSession
	 */
	void updateEtat(Utilisateur utilisateurSession);


	/**
	 * permet d'afficher tous les articles actuellement en vente
	 * @return listeArticlesVendus
	 */
	List<ArticleVendu> selectAllVentesEnCours();


	/**
	 * sélectionne l'utilisateur à partir de son numéro utilisateur (id)
	 * @param no_utilisateur
	 * @return utilisateur
	 */
	Utilisateur selectUserById(int no_utilisateur);

	/**
	 * sélectionne l'article à partir de son numéro article (id)
	 * @param no_article
	 * @return articleVendu
	 */
	ArticleVendu selectArticleById(int no_article);
	
	/**
	 * sélectionne la catégorie à partir de son numéro article (id)
	 * @param noCategorie
	 * @return categorie
	 */
	Categorie selectCategorieById(int noCategorie);

	/**
	 * selectionne tous les articles actuellement en vente et qui ne soit pas vendu par l'utilisateur
	 * @return listeEncheresOuvertes
	 */
	List<ArticleVendu> selectEncheresOuvertes(String pseudo);


}
