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

import com.sun.xml.internal.bind.v2.TODO;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletIdentification
 */
@WebServlet("/ServletIdentification")
public class ServletIdentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPIdentification.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//on récupère identifiants et mot de passe du formulaire
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		
		//on récupère les infos connexion des utilisateurs
		List<Utilisateur> listeUtilisateursConnexion = EnchereManager.getInstance().selectConnexion();
		
		System.out.println(listeUtilisateursConnexion);
		
		//on vérifie que les identifiants sont identiques en BDD : si oui on part vers la servlet Accueil Connecté, sinon retour sur la JSP connexion avec un message d'erreur
		for (Utilisateur utilisateur : listeUtilisateursConnexion) {
			if ((identifiant.equals(utilisateur.getPseudo()) || (identifiant.equals(utilisateur.getEmail()))) && mdp.equals(utilisateur.getMotDePasse())) {
				HttpSession session = request.getSession();
				session.setAttribute("pseudo", utilisateur.getPseudo());
				
				RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueilConnecte");
				rd.forward(request, response);
			}
			else {
				//TODO : mettre message d'erreur dans la JSP  
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPIdentification.jsp");
				rd.forward(request, response);
			}
		}
		
		
			
	}

}
