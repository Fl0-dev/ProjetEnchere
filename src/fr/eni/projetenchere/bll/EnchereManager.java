package fr.eni.projetenchere.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Retrait;
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

/**
 * @author Florian
 *
 */
/**
 * @author Florian
 *
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


	/**
	 * selectionne tous les articles actuellement en vente et qui ne soit pas vendu par l'utilisateur
	 * à son arrivée(sans recherche)
	 * @return ListeEncheresOuvertes
	 */
	public List<ArticleVendu> selectEncheresOuvertesA(String pseudo) {
		List<ArticleVendu> listeEncheresOuvertes = enchereDAO.selectEncheresOuvertesA(pseudo);
		return listeEncheresOuvertes;
	}
	
	/**
	 * sélectionne un utilisateur à partir de son numéro utilisateur (id)
	 * @param no_utilisateur
	 * @return utilisateur
	 */
	public Utilisateur selectUserById(int no_utilisateur) {
		Utilisateur utilisateur = enchereDAO.selectUserById(no_utilisateur);
		return utilisateur;
		
	}
	
	/**
	 * sélectionne un article à partir de son numéro (id)
	 * @param no_article
	 * @return articleVendu
	 */
	public ArticleVendu selectArticleById(int no_article) {
		ArticleVendu articleVendu = enchereDAO.selectArticleById(no_article);
		return articleVendu;
	}
	
	/**
	 * sélectionne une catégorie à partir de son numéro (id)
	 * @param no_categorie
	 * @return categorie
	 */
	public Categorie selectCategorieById(int noCategorie) {
		Categorie categorie = enchereDAO.selectCategorieById(noCategorie);
		return categorie;
		
	}

	/**
	 * sélectionne un lieu de retrait à partir d'un article
	 * @param noArticle
	 * @return retrait
	 */
	public Retrait selectRetraitByArticleId(int noArticle) {
		Retrait retrait = enchereDAO.selectRetraitByArticleId(noArticle);
		return retrait;
		
	}


	
	/**
	 * selectionne tous les articles actuellement en vente et qui ne soit pas vendu par l'utilisateur
	 * avec recherche
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return listeEncheresOuvertes
	 */
	public List<ArticleVendu> selectEncheresOuvertes(String pseudo, String contenuRecherche, String categorie) {
		List<ArticleVendu> listeEncheresOuvertes = enchereDAO.selectEncheresOuvertes(pseudo,contenuRecherche,categorie);
		return listeEncheresOuvertes;
	}

	/**
	 * selectionne tous les articles actuellemnt en vente et sur lesquels l'utilisateur
	 * a enchérit
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return listeMesEncheres
	 */
	public List<ArticleVendu> selectMesEncheres(String pseudo, String contenuRecherche, String categorie) {
		List<ArticleVendu> listeMesEncheres = enchereDAO.selectMesEncheres(pseudo,contenuRecherche,categorie);
		return listeMesEncheres;
	}

	/**
	 * ajoute une enchère en DB
	 * @param newEnchere
	 * @return newEnchere
	 * @throws SQLException 
	 */
	public Enchere insertEnchere(Utilisateur utilisateur, int montant_enchere, int no_article) throws SQLException {
		Enchere enchere = enchereDAO.insertEnchere(utilisateur, montant_enchere, no_article);
		return enchere;
	}
	
	public void insertArticle(ArticleVendu newArticle) throws SQLException {
		// TODO Auto-generated method stub
		enchereDAO.insertNouvelleVente(newArticle);
	}
	
	/**
	 * permet la récupération d'une recherche
	 * sur la page d'accueil
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return resultatRechercheAccueil
	 */
	public List<ArticleVendu> selectAllVentesEnCoursRecherche(String pseudo, String contenuRecherche,
			String categorie) {
		List<ArticleVendu> resultatRechercheAccueil = enchereDAO.selectAllVentesEnCoursRecherche(pseudo,contenuRecherche,categorie);
		return resultatRechercheAccueil;
	}



	/**
	 * permet la récupération des enchères remportées par
	 * un utilisateur
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return listeMesEncheresRemportees
	 */
	public List<ArticleVendu> selectMesEncheresRemportees(String pseudo, String contenuRecherche, String categorie) {
		List<ArticleVendu> listeMesEncheresRemportees = enchereDAO.selectMesEncheresRemportees(pseudo,contenuRecherche,categorie);
		return listeMesEncheresRemportees;
	}



	/**
	 * permet la récupération des ventes en cours
	 * d'un utilisateur
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return listeMesVentesEnCours
	 */
	public List<ArticleVendu> selectMesVentesEnCours(String pseudo, String contenuRecherche, String categorie) {
		List<ArticleVendu> listeMesVentesEnCours = enchereDAO.selectMesVentesEnCours(pseudo,contenuRecherche,categorie);
		return listeMesVentesEnCours;
	}



	/**
	 * permet la récupération des ventes non commencées
	 * d'un utilisateur
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return ListeVntesNonDebutees
	 */
	public List<ArticleVendu> selectVentesNonDebutees(String pseudo, String contenuRecherche, String categorie) {
		List<ArticleVendu> listeVentesNonDebutees = enchereDAO.selectVentesNonDebutees(pseudo,contenuRecherche,categorie);
		return listeVentesNonDebutees;
	}



	/**
	 * permet la récupération des ventes terminées 
	 * d'un utilisateurs
	 * @param pseudo
	 * @param contenuRecherche
	 * @param categorie
	 * @return listeVentesTerminees
	 */
	public List<ArticleVendu> selectVentesTerminees(String pseudo, String contenuRecherche, String categorie) {
		List<ArticleVendu> listeVentesTerminees =enchereDAO.selectVentesTerminees(pseudo,contenuRecherche,categorie);
		return listeVentesTerminees;
	}

}
