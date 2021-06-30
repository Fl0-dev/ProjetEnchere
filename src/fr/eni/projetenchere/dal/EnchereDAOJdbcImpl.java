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
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.bo.Utilisateur;


public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	
	@Override
	/* (non-Javadoc)
	 * @see fr.eni.projetenchere.dal.EnchereDAO#updateActif(fr.eni.projetenchere.bo.Utilisateur)
	 */
	public void updateEtat(Utilisateur utilisateurSession) {
		final String UPDATE_ACTIF ="update UTILISATEURS set etat = 0 where no_utilisateur=?;";
		// ouverture de la connexion à la DB
		try (Connection connection = JdbcTools.getConnection()){
			PreparedStatement requete = connection.prepareStatement(UPDATE_ACTIF);
			requete.setInt(1, utilisateurSession.getNoUtilisateur());
			//exécution de la requête
			requete.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	
	@Override
	/**
	 * update en DB le profil d'un utilisateur
	 */
	public void updateUtilisateur(Utilisateur utilisateur) {
		
		// requête SQL
		final String UPDATE_UTILISATEUR ="update UTILISATEURS set pseudo=?,nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=? where no_utilisateur=?;";
		// ouverture de la connexion à la DB
				try (Connection connection = JdbcTools.getConnection()){
					PreparedStatement requete = connection.prepareStatement(UPDATE_UTILISATEUR);
					//initialisation de la requête
					requete.setString(1, utilisateur.getPseudo());
					requete.setString(2, utilisateur.getNom());
					requete.setString(3, utilisateur.getPrenom());
					requete.setString(4, utilisateur.getEmail());
					//if (utilisateur.getTelephone()!=null) {
						requete.setString(5, utilisateur.getTelephone());
					//}
					requete.setString(6, utilisateur.getRue());
					requete.setString(7, utilisateur.getCodePostal());
					requete.setString(8, utilisateur.getVille());
					requete.setString(9, utilisateur.getMotDePasse());
					requete.setInt(10, utilisateur.getNoUtilisateur());
					//exécution de la requête
					requete.executeUpdate();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
	}
	
	
	
	@Override
	/**
	 * insert en DB un nouvel utilisateur
	 * 
	 * @return newUtilisateur
	 */
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
				//initialisation de la requête
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

		final String SELECT_UTILISATEUR_CONNEXION = "SELECT pseudo, email, mot_de_passe, etat FROM utilisateurs;";

		// ouverture de la connexion vers DB
		try (Connection connection = JdbcTools.getConnection(); Statement requete = connection.createStatement()) {
			// récupération du résultat
			ResultSet rs = requete.executeQuery(SELECT_UTILISATEUR_CONNEXION);

			Utilisateur utilisateur;

			while (rs.next()) {

				String pseudo = rs.getString("pseudo");
				String email = rs.getString("email");
				String mot_de_passe = rs.getString("mot_de_passe");
				Boolean etat = rs.getBoolean("etat");

				utilisateur = new Utilisateur(pseudo, email, mot_de_passe,etat);

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
		final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT no_utilisateur, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur,etat FROM utilisateurs WHERE pseudo=?;";

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
				boolean etat = rs.getBoolean("etat");
				
				// utilisation des résultats
				utilisateur = new Utilisateur(numUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, credit,administrateur,etat);

				

		
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return utilisateur;

	}
	
	/**
	 * Requêtes SQL préparées pour la page d'accueil
	 */
	
	// affiche toutes les enchères ouvertes
	@Override
	public List<ArticleVendu> selectAllVentesEnCours() {
		
		// création de la liste vide
				List<ArticleVendu> listeArticlesEnVente = new ArrayList<>();

				// création des variables
				ArticleVendu articleVendu;
				Utilisateur utilisateur;
				Enchere enchere;

		// requête SQL
		final String SELECT_ALL_VENTES_EN_COURS =
				"SELECT MAX(e.montant_enchere) as enchere_max, " + 
				"a.nom_article, vendeur.pseudo, date_fin_encheres, c.no_categorie, c.libelle " + 
				"FROM articles_vendus AS a  " + 
				"inner join CATEGORIES as c on c.no_categorie = a.no_categorie " + 
				"inner join UTILISATEURS as vendeur on a.no_utilisateur = vendeur.no_utilisateur " + 
				"left join ENCHERES as e on a.no_article = e.no_article " + 
				"left join UTILISATEURS as acheteur on e.no_utilisateur = acheteur.no_utilisateur " + 
				"where (date_debut_encheres < GETDATE() and date_fin_encheres > GETDATE()) " + 
				"group by a.nom_article, vendeur.pseudo, date_fin_encheres, c.no_categorie, c.libelle;";

		// ouverture de la connexion à la DB
		try (Connection connection = JdbcTools.getConnection();
				PreparedStatement requete = connection.prepareStatement(SELECT_ALL_VENTES_EN_COURS)) {
			
		// récupération du résultat
			ResultSet rs = requete.executeQuery();

			while (rs.next()) {
				LocalDate dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				 int enchereMax = rs.getInt("enchere_max");
				String nomArticle = rs.getString("nom_article");
				String pseudoVendeur = rs.getString("pseudo");

				// utilisation des résultats
				utilisateur = new Utilisateur(pseudoVendeur);
				
				enchere = new Enchere();
				enchere.setMontant_enchere(enchereMax);
				
				articleVendu = new ArticleVendu();
				articleVendu.setDateFinEncheres(dateFinEnchere);
				articleVendu.setNomArticle(nomArticle);
				articleVendu.setEnchereMax(enchere);
				articleVendu.setUtilisateur(utilisateur);
		
				// ajout dans la liste d'enchères
				listeArticlesEnVente.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	return listeArticlesEnVente;

	}

	/**
	 * sélectionne un utilisateur à partir de son numéro utilisateur
	 * @return utilisateur
	 */
	@Override
	public Utilisateur selectUserById(int no_utilisateur) {
		
		// création des variables
		Utilisateur utilisateur = new Utilisateur();
		
		// requête SQL
		final String SELECT_USER_BY_ID = "SELECT pseudo, nom, prenom, email, telephone, rue, "
				+ "code_postal, ville, mot_de_passe, credit, administrateur, etat "
				+ "FROM utilisateurs WHERE no_utilisateur=?;";

		// ouverture de la connexion à la DB
				try (Connection connection = JdbcTools.getConnection();
						PreparedStatement requete = connection.prepareStatement(SELECT_USER_BY_ID)) {

			// initialisation de la requête
			requete.setInt(1, no_utilisateur);
			
			// récupération du résultat
			ResultSet rs = requete.executeQuery();
						
			while (rs.next()) {
				
				String pseudo = rs.getString("pseudo");
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
				boolean etat = rs.getBoolean("etat");
				
				// utilisation des résultats
				utilisateur = new Utilisateur(no_utilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, credit,administrateur,etat);

			}
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return utilisateur;

	}
	
	/**
	 * sélectionne un article à partir de son id
	 * @return articleVendu
	 */
		@Override
		public ArticleVendu selectArticleById(int no_article) {
			
			// création des variables
					ArticleVendu articleVendu = new ArticleVendu();
					Utilisateur vendeur;
					Utilisateur acheteur;
					Enchere enchere = new Enchere();
					Retrait retrait = new Retrait();
					Categorie categorie;

			// requête SQL
			final String SELECT_ARTICLE_BY_ID =
					"SELECT a.nom_article, a.description, c.no_categorie, MAX(e.montant_enchere) as enchere_max,  " + 
					"a.prix_initial, a.date_fin_encheres, a.no_retrait, vendeur.pseudo as vendeur " + 
					"FROM articles_vendus AS a " + 
					"inner join CATEGORIES as c on c.no_categorie = a.no_categorie " + 
					"inner join UTILISATEURS as vendeur on a.no_utilisateur = vendeur.no_utilisateur " + 
					"left join ENCHERES as e on a.no_article = e.no_article " + 
					"left join UTILISATEURS as acheteur on e.no_utilisateur = acheteur.no_utilisateur " + 
					"WHERE a.no_article = ? " + 
					"GROUP BY a.nom_article, a.description, c.no_categorie, " + 
					"a.prix_initial, a.date_fin_encheres, a.no_retrait, vendeur.pseudo;";

			// ouverture de la connexion à la DB
			try (Connection connection = JdbcTools.getConnection();
					PreparedStatement requete = connection.prepareStatement(SELECT_ARTICLE_BY_ID)) {
				
			// initialisation de la requête
				requete.setInt(1, no_article);
				
			// récupération du résultat
				ResultSet rs = requete.executeQuery();

				while (rs.next()) {
					
					int enchereMax = rs.getInt("enchere_max");
					String nomArticle = rs.getString("nom_article");
					String pseudoVendeur = rs.getString("vendeur");
					int noCategorie = rs.getInt("no_categorie");
					int prixInitial = rs.getInt("prix_initial");
					LocalDate dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				
					// utilisation des résultats
					vendeur = selectUtilisateurByPseudo(pseudoVendeur);
									
					categorie = selectCategorieById(noCategorie);
					retrait = selectRetraitByArticleId(no_article);
					
					enchere.setMontant_enchere(enchereMax);
					enchere.setUtilisateur(acheteur);
					
					articleVendu.setDateFinEncheres(dateFinEnchere);
					articleVendu.setMiseAPrix(prixInitial);
					articleVendu.setNomArticle(nomArticle);
					articleVendu.setEnchereMax(enchere);
					articleVendu.setUtilisateur(vendeur);
					articleVendu.setCategorieArticle(categorie);
					articleVendu.setLieuRetrait(retrait);
		
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		return articleVendu;

		}

/**
 * sélectionne une catégorie à partir de son numéro (id)
 * @param noCategorie
 * @return categorie
 */
		@Override
	public Categorie selectCategorieById(int noCategorie) {
		// création des variables
			Categorie categorie = new Categorie();

		// requête SQL
		final String SELECT_CAT_BY_ID =
				"SELECT libelle FROM CATEGORIES WHERE no_categorie=?;";

		// ouverture de la connexion à la DB
		try (Connection connection = JdbcTools.getConnection();
				PreparedStatement requete = connection.prepareStatement(SELECT_CAT_BY_ID)) {
			
			// initialisation de la requête
			requete.setInt(1, noCategorie);
			
		// récupération du résultat
			ResultSet rs = requete.executeQuery();

			while (rs.next()) {
				
				String libelle = rs.getString("libelle");

				// utilisation des résultats
				categorie.setLibelle(libelle);
				categorie.setNoCategorie(noCategorie);
	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	return categorie;
	}
		
		
		@Override
		/* (non-Javadoc)
		 * @see fr.eni.projetenchere.dal.EnchereDAO#selectEncheresOuvertes()
		 */
		public List<ArticleVendu> selectEncheresOuvertes(String pseudo){
			
			// création de la liste vide
			List<ArticleVendu> listeEncheresOuvertes = new ArrayList<>();

			// création des variables
			ArticleVendu articleVendu = new ArticleVendu();
			Utilisateur vendeur = new Utilisateur();
			Enchere enchereMax = new Enchere();
			
			//requête SQL
			final String SELECT_ENCHERES_OUVERTES = "SELECT MAX(e.montant_enchere) as enchere_max, a.prix_initial, " + 
					"a.nom_article, vendeur.pseudo as vendeur, date_fin_encheres " + 
					"FROM articles_vendus AS a \n" + 
					"inner join CATEGORIES as c on c.no_categorie = a.no_categorie " + 
					"inner join UTILISATEURS as vendeur on a.no_utilisateur = vendeur.no_utilisateur " + 
					"left join ENCHERES as e on a.no_article = e.no_article " + 
					"left join UTILISATEURS as acheteur on e.no_utilisateur = acheteur.no_utilisateur " + 
					"where (date_debut_encheres < GETDATE() and date_fin_encheres > GETDATE()) and vendeur.pseudo <> ? " + 
					"group by a.nom_article, vendeur.pseudo, date_fin_encheres, a.prix_initial;";
			
			// ouverture de la connexion à la DB
			try (Connection connection = JdbcTools.getConnection();
					PreparedStatement requete = connection.prepareStatement(SELECT_ENCHERES_OUVERTES)) {
				
				// initialisation de la requête
				requete.setString(1, pseudo);
				
				// récupération du résultat
				ResultSet rs = requete.executeQuery();
							
				while (rs.next()) {
					int enchere = rs.getInt("enchere_max");
					int miseAPrix = rs.getInt("prix_initial");
					String nomArticle = rs.getString("nom_article");
					String vendeurPseudo = rs.getString("vendeur");
					LocalDate dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
					
					// utilisation des résultats
					vendeur.setPseudo(vendeurPseudo);
					articleVendu.setNomArticle(nomArticle);
					
					//si il n'y a pas encore d'enchère on utilise la mie à prix comme enchère max
					if (enchere!=0) {
						enchereMax.setMontant_enchere(enchere);
					}else {
						enchereMax.setMontant_enchere(miseAPrix);
					}
					articleVendu.setUtilisateur(vendeur);
					articleVendu.setDateFinEncheres(dateFinEnchere);
					articleVendu.setEnchereMax(enchereMax);
					
					//ajout dans la liste
					listeEncheresOuvertes.add(articleVendu);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listeEncheresOuvertes;
		}
		
		
		
		
		
		
		
	
		/**
		 * sélectionne un lieu de retrait à partir de son numéro (id)
		 * @param noArticle
		 * @return retrait
		 */
				@Override
			public Retrait selectRetraitByArticleId(int noArticle) {
				// création des variables
					Retrait retrait = new Retrait();

				// requête SQL
				final String SELECT_RETRAIT_BY_ARTID =
						"SELECT rue, ville, code_postal " + 
						"FROM RETRAITS as  " + 
						"INNER JOIN ARTICLES_VENDUS as a on a.no_retrait = r.no_retrait " + 
						"WHERE a.no_article = ?;";

				// ouverture de la connexion à la DB
				try (Connection connection = JdbcTools.getConnection();
						PreparedStatement requete = connection.prepareStatement(SELECT_RETRAIT_BY_ARTID)) {
					
					// initialisation de la requête
					requete.setInt(1, noArticle);
					
				// récupération du résultat
					ResultSet rs = requete.executeQuery();

					while (rs.next()) {
						
						String rue = rs.getString("rue");
						String codePostal = rs.getString("codePostal");
						String ville = rs.getString("ville");

						// utilisation des résultats
						retrait.setCode_postal_retrait(codePostal);
						retrait.setRue_retrait(rue);
						retrait.setVille_retrait(ville);
			
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			return retrait;
			}
			
}
