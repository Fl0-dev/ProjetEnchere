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
import fr.eni.projetenchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletIdentification
 */
@WebServlet("/Identification")
public class ServletIdentification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/JSPIdentification.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// on récupère identifiants et mot de passe du formulaire
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");

		// on récupère les infos connexion des utilisateurs
		List<Utilisateur> listeUtilisateursConnexion = EnchereManager.getInstance().selectConnexion();

		// si les champs identifiant ou mdp vides : message erreur
		if (identifiant.length() == 0 || identifiant.isEmpty()) {
			// message d'erreur dans la JSP
			request.setAttribute("messageErreur", "L'identifiant doit être renseigné");
			this.getServletContext().getRequestDispatcher("/WEB-INF/JSPIdentification.jsp").forward(request, response);
		} else if (mdp.length() == 0 || mdp.isEmpty()) {
			// message d'erreur dans la JSP
			request.setAttribute("messageErreur", "Le mot de passe doit être renseigné");
			this.getServletContext().getRequestDispatcher("/WEB-INF/JSPIdentification.jsp").forward(request, response);
		}

		// on vérifie que les identifiants sont identiques en BDD : si oui on part vers
		// la servlet Accueil Connecté, sinon retour sur la JSP connexion avec un
		// message d'erreur

		// String utilisateurPseudo = null;
		int userID = 0;
		
		for (Utilisateur utilisateur : listeUtilisateursConnexion) {

			// si l'identifiant (pseudo ou e-mail) est trouvé en BDD ET si le mot de passe
			if ((identifiant.equals(utilisateur.getPseudo()) || (identifiant.equals(utilisateur.getEmail())))
					&& mdp.equals(utilisateur.getMotDePasse()) && utilisateur.getEtat()==true) {

				//utilisateurPseudo = utilisateur.getPseudo();
				userID = utilisateur.getNoUtilisateur();

			}

		}

		//if (utilisateurPseudo != null) {
		if (userID != 0) {
			//Utilisateur utilisateurSession = EnchereManager.getInstance().selectUtilisateurByPseudo(utilisateurPseudo);
			Utilisateur utilisateurSession = EnchereManager.getInstance().selectUserById(userID);

			HttpSession session = request.getSession();

			session.setAttribute("utilisateurSession", utilisateurSession);

			response.sendRedirect("AccueilConnecte");

		} else {
			request.setAttribute("messageErreur", "L'identifiant ou le mot de passe est invalide");
			this.getServletContext().getRequestDispatcher( "/WEB-INF/JSPIdentification.jsp" ).forward( request, response );
		}

	}

}
