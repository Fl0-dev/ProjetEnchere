package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
import fr.eni.projetenchere.bo.ArticleVendu;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Retrait;
import fr.eni.projetenchere.bo.Utilisateur;

@WebServlet("/encherir")
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
			
			int no_article = 7;
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
		request.setCharacterEncoding("UTF-8"); 
		
		// on récupère les informations de session et d'article
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		//int no_article = Integer.parseInt(request.getParameter("no_article"));	
		int no_article = 7;
		ArticleVendu articleSelected = EnchereManager.getInstance().selectArticleById(no_article);
		request.setAttribute("articleSelected", articleSelected);
		
		String resultat;
        Map<String, String> MapErreurs = new HashMap<String, String>();
		
		// on récupère les données du formulaire
		int montantEnchere = Integer.valueOf(request.getParameter("montant_enchere"));
		
		// Validation montant de l'offre
        try {
      	  Verification.getInstance().verifEnchere(utilisateurSession, montantEnchere, no_article);
      	  
      	
         
        } catch ( Exception e ) {
        	MapErreurs.put( "montant", e.getMessage() );    
        }
        
      //Initialisation du résultat global de la validation
        if ( MapErreurs.isEmpty() ) {
        	
     	    // insertion de l'enchere en BD
      		Enchere newEnchere = EnchereManager.getInstance().insertEnchere(utilisateurSession, montantEnchere, no_article);
      		System.out.println("Le montant de l'enchere : " + newEnchere.getMontant_enchere());
      		
      		resultat = "Enchère réussie.";  
        	request.setAttribute("resultat", resultat);
        } else {
        	resultat = "Enchère non enregistrée";
        
            System.out.println(resultat);
             request.setAttribute("resultat", resultat);
             request.setAttribute("MapErreurs", MapErreurs);
        }
	
		response.sendRedirect("encherir");
	}

}
