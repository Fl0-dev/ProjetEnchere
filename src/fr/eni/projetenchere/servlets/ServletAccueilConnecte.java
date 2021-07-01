package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAccueilConnecte
 */
@WebServlet(urlPatterns = { "/AccueilConnecte", "/deconnexion", "/recherche" })
public class ServletAccueilConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletAccueilConnecte() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// on regarde quelle URL a été utilisée pour accéder à la servlet
		String urlUtilisee = request.getServletPath();
		// si c'est l'url /déconnexion, on ferme la session et retour vers page
		// d'accueil non connecté
		if (urlUtilisee.equals("/deconnexion")) {
			HttpSession session = request.getSession();
			session.removeAttribute("utilisateur");
			session.invalidate();
			request.setAttribute("messageDeconnexion", "Vous êtes bien déconnecté");
			this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);

		} else {
			doPost(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		///////////////////////////// Première arrivée sur la
		///////////////////////////// JSP////////////////////////////////////////////////
		List<ArticleVendu> listeAafficher = null;
		// ouverture de la session et récupération de l'utilisateur
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");

		// on récupère la liste des catégories présentes en base de données pour charger
		// dans la JSP
		List<Categorie> listeCategoriesConnecte = EnchereManager.getInstance().selectCategorie();
		request.setAttribute("listeCategories", listeCategoriesConnecte);
		
		// on regarde quelle URL a été utilisée pour accéder à la servlet
		String urlUtilisee = request.getServletPath();
		
		if (urlUtilisee.equals("/AccueilConnecte")) {
		// on récupère la liste des catégories présentes en base de données
		listeCategoriesConnecte = EnchereManager.getInstance().selectCategorie();
		request.setAttribute("listeCategories", listeCategoriesConnecte);

		// on récupère les enchères en cours
		listeAafficher = EnchereManager.getInstance().selectEncheresOuvertesA(utilisateurSession.getPseudo());

		// on envoie ça dans la requête
		request.setAttribute("listeAafficher", listeAafficher);
		System.out.println(listeAafficher);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPAccueilConnecte.jsp");
		rd.forward(request, response);
		}
		///////////////////////////// Passage après une
		///////////////////////////// recherche////////////////////////////////////////////////

		// si c'est l'url /recherche, on récupère les infos saisies par l'utilisateur
		
		if (urlUtilisee.equals("/recherche")) {

			// je récupère les résultats de la recherche
			String contenuRecherche = request.getParameter("contenuRecherche").trim();
			String categorie = request.getParameter("categorie");
			String choixAchatVente = request.getParameter("choixAchatVente");
			// choix achats
			if (choixAchatVente.equals("achats")) {
				String choixRadioAchats = request.getParameter("encheres");
				switch (choixRadioAchats) {
				// selon le choix
				case "encheresOuvertes": 
					listeAafficher = EnchereManager.getInstance().selectEncheresOuvertes(utilisateurSession.getPseudo(),contenuRecherche,categorie);
					break;
				case "mesEncheres":
					listeAafficher = EnchereManager.getInstance().selectMesEncheres(utilisateurSession.getPseudo(),contenuRecherche,categorie);
					break;
				case "mesEncheresRemportees":
					//listeAafficher = EnchereManager.getInstance().selectMesEncheresRemportees(utilisateurSession.getPseudo(),contenuRecherche,categorie);
					break;
				}

				// choix ventes
			} else {
				String choixRadioVentes = request.getParameter("ventes");
			}

			// si l'option choisit est "toutes", on recherche uniquement par nom d'article
			if (categorie.equals("0")) {
				// listeAafficher =
				// EnchereManager.getInstance().selectEnchereByArticle(contenuRecherche);
			} else {
				// sinon, afficher résultats de la recherche par catégorie ET nom article
				// listeEncheresEnCoursConnecte =
				// EnchereManager.getInstance().selectEnchereByCatAndArt(contenuRecherche,categorie);
			}

			request.setAttribute("listeAafficher", listeAafficher);
			// renvoie vers la JSP après traitement
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPAccueilConnecte.jsp");
			rd.forward(request, response);
		}

	}
}