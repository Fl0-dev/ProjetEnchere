package fr.eni.projetenchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DAOFactory;
import fr.eni.projetenchere.dal.EnchereDAO;

/**
 * Classe de la BLL contenant les méthodes utilisées en Vue et utilisant les
 * méthodes de la DAL
 * 
 * 
 * @version 1.0
 * @date 22/06/21
 */
public class EnchereManager {

	// création d'instance
	private static EnchereManager instance;

	// Singleton du manager
	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}

	//
	private EnchereDAO enchereDAO;

	// constructeur
	private EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}

	
	/**
	 * récupère toutes les enchères en cours dans
	 * 
	 * @return listeEncheres avec
	 *         enchère(dateEnchere,montantEnchere,utilisateur(pseudo),
	 *         articleVendu(nomArticle))
	 */
	public List<Enchere> selectAllEnchere() {
		// création de la liste des enchères
		List<Enchere> listeEncheres;
		listeEncheres = enchereDAO.selectAllEnchere();
		return listeEncheres;
		// TODO gestion des exceptions
	}

	/**
	 * récupère email, pseudo et mot de passe des utilisateurs pour l'accès à la
	 * connexion
	 * 
	 * @return listeUtilisateurConnexion
	 * 
	 */
	public List<Utilisateur> selectConnexion() {
		// création de la liste des utilisateurs pour la connexion
		List<Utilisateur> listeUtilisateurConnexion;

		listeUtilisateurConnexion = enchereDAO.selectConnexion();
		return listeUtilisateurConnexion;
		// TODO gestion des exceptions
	}

	/**
	 * récupère toutes les enchères selon
	 * 
	 * @param nomArticle
	 * @param noCategorie
	 * @return listeEncheresByCategorie avec
	 *         enchère(dateEnchere,montantEnchere,utilisateur(pseudo),
	 *         articleVendu(nomArticle))
	 */
	public List<Enchere> selectEnchereByCatAndArt(String nomArticle, int noCategorie) {
		List<Enchere> listeEncheresByCategorie;

		listeEncheresByCategorie = enchereDAO.selectEnchereByCatAndArt(nomArticle, noCategorie);

		return listeEncheresByCategorie;
	}

	/**
	 * récupère toutes les enchères selon
	 * 
	 * @param nomArticle
	 * @return listeEncheresByArticle
	 */
	public List<Enchere> selectEnchereByArticle(String nomArticle) {
		List<Enchere> listeEncheresByArticle;

		listeEncheresByArticle = enchereDAO.selectEnchereByArticle(nomArticle);

		return listeEncheresByArticle;
	}

	/**
	 * récupère les catégories et
	 * 
	 * @return listeCategories à utiliser pour les options du menu déroulant
	 */
	public List<Categorie> selectCategorie() {
		// création de la liste des enchères
		List<Categorie> listeCategories;
		listeCategories = enchereDAO.selectCategorie();
		return listeCategories;
		// TODO gestion exception

	}

	/**
	 * ajoute un utilisateur après l'avoir créer avec les données fournies :
	 * 
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param confirmation
	 * @return newUtilisateur
	 */
	public Utilisateur insertNewUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) {
		Utilisateur newUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				motDePasse);
		enchereDAO.insertUtilisateur(newUtilisateur);

		return newUtilisateur;
	}

	/**
	 * récupère les données utilisateur à partir de son pseudo
	 * 
	 * @param pseudo
	 * @return utilisateur
	 */
	public Utilisateur selectUtilisateurByPseudo(String pseudo) {

		Utilisateur utilisateur;

		utilisateur = enchereDAO.selectUtilisateurByPseudo(pseudo);

		return utilisateur;
		// TODO : Gestion exception
	}


	/**
	 * permet de modifier le profil d'un utilisateur
	 * @param noUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 */
	public void modifUtilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) {
		Utilisateur utilisateur = new Utilisateur(noUtilisateur,pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				motDePasse);
		enchereDAO.updateUtilisateur(utilisateur);
		// TODO : Gestion exception
	}


	/**
	 * permet de changer la valeur d'un utilisateur
	 * et de désactiver son compte
	 * @param utilisateurSession
	 */
	public void desactiveUtilisateur(Utilisateur utilisateurSession) {
		enchereDAO.updateEtat(utilisateurSession);
		
	}
	
	/**
	 * sélectionne tous les articles actuellement en vente
	 * @return listeArticlesEnVente
	 */
	public List<ArticleVendu> selectAllVentesEnCours() {
		List<ArticleVendu> listeArticlesEnVente = enchereDAO.selectAllVentesEnCours();
		return listeArticlesEnVente;
		
	}

}
