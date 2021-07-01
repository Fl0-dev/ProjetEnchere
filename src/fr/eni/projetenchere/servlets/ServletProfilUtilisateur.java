package fr.eni.projetenchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletProfilUtilisateur
 */
@WebServlet("/voirprofil")
public class ServletProfilUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ServletProfilUtilisateur() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupère l'utilisateur de la session
				HttpSession session = request.getSession();
				Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");

				// si l'utilisateur est connecté
				if (utilisateurSession != null) {
					
					int id = Integer.parseInt(request.getParameter("id"));
					
					Utilisateur profilSelected = EnchereManager.getInstance().selectUserById(id);
					request.setAttribute("profilSelected", profilSelected);
					
					// renvoie vers la JSP Profil Utilisateur
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPProfilUtilisateur.jsp");
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
