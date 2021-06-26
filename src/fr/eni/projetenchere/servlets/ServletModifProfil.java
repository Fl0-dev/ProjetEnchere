package fr.eni.projetenchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletModifProfil
 */
@WebServlet("/ServletModifProfil")
public class ServletModifProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletModifProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Appel de la session et de l'attribut utilisateurSession
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		//Envoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPModifProfil.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
