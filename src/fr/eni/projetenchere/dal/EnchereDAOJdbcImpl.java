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
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	@Override
	public Utilisateur insertUtilisateur(Utilisateur newUtilisateur) {
		// requête SQL
		final String INSERT_UTILSATEUR = "insert into UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		// ouverture de la connexion à la DB
		try (Connection connection = JdbcTools.getConnection()) {
			try {
				// désactive l'auto-commit (pour pouvoir faire une transaction)
				connection.setAutoCommit(false);
				PreparedStatement requete = connection.prepareStatement(INSERT_UTILSATEUR,
						PreparedStatement.RETURN_GENERATED_KEYS);
				requete.setString(1, newUtilisateur.getPseudo());
				requete.setString(2, newUtilisateur.getNom());
				requete.setString(3, newUtilisateur.getPrenom());
				requete.setString(4, newUtilisateur.getEmail());
				if (newUtilisateur.getTelephone() != null) {
					requete.setString(5, newUtilisateur.getTelephone());
				}
				requete.setString(6, newUtilisateur.getRue());
				requete.setString(7, newUtilisateur.getCodePostal());
				requete.setString(8, newUtilisateur.getVille());
				requete.setString(9, newUtilisateur.getMotDePasse());
				requete.setInt(10, 100);
				requete.setInt(11, 0);
				// exécution de la requête
				requete.executeUpdate();
				
				// récupère le noUtilisateur
				ResultSet rs = requete.getGeneratedKeys();
				if (rs.next()) {
					newUtilisateur.setNoUtilisateur(rs.getInt(1));
				}
				// valide
				connection.commit();
				// si souci
			} catch (SQLException e) {
				e.printStackTrace();
				// efface l'insert
				connection.rollback();
				// TODO gestion exception
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO gestion exception
		}
		return newUtilisateur;
	}

	@Override
	/**
	 * selectionne en DB les catégories et les met dans une liste
	 * 
	 * @return listeCategories
	 */
	public List<Categorie> selectCategorie() {
		// création de la liste vide
		List<Categorie> listeCategories = new ArrayList<>();
		// requête SQL
		final String SELECT_ALL_CAT = "select * from CATEGORIES";
		// ouverture de la connexion vers DB
		try (Connection connection = JdbcTools.getConnection(); Statement requete = connection.createStatement()) {
			// récupération du résultat
			ResultSet rs = requete.executeQuery(SELECT_ALL_CAT);
			// création des variables
			Categorie categorie;
			while (rs.next()) {
				int noCategorie = rs.getInt("no_categorie");
				String libelle = rs.getString("libelle");
				// utilisation des réultats
				categorie = new Categorie(noCategorie, libelle);
				// ajout dans la liste
				listeCategories.add(categorie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeCategories;
	}

	/**
	 * selectionne en DB toutes les enchères en cours et les met dans une liste
	 * 
	 * @return listeEncheres
	 */
	@Override
	public List<Enchere> selectAllEnchere() {
		// création de la liste vide
		List<Enchere> listeEncheres = new ArrayList<>();

		// requête SQL
		final String SELECT_ALL_ENCHERE = "select a.date_fin_encheres, montant_enchere, a.nom_article, u.pseudo from ENCHERES as e "
				+ "inner join ARTICLES_VENDUS as a on a.no_article= e.no_article "
				+ "inner join UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur "
				+ "where (date_debut_encheres < GETDATE() and date_fin_encheres > GETDATE())"
				+ "order by date_enchere desc;";

		// ouverture de la connexion vers DB
		try (Connection connection = JdbcTools.getConnection(); Statement requete = connection.createStatement()) {
			// récupération du résultat
			ResultSet rs = requete.executeQuery(SELECT_ALL_ENCHERE);

			// création des variables
			ArticleVendu articleVendu;
			Utilisateur utilisateur;
			Enchere enchere;

			// récupération en Java
			while (rs.next()) {
				LocalDate dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				int montantEnchere = rs.getInt("montant_enchere");
				String nomArticle = rs.getString("nom_article");
				String pseudo = rs.getString("pseudo");

				// utilisation des résultats
				utilisateur = new Utilisateur(pseudo);
				articleVendu = new ArticleVendu(nomArticle, dateFinEnchere);
				enchere = new Enchere(montantEnchere, utilisateur, articleVendu);

				// ajout dans la liste d'enchères
				listeEncheres.add(enchere);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeEncheres;
	}

	@Override
	public List<Enchere> selectEnchereByArticle(String nom_article) {
		// création de la liste vide
		List<Enchere> listeEncheresByNom = new ArrayList<>();
		// création des variables
		ArticleVendu articleVendu;
		Utilisateur utilisateur;
		Enchere enchere;

		// requête SQL
		final String SELECT_ALL_ENCHERE_BY_NOM = "select a.date_fin_encheres, montant_enchere, a.nom_article, u.pseudo from ENCHERES as e "
				+ "inner join ARTICLES_VENDUS as a on a.no_article= e.no_article "
				+ "inner join UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur "
				+ "where a.nom_article like ? and (date_debut_encheres < GETDATE() and date_fin_encheres > GETDATE())"
				+ "order by date_enchere desc;";

		// ouverture de la connexion à la DB
		try (Connection connection = JdbcTools.getConnection();
				PreparedStatement requete = connection.prepareStatement(SELECT_ALL_ENCHERE_BY_NOM)) {
			// initialisation de la requête
			
			requete.setString(1, "%" + nom_article + "%");
			// récupération du résultat
			ResultSet rs = requete.executeQuery();

			while (rs.next()) {
				LocalDate dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				int montantEnchere = rs.getInt("montant_enchere");
				String nomArticle = rs.getString("nom_article");
				String pseudo = rs.getString("pseudo");

				// utilisation des résultats
				utilisateur = new Utilisateur(pseudo);
				articleVendu = new ArticleVendu(nomArticle, dateFinEnchere);
				enchere = new Enchere(montantEnchere, utilisateur, articleVendu);

				// ajout dans la liste d'enchères
				listeEncheresByNom.add(enchere);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	return listeEncheresByNom;

	}

	/**
	 * selectionne en DB toutes les enchères en cours selon
	 * 
	 * @param nomArticle
	 * @param noCategorie
	 * @return listeEncheres
	 */
	@Override
	public List<Enchere> selectEnchereByCatAndArt(String nom_article, int noCategorie) {

		// création de la liste vide
		List<Enchere> listeEncheresBy = new ArrayList<>();

		// création des variables
		ArticleVendu articleVendu;
		Utilisateur utilisateur;
		Enchere enchere;

		// requête SQL
		final String SELECT_ALL_ENCHERE_BY = "select a.date_fin_encheres, montant_enchere, a.nom_article, u.pseudo from ENCHERES as e "
				+ "inner join ARTICLES_VENDUS as a on a.no_article= e.no_article "
				+ "inner join UTILISATEURS as u on a.no_utilisateur = u.no_utilisateur "
				+ "where a.no_categorie=? and a.nom_article like ? and (date_debut_encheres < GETDATE() and date_fin_encheres > GETDATE())"
				+ "order by date_enchere desc;";

		// ouverture de la connexion à la DB
		try (Connection connection = JdbcTools.getConnection();
				PreparedStatement requete = connection.prepareStatement(SELECT_ALL_ENCHERE_BY)) {

			// initialisation de la requête
			requete.setInt(1, noCategorie);
			requete.setString(2, "%" + nom_article + "%");
			// récupération du résultat
			ResultSet rs = requete.executeQuery();

			while (rs.next()) {
				LocalDate dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				int montantEnchere = rs.getInt("montant_enchere");
				String nomArticle = rs.getString("nom_article");
				String pseudo = rs.getString("pseudo");

				// utilisation des résultats
				utilisateur = new Utilisateur(pseudo);
				articleVendu = new ArticleVendu(nomArticle, dateFinEnchere);
				enchere = new Enchere(montantEnchere, utilisateur, articleVendu);

				// ajout dans la liste d'enchères
				listeEncheresBy.add(enchere);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeEncheresBy;
	}

	/**
	 * selectionne en base de données email, pseudo et mot de passe des utilisateurs
	 * et les met dans une liste
	 * 
	 * @return listeEncheres
	 */
	@Override
	public List<Utilisateur> selectConnexion() {
		List<Utilisateur> listeUtilisateurConnexion = new ArrayList<>();

		final String SELECT_UTILISATEUR_CONNEXION = "SELECT pseudo, email, mot_de_passe FROM utilisateurs;";

		// ouverture de la connexion vers DB
		try (Connection connection = JdbcTools.getConnection(); Statement requete = connection.createStatement()) {
			// récupération du résultat
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
	
	/**
	 * sélectionne un utilisateur à partir de son pseudo
	 * @return utilisateur
	 */
	@Override
	public Utilisateur selectUtilisateurByPseudo(String pseudo) {
		
		// création des variables
		Utilisateur utilisateur = new Utilisateur();
		
		// requête SQL
		final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT no_utilisateur, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs WHERE pseudo=?;";

		// ouverture de la connexion à la DB
				try (Connection connection = JdbcTools.getConnection();
						PreparedStatement requete = connection.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO)) {

			// initialisation de la requête
			requete.setString(1, pseudo);
			
			// récupération du résultat
			ResultSet rs = requete.executeQuery();
						
			while (rs.next()) {
				
				int numUtilisateur = rs.getInt("no_utilisateur");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				String mdp = rs.getString("mot_de_passe");
				int credit = rs.getInt("credit");
				boolean administrateur = rs.getBoolean("administrateur");
				
				// utilisation des résultats
				utilisateur = new Utilisateur(numUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, credit,administrateur);

				utilisateur = new Utilisateur(numUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, credit, administrateur);

		
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return utilisateur;

	}
	
}
