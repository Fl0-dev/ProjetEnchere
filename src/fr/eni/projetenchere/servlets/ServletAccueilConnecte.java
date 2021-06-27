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
import javax.websocket.Session;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;
import javafx.geometry.Side;

/**
 * Servlet implementation class ServletAccueilConnecte
 */
@WebServlet(urlPatterns = { "/ServletAccueilConnecte", "/deconnexion", "/AccueilNewProfil", "/recherche" })
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
			session.invalidate();
			String messageDeconnexion = "Vous êtes bien déconnecté";
			request.setAttribute("messageDeconnexion", messageDeconnexion);
			RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueil");
			rd.forward(request, response);
			// Si c'est l'url /AccueilNewProfil, on met un message de bienvenue dans la JSP

		}
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");

		// on récupère la liste des catégories présentes en base de données
		List<Categorie> listeCategories = EnchereManager.getInstance().selectCategorie();
		request.setAttribute("listeCategories", listeCategories);
		System.out.println(listeCategories);

		// on récupère les enchères en cours
		List<Enchere> listeEncheresEnCours = EnchereManager.getInstance().selectAllEnchere();

		// on envoie ça dans la requête
		request.setAttribute("listeEncheresEnCours", listeEncheresEnCours);
		System.out.println(listeEncheresEnCours);

		// on regarde quelle URL a été utilisée pour accéder à la servlet
		String urlUtilisee = request.getServletPath();
		// si c'est l'url /recherche, on récupère les infos saisies par l'utilisateur

		if (urlUtilisee.equals("/recherche")) {
			// on récupère la liste des catégories présentes en base de données
			List<Categorie> listeCategoriesConnecte = EnchereManager.getInstance().selectCategorie();
			request.setAttribute("listeCategories", listeCategoriesConnecte);

			// je récupère les résultats de la recherche
			String contenuRecherche = request.getParameter("contenuRecherche").trim();
			int categorie = Integer.valueOf(request.getParameter("categorie"));

			List<Enchere> listeEncheresEnCoursConnecte;

			// si l'option choisit est "toutes", on recherche uniquement par nom d'article
			if (categorie == 0) {
				listeEncheresEnCoursConnecte = EnchereManager.getInstance().selectEnchereByArticle(contenuRecherche);
			} else {
				// sinon, afficher résultats de la recherche par catégorie ET nom article
				listeEncheresEnCoursConnecte = EnchereManager.getInstance().selectEnchereByCatAndArt(contenuRecherche,
						categorie);
			}

			request.setAttribute("listeEncheresEnCours", listeEncheresEnCoursConnecte);

			

			

		}
		// renvoie vers la JSP après traitement
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPAccueilConnecte.jsp");
		rd.forward(request, response);
	}
}