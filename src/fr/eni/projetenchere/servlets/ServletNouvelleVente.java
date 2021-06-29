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

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletNouvelleVente() {
        super();
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on récupère la session
		HttpSession session = request.getSession();
		
		// on récupère la liste des catégories présentes en base de données
		List<Categorie> listeCategories = EnchereManager.getInstance().selectCategorie();
		request.setAttribute("listeCategories", listeCategories);
		
		// on récupère les données du formulaire
		String nomArticle = request.getParameter("nom_article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		String prix_initial = request.getParameter("prix_initial");
		String debutenchere = request.getParameter("debutenchere");
		String finenchere = request.getParameter("finenchere");
		
		// TODO: vérification des erreurs
		
		// insertion des infos
		ArticleVendu newArticleVendu = EnchereManager.getInstance().insertArticle();
		
		// renvoie vers la JSP après traitement
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp");
				rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// on récupère la liste des catégories présentes en base de données
				List<Categorie> listeCategories = EnchereManager.getInstance().selectCategorie();
				request.setAttribute("listeCategories", listeCategories);
		doGet(request, response);
	}

}
