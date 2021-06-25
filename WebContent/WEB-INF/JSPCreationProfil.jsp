<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Création Profil</title>
</head>
<body>
	<center>
		<h1>Mon Profil</h1>
	</center>
	<form
		action="${pageContext.request.contextPath }/ServletCreationProfil"
		method="POST">

		<center>

			<label for="pseudo">Pseudo : </label> 
			<input type="text" id="pseudo"name="pseudo" value="<c:out value="${param.pseudo }"/>" autofocus required> 
			<span class="erreur">${MapErreurs['pseudo']}</span> 
			 <br> <br> 
			<label for="nom">Nom : </label> 
			<input type="text" id="nom" name="nom" value="<c:out value="${param.nom }"/>" required>
			<br> <br> 
			<label for="prenom">Prénom : </label> 
			<input type="text" id="prenom" name="prenom" value="<c:out value="${param.prenom }"/>"  required> 
			 <br><br> 
			<label for="email">Email : </label> 
			<input type="mail" id="email" name="email" value="<c:out value="${param.email }"/>"  required>
			<span class="erreur">${MapErreurs['email']}</span>
			<br> <br> 
			<label for="telephone">Téléphone : </label> 
			<input type="tel" id="telephone" name="telephone" value="<c:out value="${param.telephone }"/>"> 
			 <br><br> 
			<label for="rue">Rue : </label> 
			<input type="text" id="rue" name="rue" value="<c:out value="${param.rue }"/>"  required>
			<br> <br> 
			<label for="codePostal">Code Postal : </label> 
			<input type="text" id="codePostal" name="codePostal" value="<c:out value="${param.codePostal }"/>"  required>
			 <br><br> 
			<label for="ville">Ville : </label> 
			<input type="text" id="ville" name="ville" value="<c:out value="${param.ville }"/>"  required> 
			<br> <br> 
			<label for="motDePasse">Mot de passe : </label> 
			<input type="password" id="motDePasse" name="motDePasse"  required> 
			 <br><br> 
			<label for="confirmation">Confirmation : </label> 
			<input type="password"id="confirmation" name="confirmation"  required> 
			<span class="erreur">${MapErreurs['motDePasse']}</span> 
			<br> <br>
		</center>

		<center>
			<button type="submit">Créer</button>
			<br>
			<br>
			<p>${resultat}</p>
		</center>
	</form>
	<center>
	<a href="${pageContext.request.contextPath }/ServletAccueil">Annuler</a>
	</center>
</body>
</html>