package fr.eni.projetenchere.servlets;

import java.io.IOException;
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
		
		
		//int no_article = Integer.parseInt(request.getParameter("no_article"));
		// récupère id article pour l'afficher
		int article = Integer.parseInt(request.getParameter("article"));
		
		ArticleVendu articleSelected = EnchereManager.getInstance().selectArticleById(article);		
		request.setAttribute("articleSelected", articleSelected);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encherir.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		// on récupère les informations de session et d'article
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		int article = Integer.parseInt(request.getParameter("article"));	
		
		//int no_article = 7;
		ArticleVendu articleSelected = EnchereManager.getInstance().selectArticleById(article);
		request.setAttribute("articleSelected", articleSelected);
		
		//utilisateurSession = EnchereManager.getInstance().selectUserById(utilisateurSession.getNoUtilisateur());
		
		String resultat;
        Map<String, String> MapErreurs = new HashMap<String, String>();
		
		// on récupère les données du formulaire
		int montantEnchere = Integer.valueOf(request.getParameter("montant_enchere"));
		
		// Validation montant de l'offre
        try {
      	  Verification.getInstance().verifEnchere(utilisateurSession, montantEnchere, article);

         
        } catch ( Exception e ) {
        	MapErreurs.put( "montant", e.getMessage() );    
        }
        
      //Initialisation du résultat global de la validation
        if ( MapErreurs.isEmpty() ) {
        	
     	    // insertion de l'enchere en BD
      		Enchere newEnchere = EnchereManager.getInstance().insertEnchere(utilisateurSession, montantEnchere, article);
      		System.out.println("Le montant de l'enchere : " + newEnchere.getMontant_enchere());
      		
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
	
		response.sendRedirect("encherir");
	}

}
