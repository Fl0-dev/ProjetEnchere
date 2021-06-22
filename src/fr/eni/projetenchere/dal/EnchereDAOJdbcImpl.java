package fr.eni.projetenchere.dal;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;


public class EnchereDAOJdbcImpl implements EnchereDAO {

	/**
	 * selectionne en DB toutes les enchères en cours et les met dans une liste
	 * @return listeEncheres
	 */
	@Override
	public List<Enchere> selectAllEnchere(){
		//création de la liste vide
		List<Enchere> listeEncheres = new ArrayList<>();
		
		//requête SQL
		final String SELECT_ALL_ENCHERE = "select date_enchere, montant_enchere, a.nom_article, u.pseudo from ENCHERES as e "
				+ "inner join UTILISATEURS as u on e.no_utilisateur = u.no_utilisateur "
				+ "inner join ARTICLES_VENDUS as a on a.no_article= e.no_article "
				+ "where a.prix_vente is null "
				+ "order by date_enchere desc;";
		
		//ouverture de la connexion vers DB
		try (Connection connection = JdbcTools.getConnection();
	             Statement requete = connection.createStatement()) {
			//récupération du résultat
			ResultSet rs = requete.executeQuery(SELECT_ALL_ENCHERE);
			
			//création des variables
			ArticleVendu articleVendu;
			Utilisateur utilisateur;
			Enchere enchere;
			
			//récupération en Java
			while (rs.next()) {
				LocalDate dateEnchere = rs.getDate("date_enchere").toLocalDate();
				int montantEnchere = rs.getInt("montant_enchere");
				String nomArticle = rs.getString("nom_article");
				String pseudo = rs.getString("pseudo");
				
				//utilisation des résultats
				utilisateur = new Utilisateur(pseudo);
				articleVendu = new ArticleVendu(nomArticle);
				enchere = new Enchere(dateEnchere,montantEnchere,utilisateur,articleVendu);
				
				//ajout dans la liste d'enchères
				listeEncheres.add(enchere);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listeEncheres;
	}
	
	/**
	 * selectionne en DB toutes les enchères en cours selon
	 * @param nomArticle
	 * @param noCategorie
	 * @return listeEncheres
	 */
	@Override
	public List<Enchere> selectEnchereByCatAndArt(String nom_article, int noCategorie){
		
		//création de la liste vide
		List<Enchere> listeEncheresBy = new ArrayList<>();
		
		//création des variables
		ArticleVendu articleVendu;
		Utilisateur utilisateur;
		Enchere enchere;
		
		//requête SQL
		final String SELECT_ALL_ENCHERE_BY ="select date_enchere, montant_enchere, a.nom_article, u.pseudo from ENCHERES as e "
				+ "inner join UTILISATEURS as u on e.no_utilisateur = u.no_utilisateur "
				+ "inner join ARTICLES_VENDUS as a on a.no_article= e.no_article "
				+ "where a.no_categorie=? and a.nom_article like '%?%' and a.prix_vente is null "
				+ "order by date_enchere desc;";
		
		//ouverture de la connexion à la DB
		try (Connection connection = JdbcTools.getConnection();
	             PreparedStatement requete = connection.prepareStatement(SELECT_ALL_ENCHERE_BY)) {
			
			//initialisation de la requête
			requete.setInt(1, noCategorie);
			requete.setString(2, nom_article);
			//récupération du résultat
			ResultSet rs = requete.executeQuery();
			
			while (rs.next()) {
				LocalDate dateEnchere = rs.getDate("date_enchere").toLocalDate();
				int montantEnchere = rs.getInt("montant_enchere");
				String nomArticle = rs.getString("nom_article");
				String pseudo = rs.getString("pseudo");
				
				//utilisation des résultats
				utilisateur = new Utilisateur(pseudo);
				articleVendu = new ArticleVendu(nomArticle);
				enchere = new Enchere(dateEnchere,montantEnchere,utilisateur,articleVendu);
				
				//ajout dans la liste d'enchères
				listeEncheresBy.add(enchere);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeEncheresBy;
	}
	
	
	
	/**
	 * selectionne en base de données email, pseudo et mot de passe des utilisateurs et les met dans une liste
	 * @return listeEncheres
	 */
	@Override
	public List<Utilisateur> selectConnexion() {
		List<Utilisateur> listeUtilisateurConnexion = new ArrayList<>();
		
		final String SELECT_UTILISATEUR_CONNEXION = "SELECT pseudo, email, mot_de_passe FROM utilisateurs;";
		
		//ouverture de la connexion vers DB
				try (Connection connection = JdbcTools.getConnection();
			             Statement requete = connection.createStatement()) {
					//récupération du résultat
					ResultSet rs = requete.executeQuery(SELECT_UTILISATEUR_CONNEXION);
					
					Utilisateur utilisateur;
					
					while (rs.next()) {
						
						String pseudo = rs.getString("pseudo");
						String email = rs.getString("email");
						String mot_de_passe = rs.getString("mot_de_passe");
						
						utilisateur = new Utilisateur(pseudo, email, mot_de_passe);
						
						listeUtilisateurConnexion.add(utilisateur);
					}
				} catch (SQLException e) {
					// TODO: handle exception
				}
				
				
		
		return listeUtilisateurConnexion;
		
	}
	
}
