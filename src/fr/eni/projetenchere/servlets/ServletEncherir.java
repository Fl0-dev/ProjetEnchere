package fr.eni.projetenchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.ArticleVendu;

@WebServlet("/ServletEncherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ServletEncherir() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * récupération du numéro d'article
		 */
		//int no_article = Integer.parseInt(request.getParameter("no_article"));
		
		int no_article = 1;
		ArticleVendu articleSelected = EnchereManager.getInstance().selectArticleById(no_article);

		request.setAttribute("articleSelected", articleSelected);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encherir.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
