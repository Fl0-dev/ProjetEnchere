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

import com.sun.scenario.effect.impl.state.LinearConvolveRenderState.PassType;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;
import sun.launcher.resources.launcher;

/**
 * Servlet implementation class ServletMonProfil
 */
@WebServlet("/MonProfil")
public class ServletMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletMonProfil() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupère l'utilisateur de la session
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		
		if (utilisateurSession != null) {
			// renvoie vers la JSP Mon Profil
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPMonProfil.jsp");
			rd.forward(request, response);
		} else {
			// renvoie vers l'accueil
			RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueil");
			rd.forward(request, response);
		}
		
	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
