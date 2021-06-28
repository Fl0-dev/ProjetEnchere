package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bll.Verification;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Appel de la session et de l'attribut utilisateurSession
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		// Envoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPModifProfil.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Appel de la session et de l'attribut utilisateurSession
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		// récupération des données
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		String motDePasseNew = request.getParameter("motDePasseNew");
		String confirmation = request.getParameter("confirmation");

		String resultat;
		Map<String, String> MapErreurs = new HashMap<String, String>();

		// Vérifier si nouveau pseudo, qu'il n'existe pas en DB et sa taille
		if (!pseudo.equals(utilisateurSession.getPseudo())) {
			try {
				Verification.getInstance().validationChamp30(pseudo);
				Verification.getInstance().validationPseudo(pseudo);
			} catch (Exception e) {
				MapErreurs.put("pseudo", e.getMessage());
			}
		}

		// Validation nom
		try {
			Verification.getInstance().validationChamp30(nom);
		} catch (Exception e) {
			MapErreurs.put("nom", e.getMessage());
		}

		// Validation prenom
		try {
			Verification.getInstance().validationChamp30(prenom);
		} catch (Exception e) {
			MapErreurs.put("prenom", e.getMessage());
		}

		// Vérifier si nouveau email, qu'il n'existe pas en DB et sa taille
		if (!email.equals(utilisateurSession.getEmail())) {
			try {
				Verification.getInstance().validationChamp20(email);
				Verification.getInstance().validationEmail(email);
			} catch (Exception e) {
				MapErreurs.put("email", e.getMessage());
			}
		}
		// Vérifie si téléphone la taille de l'information
		try {
			Verification.getInstance().validationChampTel(telephone);
		} catch (Exception e) {
			MapErreurs.put("tel", e.getMessage());
		}

		// Validation rue
		try {
			Verification.getInstance().validationChamp30(rue);
		} catch (Exception e) {
			MapErreurs.put("rue", e.getMessage());
		}

		// Validation codePostal
		try {
			Verification.getInstance().validationChamp10(codePostal);
		} catch (Exception e) {
			MapErreurs.put("codePostal", e.getMessage());
		}

		// Validation ville
		try {
			Verification.getInstance().validationChamp30(ville);
		} catch (Exception e) {
			MapErreurs.put("ville", e.getMessage());
		}

		// Vérification mot de passe avec le pseudo d'utilisateurSession
		try {
			Verification.getInstance().verifMdp(utilisateurSession.getPseudo(), motDePasse);
		} catch (Exception e) {
			MapErreurs.put("VerifMdp", e.getMessage());
		}

		// Validation si motDePasseNew
		if (motDePasseNew != null & motDePasseNew.trim().length() != 0) {

			try {
				Verification.getInstance().validationChamp30(motDePasseNew);
			} catch (Exception e) {
				MapErreurs.put("motDePasseNew", e.getMessage());
			}
			// Validation des champs motDePasseNew et confirmation
			try {
				Verification.getInstance().validationMotsDePasse(motDePasseNew, confirmation);
			} catch (Exception e) {
				MapErreurs.put("confirmation", e.getMessage());
			}
		} else {
			// Validation si aucun motDePasseNew
			try {
				Verification.getInstance().validationMotsDePasse(motDePasse, confirmation);
			} catch (Exception e) {
				MapErreurs.put("confirmation", e.getMessage());
			}
		}
		System.out.println("coucou servlet avant traitement");
		// Initialisation du résultat global de la validation
		if (MapErreurs.isEmpty()) {
			// insertion des infos

			EnchereManager.getInstance().modifUtilisateur(utilisateurSession.getNoUtilisateur(), pseudo, nom, prenom,
					email, telephone, rue, codePostal, ville, confirmation);

			// récupération de tous les attributs de newUtilisateur par son pseudo
			utilisateurSession = EnchereManager.getInstance().selectUtilisateurByPseudo(pseudo);
			// ouverture d'une session et mise en attribut du nouvel utilisateur
			session.setAttribute("UtilisateurSession", utilisateurSession);

			resultat = "Succès de la modification. ";
			request.setAttribute("resultat", resultat);
			RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueilConnecte");
			rd.forward(request, response);

		} else {
			resultat = "Échec de la modification. ";

		}
		System.out.println(resultat);
		request.setAttribute("resultat", resultat);
		request.setAttribute("MapErreurs", MapErreurs);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPModifProfil.jsp");
		rd.forward(request, response);

	}

}
