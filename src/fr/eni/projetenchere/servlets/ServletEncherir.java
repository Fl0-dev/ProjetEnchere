package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.monitor.Monitor;
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
		
		System.out.println(utilisateurSession);
		
		// récupère id article pour l'afficher
		int article = Integer.parseInt(request.getParameter("article"));
		
		ArticleVendu articleSelected = EnchereManager.getInstance().selectArticleById(article);		
		request.setAttribute("articleSelected", articleSelected);
		System.out.println(articleSelected.getLieuRetrait());
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encherir.jsp");
		//request.setAttribute("artid", articleSelected.getNoArticle());
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		// on récupère les informations de session et d'article
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		
		// on récupère les données du formulaire
		int montantEnchere = Integer.parseInt(request.getParameter("montant_enchere"));
		int artid = Integer.parseInt(request.getParameter("artid"));
				
		ArticleVendu articleSelected = EnchereManager.getInstance().selectArticleById(artid);
		request.setAttribute("articleSelected", articleSelected);
		
		String resultat;
        Map<String, String> MapErreurs = new HashMap<String, String>();
		

		// Validation montant de l'offre
        try {
      	  Verification.getInstance().verifEnchere(utilisateurSession, montantEnchere, articleSelected);

         
        } catch ( Exception e ) {
        	MapErreurs.put( "montant", e.getMessage() );    
        }
        
      //Initialisation du résultat global de la validation
        if ( MapErreurs.isEmpty() ) {
        	
     	    // insertion de l'enchere en BD
      		try {
				Enchere newEnchere = EnchereManager.getInstance().insertEnchere(utilisateurSession, montantEnchere, artid);
				System.out.println(newEnchere);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
      		
      		//TODO: retirer le montant de l'offre du crédit utilisateur
      		//EnchereManager.getInstance().updateCredit(utilisateurSession, montantEnchere);
      		
      		resultat = "Enchère réussie.";  
        	request.setAttribute("resultat", resultat);
        	
        } else {
        	resultat = "Enchère non enregistrée";
        
            System.out.println(resultat);
             request.setAttribute("resultat", resultat);
             request.setAttribute("MapErreurs", MapErreurs);
             
             
        }
	
        RequestDispatcher rd = request.getRequestDispatcher("AccueilConnecte");
		//request.setAttribute("artid", articleSelected.getNoArticle());
		rd.forward(request, response);
	}

}
