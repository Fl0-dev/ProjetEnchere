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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Appel de la session et de l'attribut utilisateurSession
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateurSession");
		//Envoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPModifProfil.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupération des données
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
		
      //Validation pour éviter les champs vides
        try {
        	EnchereManager.getInstance().validationChamp(pseudo);
			EnchereManager.getInstance().validationChamp(nom);
			EnchereManager.getInstance().validationChamp(prenom);
			EnchereManager.getInstance().validationChamp(email);
			EnchereManager.getInstance().validationChamp(rue);
			EnchereManager.getInstance().validationChamp(codePostal);
			EnchereManager.getInstance().validationChamp(ville);
			EnchereManager.getInstance().validationChamp(motDePasse);
			EnchereManager.getInstance().validationChamp(motDePasseNew);
			EnchereManager.getInstance().validationChamp(confirmation);
		} catch (Exception e) {
			MapErreurs.put( "champ", e.getMessage() );
		}
        
        //Vérification mot de passe
        try {
        	EnchereManager.getInstance().verifMdp(pseudo,motDePasse);
		} catch (Exception e) {
			MapErreurs.put("VerifMdp",e.getMessage());
		}

        //Validation du champ email
        try {
           EnchereManager.getInstance().validationEmail( email );
        } catch ( Exception e ) {
        	MapErreurs.put( "email", e.getMessage() );
        }

        //Validation des champs mot de passe et confirmation
        try {
        	EnchereManager.getInstance().validationMotsDePasse( motDePasseNew, confirmation );
        } catch ( Exception e ) {
        	MapErreurs.put( "motDePasse", e.getMessage() );
        }
        //Validation du champ pseudo
       try {
        	EnchereManager.getInstance().validationPseudo( pseudo );
        } catch ( Exception e ) {
       	MapErreurs.put( "pseudo", e.getMessage() );        
       	}
		
     //Initialisation du résultat global de la validation
       if ( MapErreurs.isEmpty() ) {
    	   //insertion des infos
    	   EnchereManager.getInstance().modifUtilisateur(pseudo,nom,prenom,email,telephone,rue,
   				codePostal,ville,motDePasse);
    		
       	//récupération de tous les attributs de newUtilisateur par son pseudo
    	Utilisateur utilisateurSession = EnchereManager.getInstance().selectUtilisateurByPseudo(pseudo);
    	//ouverture d'une session et mise en attribut du nouvel utilisateur
        HttpSession session = request.getSession();
        session.setAttribute("UtilisateurSession", utilisateurSession);
        
    	resultat = "Succès de la modification";  
    	request.setAttribute("resultat", resultat);
    	RequestDispatcher rd = request.getRequestDispatcher("/ServletAccueilConnecte");
		rd.forward(request, response);
    	
        } else {
            resultat = "Échec de la modification";
            
        }
       System.out.println(resultat);
        request.setAttribute("resultat", resultat);
        request.setAttribute("MapErreurs", MapErreurs);
		
       

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSPModifProfil.jsp");
		rd.forward(request, response);
		
	}

}
