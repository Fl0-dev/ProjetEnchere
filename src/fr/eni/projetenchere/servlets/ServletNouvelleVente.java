package fr.eni.projetenchere.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		debutenchere = LocalDate.parse(request.getParameter("debutenchere"));
		finenchere = LocalDate.parse(request.getParameter("finenchere"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		String resultat;
        Map<String, String> MapErreurs = new HashMap<String, String>();
        
      //Validation du champ nom article
        try {
          	  Verification.getInstance().validationNomArticle( nomArticle );
          	  Verification.getInstance().validationChamp30(nomArticle);
             } catch ( Exception e ) {
             	MapErreurs.put( "nomArticle", e.getMessage() );        
             }
        
      //Validation du champ description
        try {
          	  Verification.getInstance().validationDescription( description );
          	  Verification.getInstance().validationChamp300(description);
             } catch ( Exception e ) {
             	MapErreurs.put( "description", e.getMessage() );        
             }
        
      //Validation du champ categorie
        try {
          	  Verification.getInstance().validationCategorie( categorie );
          	  } catch ( Exception e ) {
             	MapErreurs.put( "categorie", e.getMessage() );        
             }
        
      //Validation du champ date enchère
        try {
          	  Verification.getInstance().validationDateEnchere( debutenchere, finenchere );
          	  } catch ( Exception e ) {
             	MapErreurs.put( "dateEnchere", e.getMessage() );        
             }
        
      //Validation du champ prix initial
        try {
          	  Verification.getInstance().validationPrixInitial( prix_initial );
          	  } catch ( Exception e ) {
             	MapErreurs.put( "prixInitial", e.getMessage() );        
             }
        
      //Validation du champ adresse de retrait
        try {
        	  Verification.getInstance().validationAdresseRetrait( rue, codePostal, ville );
        	  } catch ( Exception e ) {
           	MapErreurs.put( "adresseRetrait", e.getMessage() );        
           }
       
   
        if ( MapErreurs.isEmpty() ) { 
       	
    	    
        	
        	//création d'un retrait
		Retrait retrait = new Retrait(rue, codePostal, ville);
		Categorie categorie2 = EnchereManager.getInstance().selectCategorieById(categorie);
		// création d'un article
		ArticleVendu newArticle = new ArticleVendu();
		newArticle.setNomArticle(nomArticle);
		newArticle.setDescription(description);
		newArticle.setCategorieArticle(categorie2);
		newArticle.setMiseAPrix(prix_initial);
		newArticle.setDateDebutEncheres(debutenchere);
		newArticle.setDateFinEncheres(finenchere);
		newArticle.setUtilisateur(utilisateurSession);
		newArticle.setLieuRetrait(retrait);

		// insertion de l'enchere en BD
		
		try {
			EnchereManager.getInstance().insertArticle(newArticle);
			request.setAttribute("resultat", "Votre article a été ajouté aux ventes");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		resultat = "Votre article a bien été ajouté";  
    	request.setAttribute("resultat", resultat);
    	response.sendRedirect("AccueilConnecte");
    	
    	  } else {
    	 resultat = "Votre article n'a pas été enregistré";
    
    	   request.setAttribute("resultat", resultat);
    	       request.setAttribute("MapErreurs", MapErreurs);
         
    	       RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPNouvelleVente.jsp");
    			rd.forward(request, response);
    	   }

     	

	}

}
