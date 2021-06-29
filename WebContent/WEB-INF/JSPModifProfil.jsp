<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier Profil</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
</head>
<body>
	<header>
		<h1><a href="${pageContext.request.contextPath }/AccueilConnecte">ENI-Enchères</a></h1>
	</header>
	<center>
		<h1>Mon Profil</h1>
	</center>
	<form
		action="${pageContext.request.contextPath }/ModifProfil"
		method="POST">

		<center>

			<label for="pseudo">Pseudo <span>*</span> </label> 
			<input type="text" id="pseudo"name="pseudo" value="<c:out value="${utilisateurSession.pseudo }"/>" autofocus > 
			<span>${MapErreurs['pseudo']}</span> 
			 <br> <br> 
			<label for="nom">Nom <span>*</span> </label> 
			<input type="text" id="nom" name="nom" value="<c:out value="${utilisateurSession.nom }"/>" >
			<span>${MapErreurs['nom']}</span>
			<br> <br> 
			<label for="prenom">Prénom <span>*</span> </label> 
			<input type="text" id="prenom" name="prenom" value="<c:out value="${utilisateurSession.prenom }"/>"  > 
			<span>${MapErreurs['prenom']}</span>
			 <br><br> 
			<label for="email">Email <span>*</span> </label> 
			<input type="mail" id="email" name="email" value="<c:out value="${utilisateurSession.email }"/>"  >
			<span>${MapErreurs['email']}</span>
			<br> <br> 
			<label for="telephone">Téléphone : </label> 
			<input type="tel" id="telephone" name="telephone" value="<c:out value="${utilisateurSession.telephone }"/>"> 
			<span>${MapErreurs['tel']}</span>
			 <br><br> 
			<label for="rue">Rue <span>*</span> </label> 
			<input type="text" id="rue" name="rue" value="<c:out value="${utilisateurSession.rue }"/>"  >
			<span>${MapErreurs['rue']}</span>
			<br> <br> 
			<label for="codePostal">Code Postal <span>*</span> </label> 
			<input type="text" id="codePostal" name="codePostal" value="<c:out value="${utilisateurSession.codePostal }"/>"  >
			<span>${MapErreurs['codePostal']}</span> 
			 <br><br> 
			<label for="ville">Ville <span>*</span> </label> 
			<input type="text" id="ville" name="ville" value="<c:out value="${utilisateurSession.ville }"/>"  > 
			<span>${MapErreurs['ville']}</span>
			<br> <br> 
			<label for="motDePasse">Mot de passe actuel <span>*</span> </label> 
			<input type="password" id="motDePasse" name="motDePasse" > 
			<span>${MapErreurs['VerifMdp']}</span> 
			 <br><br> 
			 <label for="motDePasseNew">Nouveau mot de passe <span>*</span> </label> 
			<input type="password" id="motDePasseNew" name="motDePasseNew"  > 
			<span>${MapErreurs['motDePasseNew']}</span> 
			 <br><br> 
			<label for="confirmation">Confirmation <span>*</span> </label> 
			<input type="password"id="confirmation" name="confirmation"  > 
			<span>${MapErreurs['confirmation']}</span> 
			<br> <br>
			<label for="credit">Crédit : </label>
			<label for="credit">${utilisateurSession.credit}</label>
			</center>

		<center>
			<span>${MapErreurs['champ']}</span> 
			<br><br>
			<button type="submit" value="modif" name="bouton" >Enregistrer</button>
			<br>
			<br>
			<button type="submit" value="suppr" name="bouton" >Supprimer mon compte</button>
			<br>
			<br>
			<p>${resultat}</p>
		</center>
	</form>
	<center>
	<a href="${pageContext.request.contextPath }/Accueil">Supprimer mon compte</a>
	</center>
</body>
</html>