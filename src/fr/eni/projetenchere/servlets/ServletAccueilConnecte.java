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
@WebServlet(urlPatterns = {"/ServletAccueilConnecte", "/deconnexion","/AccueilNewProfil"})
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
		//Si c'est l'url /AccueilNewProfil, on met un message de bienvenue dans la JSP
		
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

		// renvoie vers la JSP après traitement

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPAccueilConnecte.jsp");
		rd.forward(request, response);
	}

}
