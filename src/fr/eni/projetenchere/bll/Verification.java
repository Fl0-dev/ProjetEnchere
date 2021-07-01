package fr.eni.projetenchere.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;
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
		public void validationChamp30 (String test) throws Exception {
			if (test == null || test.trim().length() == 0) {
				throw new Exception("Le champ est obligatoire");
			}
			if (test.length()>30) {
				throw new Exception("Pas plus de 30 caractères");
			}
		}
		
		/**
		 * permet de vérifier si le champ est vide si l'utilisateur 
		 * fait sauter les balises html
		 * et que le champ ne dépasse pas 20 caractères
		 * @param test
		 * @throws Exception
		 */
		public void validationChamp20 (String test) throws Exception {
			if (test == null || test.trim().length() == 0) {
				throw new Exception("Le champ est obligatoire");
			}
			if (test.length()>20) {
				throw new Exception("Pas plus de 20 caractères");
			}
		}
		
		/**
		 * permet de vérifier si le champ est vide si l'utilisateur 
		 * fait sauter les balises html
		 * et que le champ ne dépasse pas 10 caractères
		 * @param test
		 * @throws Exception
		 */
		public void validationChamp10 (String test) throws Exception {
			if (test == null || test.trim().length() == 0) {
				throw new Exception("Le champ est obligatoire");
			}
			if (test.length()>10) {
				throw new Exception("Pas plus de 10 caractères");
			}
		}
		
		/**
		 * permet de vérifier si le champ est vide si l'utilisateur 
		 * fait sauter les balises html
		 * et que le champ ne dépasse pas 15 caractères
		 * @param test
		 * @throws Exception
		 */
		public void validationChampTel (String test) throws Exception {
			if (test != null && test.length()>15) {
				throw new Exception("Pas plus de 15 caractères");
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
			if (!utilisateur.getMotDePasse().equals(motDePasse)) {
				throw new Exception("Le mot de passe de "+pseudo+" est incorrect");
			}
			
		}
		
		/**
		 * Vérifie que l'offre saisie est strictement supérieure à la meilleure offre
		 * @param montant_enchere
		 * @param no_article
		 * @throws Exception
		 */
		public void verifEnchere(Utilisateur utilisateur, int montant_enchere, ArticleVendu articleVendu) throws Exception {
						
			if (montant_enchere < articleVendu.getEnchereMax().getMontant_enchere()) {
				throw new Exception("Votre offre doit être strictement supérieure à la meilleure offre.");
			}
			
			if (montant_enchere == 0) {
				throw new Exception("Veuillez saisir un montant.");
			}
			
			// l'utilisateur a-t-il suffisamment de crédit pour faire cette offre ?
			//	if (utilisateur.getCredit() < montant_enchere) {
			//	throw new Exception("Votre crédit est insuffisant.");	
			//	}
				
		}
		
		/**
		 * Vérifie que le champ nom de l'article est bien rempli
		 * @param nomArticle
		 * @throws Exception 
		 */
		public void validationNomArticle(String nomArticle) throws Exception {
			if (nomArticle.isEmpty() || nomArticle == null) {
				throw new Exception("Veuillez saisir un nom d'article");
			}
			
		}
		
		/**
		 * Vérifie que le champ description est bien rempli
		 * @param description
		 * @throws Exception 
		 */
		public void validationDescription(String description) throws Exception {
			if (description.isEmpty() || description == null) {
				throw new Exception("Veuillez saisir une description");
					}
			
		}
		
		/**
		 * Vérifie que le champ description ne dépasse pas 300 caractères
		 * @param description
		 * @throws Exception 
		 */
		public void validationChamp300(String description) throws Exception {
			if (description == null || description.trim().length() == 0) {
				throw new Exception("Le champ est obligatoire");
			}
			if (description.length()>300) {
				throw new Exception("Pas plus de 30 caractères");
			}
			
		}
		
		/**
		 * Vérifie que le champ categorie est bien rempli
		 * @param categorie
		 * @throws Exception 
		 */
		public void validationCategorie(int categorie) throws Exception {
			if (categorie == 0) {
				throw new Exception ("Veuillez sélectionner une catégorie");
				
			}
			
		}
		
		/**
		 * Vérifie les champs dates nouvelle vente
		 * @param debutenchere
		 * @param finenchere
		 * @throws Exception 
		 */
		public void validationDateEnchere(LocalDate debutenchere, LocalDate finenchere) throws Exception {
			if (debutenchere == null || finenchere == null) {
				throw new Exception ("Veuillez saisir une date");
			}
			if (finenchere.isBefore(debutenchere)) {
				throw new Exception ("La date de début d'enchère doit être inférieure à la date de fin d'enchère");
				
			}
			if (debutenchere.isBefore(LocalDate.now())) {
				throw new Exception ("La date de début d'enchère doit être supérieure à la date du jour");
				
			}
		}
		/**
		 * Vérifie le champ prix initial
		 * @param prix_initial
		 * @throws Exception 
		 */
		public void validationPrixInitial(int prix_initial) throws Exception {
			if (prix_initial <= 0) {
				throw new Exception ("Le prix doit être positif");
				}
			
		}
		
		/**
		 * Vérifie les champs adresse de retrait
		 * @param rue
		 * @param codePostal
		 * @param ville
		 * @throws Exception 
		 */
		public void validationAdresseRetrait(String rue, String codePostal, String ville) throws Exception {
			if (rue.isEmpty() || rue == null || codePostal.isEmpty() || codePostal == null || ville.isEmpty()
					|| ville == null) {
				throw new Exception("L'adresse de retrait doit être renseignée");
				}
			
		}
		
		
		
}
