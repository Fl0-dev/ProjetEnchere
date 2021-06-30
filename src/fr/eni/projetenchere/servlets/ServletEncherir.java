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
import fr.eni.projetenchere.bo.Utilisateur;

@WebServlet("/ServletEncherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ServletEncherir() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupère l'utilisateur de la session
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		
		// on récupère les ventes (enchères) en cours
		List<ArticleVendu> listeVentesEnCours = EnchereManager.getInstance().selectAllVentesEnCours();
		request.setAttribute("listeVentesEnCours", listeVentesEnCours);
		
		if (utilisateurSession != null) {
			
			//int no_article = Integer.parseInt(request.getParameter("no_article"));
			// récupère id article pour l'afficher
			
			int no_article = 3;
			ArticleVendu articleSelected = EnchereManager.getInstance().selectArticleById(no_article);
			
			request.setAttribute("articleSelected", articleSelected);
			System.out.println(articleSelected);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encherir.jsp");
			rd.forward(request, response);
		} else {
			// renvoie vers l'accueil
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
