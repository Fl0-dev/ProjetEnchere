package fr.eni.projetenchere.bll;

import java.util.List;

import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DAOFactory;
import fr.eni.projetenchere.dal.EnchereDAO;

/**
 * Classe de la BLL contenant les méthodes
 * utilisées en Vue et utilisant les méthodes de la DAL
 * @author Florian
 * @version 1.0
 * @date 22/06/21 
 */
public class EnchereManager {

	//création d'instance
	private static EnchereManager instance;
	
	//Singleton du manager
	public static EnchereManager getInstance() {
		if(instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
		
	//
	private EnchereDAO enchereDAO;
	
	//constructeur
	private EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	
	/**
	 * récupère toutes les enchères en cours dans
	 * @return listeEncheres 
	 * avec enchère(dateEnchere,montantEnchere,utilisateur(pseudo), articleVendu(nomArticle)) 
	 */
	public List<Enchere> selectAllEnchere(){
		//création de la liste des enchères
		List<Enchere> listeEncheres;
		
		listeEncheres = enchereDAO.selectAllEnchere();
		
		return listeEncheres;
		//TODO gestion des exceptions
	}


	/**
	 * récupère email, pseudo et mot de passe des utilisateurs pour l'accès à la connexion
	 * @return listeUtilisateurConnexion
	 *  
	 */
	public List<Utilisateur> selectConnexion() {
		//création de la liste des utilisateurs pour la connexion
		List<Utilisateur> listeUtilisateurConnexion;
		
		listeUtilisateurConnexion = enchereDAO.selectConnexion();
		return listeUtilisateurConnexion;
		//TODO gestion des exceptions
	}
	
	/**
	 * récupère toutes les enchères selon
	 * @param nomArticle
	 * @param noCategorie
	 * @return listeEncheresByCategorie
	 * avec enchère(dateEnchere,montantEnchere,utilisateur(pseudo), articleVendu(nomArticle)) 
	 */
	public List<Enchere> selectEnchereByCatAndArt(String nomArticle,int noCategorie){
		List<Enchere> listeEncheresByCategorie;
		
		listeEncheresByCategorie =enchereDAO.selectEnchereByCatAndArt(nomArticle,noCategorie);
		
		return listeEncheresByCategorie;
	}
	
	
	
	
	
	
	
	
	
}
