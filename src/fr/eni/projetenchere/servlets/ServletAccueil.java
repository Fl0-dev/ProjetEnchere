package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;


@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletAccueil() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* on récupère la liste des catégories présentes en base de données
				List<Categorie> listeCategories = 
		
	 	 	*/
		// on récupère les enchères en cours
				List<Enchere> listeEncheresEnCours = EnchereManager.getInstance().selectAllEnchere();
				
		// on envoie ça dans la requête
				request.setAttribute("listeEncheresEnCours", listeEncheresEnCours);
				
				System.out.println(listeEncheresEnCours);
				
		// renvoie vers la JSP après traitement
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPAccueil.jsp");
				rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: traitement des données de formulaire pour afficher un résultat de recherche
		doGet(request, response);
	}

}
