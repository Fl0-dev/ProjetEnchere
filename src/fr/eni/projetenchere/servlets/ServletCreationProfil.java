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

import org.apache.el.util.Validation;

import fr.eni.projetenchere.bll.EnchereManager;
import fr.eni.projetenchere.bll.Verification;
import fr.eni.projetenchere.bo.Utilisateur;


@WebServlet("/ServletCreationProfil")
public class ServletCreationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPCreationProfil.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// on récupère identifiants et mot de passe du formulaire
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		
		String resultat;
        Map<String, String> MapErreurs = new HashMap<String, String>();
        
        
        //Validation du champ pseudo
        try {
     	   Verification.getInstance().validationPseudo( pseudo );
     	  Verification.getInstance().validationChamp30(pseudo);
         } catch ( Exception e ) {
        	MapErreurs.put( "pseudo", e.getMessage() );        
        	}
        //Validation nom
        try {
      	  Verification.getInstance().validationChamp30(nom);
          } catch ( Exception e ) {
         	MapErreurs.put( "nom", e.getMessage() );        
         	}
        
      //Validation prenom
        try {
      	  Verification.getInstance().validationChamp30(prenom);
          } catch ( Exception e ) {
         	MapErreurs.put( "prenom", e.getMessage() );        
         	}
        
        //Validation du champ email
        try {
        	Verification.getInstance().validationChamp20(email);
           Verification.getInstance().validationEmail( email );
        } catch ( Exception e ) {
        	MapErreurs.put( "email", e.getMessage() );
        }
        
      //Vérifie si téléphone la taille de l'information
        try {
        	Verification.getInstance().validationChampTel(telephone);
        }catch(Exception e) {
        	MapErreurs.put( "tel", e.getMessage() );
        }
        
      //Validation rue
        try {
      	  Verification.getInstance().validationChamp30(rue);
          } catch ( Exception e ) {
         	MapErreurs.put( "rue", e.getMessage() );        
         	}
        
      //Validation codePostal
        try {
      	  Verification.getInstance().validationChamp10(codePostal);
          } catch ( Exception e ) {
         	MapErreurs.put( "codePostal", e.getMessage() );        
         	}
        
      //Validation ville
        try {
      	  Verification.getInstance().validationChamp30(ville);
          } catch ( Exception e ) {
         	MapErreurs.put( "ville", e.getMessage() );        
         	}
        
        //Validation motDePasse
        try {
        	  Verification.getInstance().validationChamp30(motDePasse);
            } catch ( Exception e ) {
           	MapErreurs.put( "motDePasse", e.getMessage() );        
           	}
        
        //Validation confirmation similaire à motDePasse
        try {
        	Verification.getInstance().validationMotsDePasse( motDePasse, confirmation );
        } catch ( Exception e ) {
        	MapErreurs.put( "confirmation", e.getMessage() );
        }
       
       //Initialisation du résultat global de la validation
       if ( MapErreurs.isEmpty() ) {
    	   //insertion des infos
    	   Utilisateur newUtilisateur = EnchereManager.getInstance().inserNewtUtilisateur(pseudo,nom,prenom,email,telephone,rue,
   				codePostal,ville,motDePasse);
    		
       	//récupération de tous les attributs de newUtilisateur par son pseudo
    	Utilisateur utilisateurSession = EnchereManager.getInstance().selectUtilisateurByPseudo(pseudo);
    	//ouverture d'une session et mise en attribut du nouvel utilisateur
        HttpSession session = request.getSession();
        session.setAttribute("UtilisateurSession", utilisateurSession);
        
    	resultat = "Succès de l'inscription.";  
    	request.setAttribute("resultat", resultat);
    	RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueilConnecte");
		rd.forward(request, response);
    	
        } else {
            resultat = "Échec de l'inscription";
            
        }
       System.out.println(resultat);
        request.setAttribute("resultat", resultat);
        request.setAttribute("MapErreurs", MapErreurs);
		
       

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPCreationProfil.jsp");
		rd.forward(request, response);
		
	}

}
