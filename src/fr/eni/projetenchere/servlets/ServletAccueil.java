package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JSpinner.ListEditor;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;


@WebServlet(urlPatterns = { "/Accueil", "/SuppressionProfil"})
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletAccueil() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// on regarde quelle URL a été utilisée pour accéder à la servlet
				String urlUtilisee = request.getServletPath();
				// si c'est l'url /SuppressionProfil
				if (urlUtilisee.equals("/SuppressionProfil")) {
					String resultat = "Le profil a bien été supprimé.";
					request.setAttribute("resultat", resultat);		
				}
		
		
		// on récupère la liste des catégories présentes en base de données
				List<Categorie> listeCategories = EnchereManager.getInstance().selectCategorie();
				request.setAttribute("listeCategories", listeCategories);
		
		// on récupère les ventes (enchères) en cours
				List<ArticleVendu> listeVentesEnCours = EnchereManager.getInstance().selectAllVentesEnCours();
				
		// on envoie ça dans la requête
				request.setAttribute("listeVentesEnCours", listeVentesEnCours);
			
		// renvoie vers la JSP après traitement
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPAccueil.jsp");
				rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// on récupère la liste des catégories présentes en base de données
		List<Categorie> listeCategories = EnchereManager.getInstance().selectCategorie();
		request.setAttribute("listeCategories", listeCategories);
		
		// je récupère les résultats de la recherche
		// String contenuRecherche = request.getParameter("contenuRecherche").trim();
		int categorie = Integer.valueOf(request.getParameter("categorie"));
		
		List<ArticleVendu> listeVentesEnCours = EnchereManager.getInstance().selectAllVentesEnCours();
		
		// si l'option choisit est "toutes", on recherche uniquement par nom d'article
		if (categorie == 0) {
			
		} else {
			// sinon, afficher résultats de la recherche par catégorie ET nom article
		}
		
		request.setAttribute("listeVentesEnCours", listeVentesEnCours);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPAccueil.jsp");
		rd.forward(request, response);
	
	}

}
