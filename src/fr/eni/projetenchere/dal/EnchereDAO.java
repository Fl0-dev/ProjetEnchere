package fr.eni.projetenchere.dal;

import java.util.List;

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
	
	
	/**
	 * permet de supprimer un utilisateur
	 * @param utilisateurSession
	 */
	void deleteUtilisateur(Utilisateur utilisateurSession);

	/**
	 * sélectionne tous les articles en BDD et affiche le cas échéant la dernière enchère effectuée dessus
	 * @return listeArticlesVendus
	 */
	List<ArticleVendu> selectAllArticles();

	

}
