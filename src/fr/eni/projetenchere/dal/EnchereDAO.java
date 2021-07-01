package fr.eni.projetenchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.bo.Utilisateur;


public interface EnchereDAO {

	/**
	 * selectionne en base de données email, pseudo et mot de passe des utilisateurs et les met dans une liste
	 * @return listeEncheres
	 */
	List<Utilisateur> selectConnexion();
	

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
	List<ArticleVendu> selectEncheresOuvertesA(String pseudo);

	/**
	 * selectionne un lieu de retrait en fonction de l'article
	 * @return retrait
	 */
	Retrait selectRetraitByArticleId(int noArticle);

	/**
	 * selectionne tous les articles actuellement en vente et qui ne soit pas vendu par l'utilisateur
	 * avec recherche
	 * @return ListeEncheresOuvertes
	 */
	List<ArticleVendu> selectEncheresOuvertes(String pseudo, String contenuRecherche, String categorie);

	/**
	 * selectionne tous les articles actuellement en vente et sur lesquels l'utilisateur
	 * a enchérit
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return listeMesEncheres
	 */
	List<ArticleVendu> selectMesEncheres(String pseudo, String contenuRecherche, String categorie);
	
	/**
	 * ajoute une enchère en DB
	 * @param newEnchere
	 * @return newEnchere
	 * @throws SQLException 
	 */
	//Enchere insertEnchere(Enchere newEnchere);

	Enchere insertEnchere(Utilisateur utilisateur, int montant_enchere, int no_article) throws SQLException;


	
	/**
	 * ajoute un article
	 * @throws SQLException 
	 */
	void insertNouvelleVente(ArticleVendu articleVendu) throws SQLException;


	/**
	 * permet la récupération d'une recherche
	 * sur la page d'accueil
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return resultatRechercheAccueil
	 */
	List<ArticleVendu> selectAllVentesEnCoursRecherche(String pseudo, String contenuRecherche, String categorie);


	/**
	 * permet la récupération des enchères remportées par
	 * un utilisateur
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return listeMesEncheresRemportees
	 */
	List<ArticleVendu> selectMesEncheresRemportees(String pseudo, String contenuRecherche, String categorie);

	
	/**
	 * permet la récupération des ventes en cours
	 * d'un utilisateur
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return mesVentesEnCours
	 */
	List<ArticleVendu> selectMesVentesEnCours(String pseudo, String contenuRecherche, String categorie);


	/**
	 * permet la récupération des ventes non commencées
	 * d'un utilisateur
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return ListeVntesNonDebutees
	 */
	List<ArticleVendu> selectVentesNonDebutees(String pseudo, String contenuRecherche, String categorie);

		

}
