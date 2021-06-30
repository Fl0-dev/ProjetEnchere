package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.bo.Utilisateur;

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
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		// on récupère la liste des catégories présentes en base de données
		List<Categorie> listeCategories = EnchereManager.getInstance().selectCategorie();
		request.setAttribute("listeCategories", listeCategories);

		// renvoie vers la JSP 
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp");
				rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		// on récupère la session
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		
		// on récupère la liste des catégories présentes en base de données
		List<Categorie> listeCategories = EnchereManager.getInstance().selectCategorie();
		request.setAttribute("listeCategories", listeCategories);
		
		// on récupère les données du formulaire
		LocalDate debutenchere = null;
		LocalDate finenchere = null;
		
		String nomArticle = request.getParameter("nom_article");
		String description = request.getParameter("description");
		int categorie = Integer.valueOf(request.getParameter("categorie"));
		int prix_initial = Integer.valueOf(request.getParameter("prix_initial"));
		try {
			debutenchere = LocalDate.parse(request.getParameter("debutenchere"));
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		
		try {
			finenchere = LocalDate.parse(request.getParameter("finenchere"));
		} catch (DateTimeParseException f) {
				f.printStackTrace();	
		//String finenchere = request.getParameter("finenchere");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		//on regarde si les champs sont vides (si oui message erreur)
				if (nomArticle.isEmpty()|| nomArticle.equals(null)) {
					request.setAttribute("messageErreur", "Veuillez remplir le nom de l'article");
					this.getServletContext().getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp").forward(request, response);
				}
				if (description.isEmpty()|| description.equals(null)) {
					request.setAttribute("messageErreur", "Veuillez saisir une description");
					this.getServletContext().getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp").forward(request, response);
				}
				if (categorie == 0) {
					request.setAttribute("messageErreur", "Veuillez sélectionner une catégorie");
					this.getServletContext().getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp").forward(request, response);
				}
				if (debutenchere.equals(null)|| finenchere.equals(null))  {
					request.setAttribute("messageErreur", "Veuillez saisir une date");
					this.getServletContext().getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp").forward(request, response);
				}
				if (debutenchere.isBefore(finenchere)) {
					request.setAttribute("messageErreur", "La date de début d'enchère doit être inférieure à la date de fin d'enchère");
					this.getServletContext().getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp").forward(request, response);
				}
				if (debutenchere.isAfter(LocalDate.now())) {
					request.setAttribute("messageErreur", "La date de début d'enchère doit être supérieure à la date du jour");
					this.getServletContext().getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp").forward(request, response);
				}
				if (prix_initial<=0) {
					request.setAttribute("messageErreur", "Le prix doit être positif");
					this.getServletContext().getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp").forward(request, response);
				}
		
		// TODO: vérification des erreurs
				
				
		// insertion des infos
				
				
				//Retrait retrait = EnchereManager.getInstance().insertRetrait(rue, codePostal, ville);
				Retrait retrait = new Retrait(rue, codePostal, ville);
				Categorie categorie2 = EnchereManager.getInstance().selectCategorieById(categorie);
				
				ArticleVendu newArticle = new ArticleVendu();
				newArticle.setNomArticle(nomArticle);
				newArticle.setDescription(description);
				newArticle.setCategorieArticle(categorie2);
				newArticle.setMiseAPrix(prix_initial);
				newArticle.setDateDebutEncheres(debutenchere);
				newArticle.setDateFinEncheres(finenchere);
				newArticle.setUtilisateur(utilisateurSession);
				newArticle.setLieuRetrait(retrait);
				
				
				ArticleVendu articleVendu = EnchereManager.getInstance().insertArticle(newArticle);
				
		
		// renvoie vers la JSP après traitement
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp");
				rd.forward(request, response);
		
			}
		}
}
}