package fr.eni.projetenchere.bll;

import java.util.List;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DAOFactory;
import fr.eni.projetenchere.dal.EnchereDAO;

/**
 * Classe de la BLL contenant les méthodes utilisées en pour
 * la vérification des informations rentrées
 * par l'utilisateur
 * @author Florian
 * @version 1.0
 * @date 27/06/21
 */
public class Verification {

	// création d'instance
		private static Verification instance;

		// Singleton du manager
		public static Verification getInstance() {
			if (instance == null) {
				instance = new Verification();
			}
			return instance;
		}
		/**
		 * si l'email est en DB
		 * 
		 * @param email
		 * @throws Exception
		 */
		public void validationEmail(String email) throws Exception {
			// appel du manager pour récupérer une liste de tous les emails en DB
			List<Utilisateur> listPseudoEmail = EnchereManager.getInstance().selectConnexion();
				for (Utilisateur utilisateur : listPseudoEmail) {
					if (email.equals(utilisateur.getEmail())) {
						throw new Exception("L'email existe déjà");
					}
				}
			}
		
		/**
		 * permet de vérifier si le champ est vide si l'utilisateur 
		 * fait sauter les balises html
		 * @param test
		 * @throws Exception
		 */
		public void validationChamp (String test) throws Exception {
			if (test == null || test.trim().length() == 0) {
				throw new Exception("Les champs marqués d'un * sont obligatoires");
			}
		}
		
		/**
		 * permet de vérifier si le pseudo est valide
		 * 
		 * @param pseudo
		 * @throws Exception
		 */
		public void validationPseudo(String pseudo) throws Exception {
			// appel du manager pour récupérer une liste de tous les pseudo en DB
			List<Utilisateur> listPseudoEmail = EnchereManager.getInstance().selectConnexion();
			// si le pseudo n'est pas null et pas vide
			
				if (!pseudo.matches("[a-zA-z0-9]*")) {
					throw new Exception("Le pseudo ne doit contenir que des caractères alphanumériques");
				}
				
				// compare le pseudo aux pseudos de la database
				for (Utilisateur utilisateur : listPseudoEmail) {
					
					if (pseudo.equals(utilisateur.getPseudo())) {
						throw new Exception("Le pseudo existe déjà");
					}
					
				}
			}
		
		/**
		 * Valide que le mot de passe rentré
		 * soit le même que la confirmation
		 * @param motDePasse
		 * @param confirmation
		 * @throws Exception
		 */
		public void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
			
				if (!motDePasse.equals(confirmation)) {
					throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
				}
		}
		
		/**
		 * Vérifie si le mot de passe correspond au pseudo
		 * @param pseudo
		 * @param motDePasse
		 * @throws Exception
		 */
		public void verifMdp(String pseudo, String motDePasse)  throws Exception{
			Utilisateur utilisateur;
			utilisateur = EnchereManager.getInstance().selectUtilisateurByPseudo(pseudo);
			if (utilisateur.getMotDePasse()!=motDePasse) {
				throw new Exception("Le mot de passe de "+pseudo+" est incorrect");
			}
			
		}
		
}
